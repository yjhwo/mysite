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

public class BoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = Long.parseLong(request.getParameter("no"));	// 게시물 번호
		System.out.println("BoardModifyAction: "+no);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		// 수정 처리 (dao)
		BoardVO vo = new BoardVO(no, title, content);
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		dao.modify(vo);
		
		WebUtil.redirect(request, response, "/mysite/board");
	}

}
