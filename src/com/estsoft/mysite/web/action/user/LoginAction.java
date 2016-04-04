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

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVO vo = new UserVO(email, password);
		UserDAO dao = new UserDAO(new MySQLWebDBConnection());
		UserVO authUser = dao.get(vo);
		
		System.out.println(authUser);
		
		if(authUser == null){
			// 이메일이나 비밀번호가 틀린 경우

			//1. redirect방식
//			WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail");
			
			// 2. forward방식
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform_fail.jsp");
			return ;
		}
		
		// 인증 성공 코드(로그인처리)
		HttpSession session = request.getSession(true);		// JSESSIONID와 mapping된 객체가 리턴됨
		// JSESSIONID쿠키만 다루는 method
		// true; 객체가 없으면 만들어주고, 있어도 새로 만드는 것?? true 안 주면 없으면 null을 리턴한다.
		// 사용하는 쪽에선 false 또는 파라미터 값 안 주고 사용?
		session.setAttribute("authUser", authUser);			// UserVO객체를 저장
		
		UserVO uu = (UserVO)session.getAttribute("authUser");
		System.out.println("userVO:::::"+uu);
		uu.getNo();
		
		WebUtil.redirect(request, response, "/mysite/main");
		
		
		
	}

}
