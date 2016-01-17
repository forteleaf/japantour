package Profile_Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Profile_Dao.ProfileDao;
import Profile_Dto.ProfileDto;
import Users_Dao.UsersDao;
import Users_Dto.UsersDto;

@WebServlet("/profilecontroller")
public class ProfileController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd.equals("mypage")){
			mypage(req,resp);
		}else if(cmd.equals("cupdate")){
			cupdate(req,resp);
		}else if(cmd.equals("passwordok")){
			resp.sendRedirect("layout.jsp?page=/profile/passwordOk.jsp");
		}else if(cmd.equals("usersprofile")){
			usersprofile(req,resp);
		}else if(cmd.equals("usersupdate")){
			usersupdate(req,resp);
		}else if(cmd.equals("usersupdateok")){
			usersupdateok(req,resp);		
		}
	}
	private void mypage (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		HashMap<String, String> map=new UsersDao().mypage(id);
		req.setAttribute("map",map);		
		req.getRequestDispatcher("layout.jsp?page=/profile/mypage.jsp").forward(req, resp);
	}
	private void cupdate (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String comments=req.getParameter("comments");
		HashMap<String, String> map=new HashMap<>();
		map.put("id", id);
		map.put("comments", comments);
		int n=new ProfileDao().c_update(map);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n>0){
			pw.print("<info>success</info>");			
		}else{
			pw.print("<info>fial...</info>");			
		}		
		pw.println("</result>");
		pw.close();
	}
	private void usersprofile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		ProfileDto dto=new ProfileDao().profile(id);		
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");		
		if(dto!=null){
			pw.print("<info>success</info>");
			pw.print("<savefilename>"+dto.getSavefilename()+"</savefilename>");
			pw.print("<id>"+ dto.getId() +"</id>");
			pw.print("<comments>"+dto.getComments()+"</comments>");
		}else{
			pw.print("<info>fail</info>");
		}
		pw.println("</result>");
		pw.close();			
	}	
	private void usersupdate (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pwd=req.getParameter("pwd");
		UsersDto dto=new UsersDao().pwdcheck(pwd);
		req.setAttribute("dto",dto);
		RequestDispatcher rd=req.getRequestDispatcher("layout.jsp?page=profile/usersupdate.jsp");
		rd.forward(req, resp);
	}
	private void usersupdateok (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String name=req.getParameter("name");
		String birth=req.getParameter("birth");
		String email=req.getParameter("email");
		UsersDto dto=new UsersDto(id,pwd,name,birth,email);
		int n=new UsersDao().usersupdate(dto);
		if(n>0){
			resp.sendRedirect("/profilecontroller?cmd=mypage");
		}
	}	
}
