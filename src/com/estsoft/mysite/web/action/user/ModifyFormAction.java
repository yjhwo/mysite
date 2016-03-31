package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDAO;
import com.estsoft.mysite.vo.UserVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// 세션 가져오기
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		System.out.println("ModifyFormAction의 authUser "+authUser);
		
		UserDAO dao = new UserDAO(new MySQLWebDBConnection());
		UserVO userVo = dao.get(authUser.getNo());
		
		System.out.println("ModifyFormAction의 userVo "+userVo);
		request.setAttribute("userVo", userVo);
		
		WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
		
	}

}
