package Users_Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Msg_Dao.MsgDao;
import Msg_Dto.MsgDto;
import Profile_Dao.ProfileDao;
import Profile_Dto.ProfileDto;
import Users_Dao.UsersDao;
import Users_Dto.UsersDto;
import Users_email.MailSender;

@WebServlet("/userscontroller")
public class UsersController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd.equals("login")){
			login(req,resp);
		}else if(cmd.equals("logout")){
			resp.sendRedirect("login/logout.jsp");
		}else if(cmd.equals("search")){
			search(req,resp);
		}else if(cmd.equals("searchOk")){
			searchok(req,resp);
		}else if(cmd.equals("insert")){
			resp.sendRedirect("layout.jsp?page=/usersinsert/insert.jsp");
		}else if(cmd.equals("insertok")){
			insert(req,resp);
		}else if(cmd.equals("idcheck")){
			idcheck(req,resp);		
		}
	}
	private void login (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		UsersDao dao=new UsersDao();
		int n=dao.login(id, pwd);
		if(n==1){
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			resp.sendRedirect("layout.jsp");
		}else if(n==0){
			req.setAttribute("errMsg","아이디 또는 비밀번호가 맞지 않습니다!");
			RequestDispatcher rd=req.getRequestDispatcher("layout.jsp?page=/login/login.jsp");
			rd.forward(req, resp);
		}else{
			req.setAttribute("errMsg","오류........!");
			RequestDispatcher rd=req.getRequestDispatcher("layout.jsp?page=/login/login.jsp");
			rd.forward(req, resp);
		}
	}
	private void search (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s=req.getParameter("s");
		if(s.equals("id")){
			resp.sendRedirect("layout.jsp?page=login/search.jsp?ss=id");
		}else{
			resp.sendRedirect("layout.jsp?page=login/search.jsp?ss=pwd");
		}
	}
	private void searchok (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String s=req.getParameter("ss");
		UsersDao dao=new UsersDao();
		if(s.equals("id")){
			String name=req.getParameter("name");
			HashMap<String, String> map=dao.searchid(name);			
			if(map==null){
				req.setAttribute("msg1", "이름을 다시 확인해 주세요");
				req.getRequestDispatcher("layout.jsp?page=/login/search.jsp&ss=id").forward(req, resp);
			}else{
				req.setAttribute("msg", "id");
				req.setAttribute("name", map.get("name"));
				req.setAttribute("id", map.get("id"));
				req.getRequestDispatcher("layout.jsp?page=/login/searchOk.jsp").forward(req, resp);
			}			
		}else if(s.equals("pwd")){
			String id=req.getParameter("id");
			String name=req.getParameter("name");
			String yy=req.getParameter("yy");
			String m=req.getParameter("mm");
			String d=req.getParameter("dd");
			String mm="";
			String dd="";
			if(m.length()==1){
				mm="0"+m;
			}else{
				mm=m;
			}
			if(d.length()==1){			
				dd="0"+d;
			}else{
				dd=d;
			}
			String birth=yy+"/"+ mm +"/"+ dd;
			HashMap<String, String> map=dao.searchpwd(id, name, birth);
			if(map==null){
				req.setAttribute("msg1", "정보를 정확히 입력해 주세요");
				req.getRequestDispatcher("layout.jsp?page=/login/search.jsp&ss=pwd").forward(req, resp);
			}else{
				new MailSender().MailSend(map.get("name"), map.get("pwd"), map.get("email"));
				req.setAttribute("msg", "pwd");
				req.setAttribute("name", map.get("name"));
				req.setAttribute("email", map.get("email"));
				req.getRequestDispatcher("layout.jsp?page=/login/searchOk.jsp").forward(req, resp);
			}			
		}
	}
	private void insert (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String name=req.getParameter("name");
		String yy=req.getParameter("yy");
		String m=req.getParameter("mm");
		String d=req.getParameter("dd");
		String mm="";
		String dd="";
		if(m.length()==1){
			mm="0"+m;
		}else{
			mm=m;
		}
		if(d.length()==1){			
			dd="0"+d;
		}else{
			dd=d;
		}
		String birth=yy+"/"+ mm +"/"+ dd;
		String email=req.getParameter("email");
		UsersDto dto=new UsersDto(id,pwd,name,birth,email);
		int n=new UsersDao().insert(dto);
		if(n>0){
			resp.sendRedirect("layout.jsp");
		}		
	}
	private void idcheck (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		UsersDao dao=new UsersDao();
		int n=dao.idcheck(id);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n==1){
			pw.print("<info>fail</info>");
		}else if(n==0){
			pw.print("<info>success</info>");
		}
		pw.println("</result>");
		pw.close();		
	}	
}
