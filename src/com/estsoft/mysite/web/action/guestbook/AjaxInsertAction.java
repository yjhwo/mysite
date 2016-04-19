package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDAO;
import com.estsoft.mysite.vo.GuestBookVO;
import com.estsoft.web.action.Action;

import net.sf.json.JSONObject;

public class AjaxInsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// parameter 값을 가져오기
		String name = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		String message = request.getParameter("message");
		/*
		 * vo값 세팅
		 * name,password,message
		 */
		GuestBookVO vo = new GuestBookVO(name, passwd, message);
		
		GuestBookDAO dao = new GuestBookDAO(new MySQLWebDBConnection());
		Long no = dao.insert(vo);
		vo = dao.get(no);			// 이렇게 해야 reg_date이 들어있음
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("result", "success");
		map.put("data", vo);		// vo값을 가지고 화면에 맨 위에 추가해주기 위해서
		
		JSONObject jsonObject = JSONObject.fromObject(map);	//java object를 json object로 바꿔줌
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);
	}

}
