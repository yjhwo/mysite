package com.estsoft.mysite.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.mysite.web.action.guestbook.GuestBookActionFactory;
import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

@WebServlet("/gb")
public class GuestBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String actionName = request.getParameter("a");
		System.out.println("actionName:"+actionName);
		
		ActionFactory actionFactory = new GuestBookActionFactory();
		Action action = actionFactory.getAction(actionName);
		
		action.execute(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
