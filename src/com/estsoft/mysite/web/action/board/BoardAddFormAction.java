package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.mysite.vo.BoardVO;
import com.estsoft.mysite.vo.UserVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardAddFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인증 유무 체크
		HttpSession session = request.getSession();
		if (session == null) {
			WebUtil.redirect(request, response, request.getContextPath() + "/board");
			return;
		}
		UserVO authUser = (UserVO) session.getAttribute("authUser");
		if (authUser == null) {
			WebUtil.redirect(request, response, request.getContextPath() + "/board");
			return;
		}

		String user_no = request.getParameter("user_no");
		System.out.println("BoardAddFormAction user_no:" + user_no);

		// WebUtil.forward(request, response,
		// "/WEB-INF/views/board/write.jsp?user_no="+user_no+"&no="+no);
		WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp?user_no=" + user_no);
	}

}
