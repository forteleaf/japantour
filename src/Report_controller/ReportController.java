package Report_controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.descriptor.web.MultipartDef;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.sun.xml.internal.txw2.Document;

import Report_dao.ReportDao;
import Report_dto.ReportDto;
import Report_dto.Report_reDto;



@WebServlet("/report")
public class ReportController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("write")){
			response.sendRedirect("layout.jsp?page=report/write.jsp");
		}else if(cmd.equals("writeOk")){
			insert(request,response);
		}else if(cmd.equals("list")){
			listAll(request,response);
		}else if(cmd.equals("detail")){
			detail(request,response);
		}else if(cmd.equals("count")){
			count(request,response);
		}else if(cmd.equals("delete")){
			delete(request,response);
		}else if(cmd.equals("update1")){
			update1(request,response);
		}else if(cmd.equals("update")){
			update(request,response);
		}else if(cmd.equals("list1")){
			list_re(request,response);
		}else if(cmd.equals("insert_re")){
			insert_re(request,response);
		}else if(cmd.equals("delete1")){
			delete_re(request,response);	
		}else if(cmd.equals("select")){
			select(request,response);
		}
	}
	
	
	//-------------------------------------★답 글 파 트★  -------------------------------------------------------------//
	
	//▶답글삭제
	protected void delete_re(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int renum=Integer.parseInt(request.getParameter("renum"));
		ReportDao dao=new ReportDao();
		int n=dao.delete_re(renum);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n>0){
			pw.println("<info>success</info>");
		}else{
			pw.println("<info>fail</info>");
		}
		pw.println("</result>");
		pw.close();
	}
	
	//▶답글추가
	protected void insert_re(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rnum=Integer.parseInt(request.getParameter("rnum"));
		String id=request.getParameter("id");
		String comments=request.getParameter("comments");
		Report_reDto dto=new Report_reDto(0,rnum,id, null,comments, 0, 0, 0);
		ReportDao dao=new ReportDao();
		int n=dao.insert_re(dto);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n>0){
			pw.println("<info>success</info>");
		}else{
			pw.println("<info>fail</info>");
		}
		pw.println("</result>");
		pw.close();
	}
	
	//▶답글 목록
	protected void list_re(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ReportDao dao=new ReportDao();
		int rnum=Integer.parseInt(request.getParameter("rnum"));
		ArrayList<Report_reDto> list1=dao.list_re(rnum);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<list>");
		for(Report_reDto dto:list1){
			pw.print("<renum>" + dto.getRenum() +"</renum>");
			pw.print("<id>" + dto.getId() +"</id>");
			pw.print("<comments>" + dto.getComments() +"</comments>");
		}
		pw.println("</list>");
		pw.close();
	}

	//------------------------------------------------------------------------------------------------------------------//


	//-------------------------------------★후 기 파 트★  -------------------------------------------------------------//

	//▶후기 수정
	protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext upapp=request.getSession().getServletContext();
		String path = upapp.getRealPath("/upload"); 
		System.out.println(path);
		MultipartRequest mq=new MultipartRequest( 
				request, 
				path, 
				1024 * 1024 * 5, 
				"utf-8", 
				new DefaultFileRenamePolicy() 
		);
		int rnum=Integer.parseInt(mq.getParameter("rnum"));
		String id=mq.getParameter("id");
		String title=mq.getParameter("title");	
		String content=mq.getParameter("content");	
		int localnum=Integer.parseInt(mq.getParameter("localnum"));
		String orgfilename=mq.getOriginalFileName("fileupload");		
		String savefilename=mq.getFilesystemName("fileupload");		
		File fe = new File(path + "\\" + savefilename);		
		long filesize = fe.length();		
		ReportDao dao=new ReportDao();
		ReportDto dto=new ReportDto(rnum,id,title,content,null,localnum,0,orgfilename,savefilename,filesize);
		int n=dao.update(dto);
		if(n>0){
			request.setAttribute("update", "success");

		}else{
			request.setAttribute("update", "fail");
		}
		RequestDispatcher rd=request.getRequestDispatcher("layout.jsp?page=report/updateOk.jsp");
		rd.forward(request, response);

	}
	protected void update1(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rnum=Integer.parseInt(request.getParameter("rnum"));
		ReportDao dao=new ReportDao();
		ReportDto dto=dao.detail(rnum);
		request.setAttribute("dto",dto);
		request.getRequestDispatcher("layout.jsp?page=report/update1.jsp").forward(request,response);
	}
	
	protected void select(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int localnum=Integer.parseInt(request.getParameter("localnum"));
		ReportDao dao= new ReportDao();
		ArrayList<ReportDto> list=dao.select(localnum);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.println("<select>");
		for(ReportDto dto:list){
			pw.println("<rnum>" + dto.getRnum() + "</rnum>");
			if(dto.getLocalnum() == 1){
				pw.println("<localname>홋카이도</localname>");
			}else if(dto.getLocalnum() == 2){
				pw.println("<localname>혼슈</localname>");
			}else if(dto.getLocalnum() == 3){
				pw.println("<localname>시코쿠</localname>");
			}else if(dto.getLocalnum() == 4){
				pw.println("<localname>큐슈</localname>");
			}
			
			pw.println("<localnum>" + dto.getLocalnum() + "</localnum>");
			pw.println("<title>" + dto.getTitle() + "</title>");
			pw.println("<id>" + dto.getId() + "</id>");
			pw.println("<time>" + dto.getTime() + "</time>");
			pw.println("<likecnt>" + dto.getLikecnt() + "</likecnt>");
		}
		pw.println("</select>");
		pw.close();		
	}
	//▶후기 좋아요
	protected void count(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rnum=Integer.parseInt(request.getParameter("rnum"));	
		ReportDao dao=new ReportDao();	
		ReportDto dto=new ReportDto(rnum,null,null,null,null,0,0,null,null,0);
		int n=dao.count(dto);
		response.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=response.getWriter();
		pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");
		if(n>0){
			pw.println("<info>success</info>");
		}else{
			pw.println("<info>fail</info>");
		}
		pw.println("</result>");
		pw.close();
	}
	
	//▶ 후기 삭제
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rnum=Integer.parseInt(request.getParameter("rnum"));
		ReportDao dao=new ReportDao();
		int  n=dao.delete(rnum);
		if(n>0){
			request.setAttribute("delete", "success");
		}else{
			request.setAttribute("delete", "fail");
		}
		RequestDispatcher rd=request.getRequestDispatcher("layout.jsp?page=report/delete.jsp");
		rd.forward(request,response);
	}
	
	//▶후기 상세보기
	protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int rnum=Integer.parseInt(request.getParameter("rnum"));
		ReportDao dao=new ReportDao();
		ReportDto dto=dao.detail(rnum);
		request.setAttribute("dto",dto);
		request.getRequestDispatcher("layout.jsp?page=report/detail.jsp").forward(request,response);
	}
	protected void listAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageNum=1;	
		String pageNum1=request.getParameter("pageNum");
		if(pageNum1!=null){
			pageNum=Integer.parseInt(pageNum1);
		}
		int startRow=(pageNum-1)*10+1;
		int endRow=startRow+9;
		ReportDao dao=new ReportDao();
		ArrayList<ReportDto> list=dao.listAll(startRow,endRow);
		int pageCount=(int)(Math.ceil(dao.getCount()/10.0));
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
		request.getRequestDispatcher("layout.jsp?page=report/list.jsp").forward(request, response);	
	}

	//▶후기 삽입
	protected void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext app=request.getSession().getServletContext();
		String path = app.getRealPath("/upload"); 
		System.out.println(path);
		MultipartRequest mr=new MultipartRequest( 
				request, 
				path, 
				1024 * 1024 * 5, 
				"utf-8", 
				new DefaultFileRenamePolicy() 
		);	
		String id=mr.getParameter("id");		
		String title=mr.getParameter("title");		
		String content=mr.getParameter("content");		
		int localnum=Integer.parseInt(mr.getParameter("localnum"));		
		String orgfilename=mr.getOriginalFileName("fileupload");		
		String savefilename=mr.getFilesystemName("fileupload");		
		File f = new File(path + "\\" + savefilename);		
		long filesize = f.length();		
		ReportDto dto=new ReportDto(0,id,title,content,null,localnum,0,orgfilename,savefilename,filesize);	
		ReportDao dao=new ReportDao();	
		String result="fail";
		int n=dao.insert(dto);
		if(n>0){
			result="success";
		}
		request.setAttribute("result", result);
		request.getRequestDispatcher("layout.jsp?page=report/result.jsp").forward(request,response);

	}
}
