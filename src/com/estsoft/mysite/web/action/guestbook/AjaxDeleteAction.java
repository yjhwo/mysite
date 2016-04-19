package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDAO;
import com.estsoft.web.action.Action;

import net.sf.json.JSONObject;

public class AjaxDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// a=ajax-delete&no="+no+"&passwd="+password
		int no = Integer.parseInt(request.getParameter("no"));
		String passwd = request.getParameter("passwd");
				
		GuestBookDAO dao = new GuestBookDAO(new MySQLWebDBConnection());
		int cnt = dao.delete(no, passwd);
		
		// 성공한 경우
		Map<String, Object> map = new HashMap<String,Object>();
		
		if(cnt < 1){	// 실패한 경우
			map.put("result", "fail");
		}else{
			map.put("result", "success");
			
		}
		
		JSONObject jsonObject = JSONObject.fromObject(map);	//java object를 json object로 바꿔줌
		response.setContentType("application/json; charset=utf-8");
		response.getWriter().print(jsonObject);
		
		
	
	}

}
