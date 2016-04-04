package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.mysite.dao.GuestBookDAO;
import com.estsoft.mysite.vo.BoardVO;
import com.estsoft.mysite.vo.GuestBookVO;
import com.estsoft.mysite.vo.UserVO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("BoardAddAction 진입");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String user_no = request.getParameter("user_no");
		System.out.println("BoardAddAction user_no:"+user_no);
		
		HttpSession session = request.getSession();
		BoardVO vo2 = new BoardVO(title, content);
		session.setAttribute("vo2", vo2);

		
		String group_no = request.getParameter("group_no");
		String order_no = request.getParameter("order_no");
		String depth = request.getParameter("depth");
		
		BoardVO vo = new BoardVO(title, content, Long.parseLong(user_no));
		BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
		
		if(group_no != null){
			
			vo.setGroup_no(Long.parseLong(group_no));
			vo.setOrder_no(Long.parseLong(order_no)+1);
			vo.setDepth(Long.parseLong(depth)+1);
			
//			dao.updateGroupOrder();
		}
				
		dao.insert(vo);
		

		WebUtil.redirect(request, response, "/mysite/board");
	}

}
