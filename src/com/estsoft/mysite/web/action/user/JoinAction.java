package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDAO;
import com.estsoft.mysite.vo.UserVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class JoinAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO vo = new UserVO(name, email, password, gender);
		UserDAO dao = new UserDAO(new MySQLWebDBConnection());
		dao.insert(vo);
				
		WebUtil.redirect(request, response, "/mysite/user?a=joinsuccess");
	}

}
