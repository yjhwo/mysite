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

public class BoardAddAction implements Action {

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

		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_no = request.getParameter("user_no");
		System.out.println("BoardAddAction user_no:" + user_no);

		/*
		 * String no = request.getParameter("no"); System.out.println(
		 * "BoardAddAction no:"+no);
		 * 
		 * HttpSession session = request.getSession(); BoardVO boardVO = new
		 * BoardVO(Long.parseLong(no), title, content);
		 * session.setAttribute("boardVO", boardVO);
		 * 
		 */
		String group_no = request.getParameter("group_no"); // 3
		String order_no = request.getParameter("order_no"); // 1
		String depth = request.getParameter("depth"); // 0
		System.out.println("BoardAddAction:" + user_no + ":" + group_no + ":" + order_no + ":" + depth);

		BoardVO vo = new BoardVO(title, content, Long.parseLong(user_no));
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());

		if (group_no != null) {
			vo.setGroup_no(Long.parseLong(group_no)); 		// 3
			vo.setOrder_no(Long.parseLong(order_no) + 1); 	// 2 
			vo.setDepth(Long.parseLong(depth) + 1); 		// 1

			 dao.updateGroupOrder(vo);						// order_no 증가시키는 메소드(insert하기 전에 같은 그룹내의 order_no보다 큰 order_no는 다 +1
		}

		dao.insert(vo);

		WebUtil.redirect(request, response, "/mysite/board");
	}

}
