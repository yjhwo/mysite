package com.estsoft.mysite.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 호출 되는 순서 알아보기 위한 서블릿
// 생명주기는 tomcat이 관리
@WebServlet("/life")
public class LifeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map map;	// 이렇게 servlet변수를 사용하면 위험하다.
	
	public void init(ServletConfig config) throws ServletException {	// 첫 번째에만 init이 호출됨
		System.out.println("LifeServlet:init() called");
	}

	
	public void destroy() {	// 요청이 좀 뜸해지면 서버가 destroy를 호출한다.
		System.out.println("LifeServlet:destroy() called");
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeServlet:service() called");
		super.service(request, response);
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeServlet:doGet() called");
		
		// doGet method가 thread를 타고,
		// 사용자가 많아지면 동시에 사용할 수 있기 때문에.. 꼭 써야하면 Application 범위에 담아라.
		synchronized (map) {
			
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("LifeServlet:doPost() called");
		doGet(request, response);
	}

}
