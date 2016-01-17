package japanintro_controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import japanintro_dao.JapanIntroDao;
import japanintro_dto.JapanIntroDto;
@WebServlet("/intro")
public class JapanIntroController extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("insert")){
			insert(request,response);
			
		}else if (cmd.equals("list")) {
			list(request,response);
			
		}else if (cmd.equals("delete")){
			delete(request,response);
			
		}else if (cmd.equals("getInfo")) {
			getInfo(request,response);
			
		}else if (cmd.equals("updateOk")) {
			updateOk(request,response);
			
		}else if (cmd.equals("update")) {
			update(request,response);
		}else if (cmd.equals("alist")) {
			alist(request,response);
		}
	}

	private void insert(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String content=request.getParameter("content");	
		int localnum=Integer.parseInt(request.getParameter("localnum"));
		JapanIntroDto dto=new JapanIntroDto(0, id, title, content, null, localnum); 		
		JapanIntroDao dao=new JapanIntroDao();
		int n=dao.insert(dto);
		String result="fail";
		if(n>0){
			result="success";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("layout.jsp?page=/japanintro/J_result.jsp").forward(request, response);
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		int pageNum=1;
		String spageNum=request.getParameter("pageNum");
		if(spageNum!=null){
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		JapanIntroDao dao=new JapanIntroDao();
		ArrayList<JapanIntroDto> list=dao.list(startRow, endRow);
		
		int pageCount=(int)Math.ceil((dao.getCount()/10.0));
		int startPageNum=((pageNum-1)/10*10)+1;
		int endPageNum=startPageNum+9;
		if(pageCount<endPageNum){
			endPageNum=pageCount;
		}
		
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("list", list);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.getRequestDispatcher("layout.jsp?page=japanintro/J_list.jsp").forward(request, response);
					
	}
	private void delete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		JapanIntroDao dao=new JapanIntroDao();
		int n=dao.delete(num);
		request.setAttribute("n", n);
		request.getRequestDispatcher("layout.jsp?page=/japanintro/J_delete.jsp").forward(request, response);	
	}
	private void alist(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		int localnum=Integer.parseInt(request.getParameter("localnum"));
		JapanIntroDao dao=new JapanIntroDao();
		ArrayList<JapanIntroDto> alist=dao.alist(localnum);
		
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<alist>");
		for(JapanIntroDto dto:alist){
			pw.println("<num>" + dto.getNum() + "</num>");
			pw.print("<title><![CDATA[" + dto.getTitle() + "]]></title>");
			pw.print("<content><![CDATA[" + dto.getContent().toString() + "]]></content>");
			pw.print("<time>" + dto.getTime().toString()+ "</time>");
			pw.println("<localnum>" + dto.getLocalnum() + "</localnum>");
		}
		pw.print("</alist>");
		pw.close();
	}
	private void getInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		JapanIntroDao dao=new JapanIntroDao();
		JapanIntroDto dto=dao.getInfo(num);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("layout.jsp?page=/japanintro/J_getInfo.jsp").forward(request, response);
		
		
	}
	private void update(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		JapanIntroDao dao=new JapanIntroDao();
		JapanIntroDto dto=dao.update(num);
		request.setAttribute("dto", dto);
		request.getRequestDispatcher("layout.jsp?page=/japanintro/J_update.jsp").forward(request, response);
		
		
	}
	private void updateOk(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num=Integer.parseInt(request.getParameter("num"));
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int localnum=Integer.parseInt(request.getParameter("localnum"));
		JapanIntroDto dto=new JapanIntroDto(num, null, title, content, null, localnum);
		JapanIntroDao dao=new JapanIntroDao();
		int n=dao.updateOk(dto);
		if(n>0){
			request.setAttribute("result", "success");
			
		}else {
			request.setAttribute("result", "fail");

		}
		RequestDispatcher rd=request.getRequestDispatcher("layout.jsp?page=japanintro/J_updateOk.jsp");
		rd.forward(request, response);
	}
}

