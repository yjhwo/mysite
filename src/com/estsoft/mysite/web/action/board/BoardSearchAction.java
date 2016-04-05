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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String kwd = request.getParameter("kwd");			// 검색어
		System.out.println("BoardSearchAction keyword:"+kwd);
		
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		List<BoardVO> list = dao.search(kwd);		// 검색된 결과 리턴
		
//		Map<String, Object> mapList = new HashMap<String, Object>();
//		mapList.put("list", list);
//		mapList.put("kwd", kwd);		// ??? 잘 모르겠음
//		
//		request.setAttribute("mapList", mapList);
		
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
	}

}
