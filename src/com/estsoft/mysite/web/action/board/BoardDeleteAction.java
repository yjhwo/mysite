package com.estsoft.mysite.web.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.BoardDAO;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String no = request.getParameter("no");
		System.out.println("BoardDeleteAction board_no:"+no);
		
		if(no != null){	// 번호가 제대로 들어온 경우에만 처리
			
			BoardDAO dao = new BoardDAO(new MySQLWebDBConnection());
			int cnt = dao.delete(Integer.parseInt(no));
			
			if(cnt<1){
				System.out.println("삭제에 실패하였습니다.");
			}
			
			WebUtil.redirect(request, response, "/mysite/board");
			
		}
		
		
		
		
	}

}
