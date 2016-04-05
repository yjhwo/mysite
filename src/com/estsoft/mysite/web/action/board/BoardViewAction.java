package com.estsoft.mysite.web.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.mysite.vo.BoardVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		Long no = Long.parseLong(request.getParameter("no")); // 게시물 번호
		System.out.println("BoardViewAction no:" + no);

		if( no == null || WebUtil.isNumeric( Long.toString(no) ) == false ) {
			WebUtil.redirect( request, response, request.getContextPath() + "/board" );
			return;
		}
		
		
		Long user_no = Long.parseLong(request.getParameter("user_no"));
		System.out.println("BoardViewAction user_no:"+user_no);
		
		
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		BoardVO vo = dao.get(no); // 글 자세히 보기 ( title, content ) 가져옴
		dao.updateCount(no); // viewCount+1

		vo.setNo(no);
		vo.setUser_no(user_no);				// request에 id번호도 넘겨줘야한다.
		request.setAttribute("vo", vo);
		
		// ------ session에 no,title,content담는다. modify에서 사용
		HttpSession session = request.getSession();
		BoardVO boardVO = new BoardVO(no, vo.getTitle(), vo.getContent());
		session.setAttribute("boardVO", boardVO);
		// ------	
		
		
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");
	}

}
