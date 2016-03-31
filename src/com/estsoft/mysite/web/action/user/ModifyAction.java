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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 세션 가져오기
		HttpSession session = request.getSession();
		UserVO authUser = (UserVO)session.getAttribute("authUser");
		System.out.println("ModifyAction의 authUser "+authUser);
		
		Long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVO userVo = new UserVO(name, password, gender);
		userVo.setNo(no);
		System.out.println("ModifyAction의 userVo "+userVo);
		
		UserDAO dao = new UserDAO(new MySQLWebDBConnection());
		dao.update(userVo);

		// 세션 저장객체 내용 변경
		authUser.setName(name);
		
		// 리다이렉트
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
