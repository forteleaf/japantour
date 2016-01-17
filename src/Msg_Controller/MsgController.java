package Msg_Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Msg_Dao.MsgDao;
import Msg_Dto.MsgDto;

@WebServlet("/msgcontroller")
public class MsgController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String cmd=req.getParameter("cmd");
		if(cmd.equals("sendmsg")){
			sendmsg(req,resp);
		}else if(cmd.equals("msglist")){
			msglist(req,resp);
		}else if(cmd.equals("msgread")){
			msgread(req,resp);
		}else if(cmd.equals("msgdelete")){
			msgdelete(req,resp);
		}
	}
	private void sendmsg(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String id=req.getParameter("id");
		String toid=req.getParameter("toid");
		String title=req.getParameter("title");
		String contents=req.getParameter("contents");
		MsgDto dto=new MsgDto(0,id,toid,title,contents,null);
		int n=new MsgDao().sendMsg(dto);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");		
		if(n>0){
			pw.print("<info>success</info>");
		}else{
			pw.print("<info>fail</info>");
		}
		pw.println("</result>");
		pw.close();		
	}
	private void msglist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		int pageNum=1;
		String spageNum=req.getParameter("pageNum");
		if(spageNum!=null){
			pageNum=Integer.parseInt(spageNum);
		}
		int startRow=(pageNum-1)*5+1;
		int endRow=startRow+4;
		
		MsgDao dao=new MsgDao();
		ArrayList<MsgDto> list=dao.msglist(id,startRow,endRow);		
		int pageCount=(int)Math.ceil(dao.getCount(id)/5.0);
		int startPageNum=(pageNum-1)/5*5+1;
		int endPageNum=startPageNum+4;
		if(pageCount<endPageNum){
			endPageNum=pageCount;
		}
		req.setAttribute("pageCount", pageCount);
		req.setAttribute("list", list);
		req.setAttribute("startPageNum", startPageNum);
		req.setAttribute("endPageNum", endPageNum);
		req.setAttribute("pageNum", pageNum);
		req.getRequestDispatcher("layout.jsp?page=msg/msglist.jsp").forward(req, resp);
	}
	private void msgread(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int msgnum=Integer.parseInt(req.getParameter("msgnum"));
		MsgDto dto=new MsgDao().msgread(msgnum);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");		
		if(dto!=null){
			pw.print("<info>success</info>");
			pw.print("<contents>"+dto.getContents()+"</contents>");
		}else{
			pw.print("<info>fial</info>");
		}
		pw.println("</result>");
		pw.close();			
	}
	private void msgdelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int msgnum=Integer.parseInt(req.getParameter("msgnum"));
		int n=new MsgDao().msgdelete(msgnum);
		resp.setContentType("text/xml;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		pw.print("<result>");		
		if(n>0){
			pw.print("<info1>success</info1>");
		}else{
			pw.print("<info1>fial</info1>");
		}
		pw.println("</result>");
		pw.close();		
	}
}
