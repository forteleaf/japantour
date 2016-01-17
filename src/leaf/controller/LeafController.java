package leaf.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import leaf.dao.LoginDao;

// user 메인페이지에서 user이동에 대한 페이지
@WebServlet("/user")
public class LeafController extends HttpServlet{
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd.equals("login")){
			login(request,response);
		}else if (cmd.equals("logout")){
			logout(request,response);
		}
	}
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		LoginDao dao= LoginDao.getInstance();
		response.setContentType("text/xml;charset=utf-8");
		JSONObject json = new JSONObject();
		PrintWriter pw = response.getWriter();
		int n = dao.login(id,pwd);
		if(n>0){
			json.put("result","success");
			HttpSession session = request.getSession();
			session.setAttribute("id",id);
		}else{
			json.put("result","fail");
		}
		pw.print(json.toString());
		pw.close();
	}
	private void logout (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("main.jsp");
	}
}
