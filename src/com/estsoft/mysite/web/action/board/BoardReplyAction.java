package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.mysite.vo.BoardVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardReplyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = Long.parseLong(request.getParameter("no"));	// 게시물 번호
		System.out.println("BoardReplyAction: "+no);
		
		if( no == null || WebUtil.isNumeric( Long.toString(no) ) == false ) {
			WebUtil.redirect( request, response, request.getContextPath() + "/board" );
			return;
		}
		
		
		// dao에서 게시물 번호에 대한 값 가져와서 넘겨주기
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		BoardVO vo = dao.getReplyInform(no);
		System.out.println("BoardReplyAction vo:"+vo);
		
		request.setAttribute("vo", vo);
		
//		WebUtil.forward(request, response, "/WEB-INF/views/board/replyform.jsp?no="+no);
		WebUtil.forward(request, response, "/WEB-INF/views/board/replyform.jsp");
		
	}

}
