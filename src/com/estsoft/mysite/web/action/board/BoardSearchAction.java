package com.estsoft.mysite.web.action.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.mysite.vo.BoardVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardSearchAction implements Action {
	private static final int COUNTPAGE = 5;			// 페이지 당 게시물의 수
	private static final int COUNTLIST = 5;			// 페이지 리스트의 수
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kwd = request.getParameter("kwd");			// 검색어
		System.out.println("BoardSearchAction keyword:"+kwd);
		
		// -------
		int page;
		
		if(request.getParameter("page")==null || request.getParameter("page")==""){
			page = 1;
		}else{
			page = Integer.parseInt(request.getParameter("page"));
		}
	
		
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		List<BoardVO> list = dao.search(kwd, page);		// 검색된 결과 리턴
		request.setAttribute("list", list);
		// ------
		
		int left = 1, right = 1;
		int startPage, lastPage;
		int count = dao.getTotalCount(kwd);			// search도 바꿔주기!!
		int maxPage = count/COUNTPAGE;
		
		if(count % COUNTPAGE != 0)
			maxPage++;
		
		if(page <1 || page > maxPage)
			page = 1;
		
		int maxPageGroup = maxPage/COUNTLIST;
		if(maxPage % COUNTLIST != 0)
			maxPageGroup++;
		
		int selectedPageGroup = page/COUNTLIST;
		if(page % COUNTLIST != 0)
			selectedPageGroup++;
		
		if(selectedPageGroup == 1)
			left = 0;
		
		if(selectedPageGroup == maxPageGroup)
			right = 0;
		
		startPage = (selectedPageGroup-1)*COUNTLIST+1;
		lastPage = (selectedPageGroup)*COUNTLIST;
		
		if(lastPage > maxPage)
			lastPage = maxPage;
		
		System.out.println(selectedPageGroup+":"+left+":"+right+":"+startPage+":"+lastPage+":"+page+":"+count);
		// ----------
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("left", left);
		map.put("right", right);
		map.put("startPage", startPage);
		map.put("lastPage", lastPage);
		map.put("page", page);
		map.put("total", count);
		
		request.setAttribute("pageMap", map);		
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
