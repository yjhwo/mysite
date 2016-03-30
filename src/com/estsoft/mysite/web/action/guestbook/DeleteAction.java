package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDAO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		String password = request.getParameter("password");

		if (!no.equals(null)) { // no가 제대로 넘어온 경우에만 처리

			GuestBookDAO dao = new GuestBookDAO(new MySQLWebDBConnection());
			int chk = dao.delete(Integer.parseInt(no), password);

			if (chk > 0){
				WebUtil.redirect(request, response, "/mysite/gb");
			}
			else{

				WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deletefail.jsp?no="+no);
			}
		}
	}

}
