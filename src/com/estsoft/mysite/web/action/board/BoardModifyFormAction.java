package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long no = Long.parseLong(request.getParameter("no"));	// 게시물 번호
		System.out.println("BoardModifyFormAction: "+no);
		
		request.setAttribute("no", no);
		WebUtil.forward(request, response, "/WEB-INF/views/board/modify.jsp");
	}

}
