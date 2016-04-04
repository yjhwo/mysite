package com.estsoft.mysite.web.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.mysite.vo.BoardVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Long no = Long.parseLong(request.getParameter("no"));	// 게시물 번호
		System.out.println("BoardViewAction: "+no);
		
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		BoardVO vo = dao.get(no);			// 글 자세히 보기
		dao.updateCount(no);			    // viewCount+1
		
		vo.setNo(no);
		request.setAttribute("vo", vo);			
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
