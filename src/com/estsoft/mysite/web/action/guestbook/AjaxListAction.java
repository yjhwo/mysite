package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDAO;
import com.estsoft.mysite.vo.GuestBookVO;
import com.estsoft.web.action.Action;

import net.sf.json.JSONObject;

public class AjaxListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = request.getParameter("p");
		
		GuestBookDAO dao = new GuestBookDAO(new MySQLWebDBConnection());
		List<GuestBookVO> list = dao.getList(Integer.parseInt(page));
		
		Map<String, Object> map = new HashMap<String,Object>();
		map.put("result", "success");
		map.put("data", list);
		
		JSONObject jsonObject = JSONObject.fromObject(map);	//java object를 json object로 바꿔줌
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);
		
	}

}
