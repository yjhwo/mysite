package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.mysite.vo.UserVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// 그냥 logout으로 바로 들어올 수도 있기 때문에
		if(session == null){
			WebUtil.redirect(request, response, "/mysite/main");
			return ;
		}
		
		
		// 로그아웃 처리
		session.removeAttribute("authUser"); 	// authUser라는 이름으로 저장되어 있는 UserVO객체를 제거하라.
		session.invalidate();					// 세션 무효화
		// 세션객체 자체를 소멸시키는 것이 아니라,세션의 기능을 중단시키고 무효화 시키는 것
		
		WebUtil.redirect(request, response, "/mysite/main");
		
	}

}
