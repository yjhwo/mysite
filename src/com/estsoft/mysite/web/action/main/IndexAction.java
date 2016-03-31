package com.estsoft.mysite.web.action.main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class IndexAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Cookie cookie = new Cookie("testCookie","Hello World");	// 쿠키이름, 내용
		cookie.setMaxAge(60*60*24*1);		// 언제까지 유효해야 하는가. 초 단위 => 하루
		cookie.setPath("/mysite/");
		response.addCookie(cookie);			// response에 cookie를 굽는다.
		
		
		WebUtil.forward(request,response,"/WEB-INF/views/main/index.jsp");
	}

}
