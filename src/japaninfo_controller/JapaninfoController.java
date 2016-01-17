package japaninfo_controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import japaninfo_dao.Japaninfo_dao;
import japaninfo_dto.Japaninfo_dto;
import japaninfo_dto.Japaninfo_re_dto;

@WebServlet("/japaninfo")
public class JapaninfoController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request,
				HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd=request.getParameter("cmd");
		if(cmd.equals("write")){
			HttpSession session= request.getSession();
			String id=(String)session.getAttribute("id");
			if(id==null){
				response.sendRedirect("layout.jsp");
			}else if(id.equals("admin")){
				response.sendRedirect("layout.jsp?page=/japaninfo/write.jsp");
			}else{
				request.setAttribute("err","�����ڷ� �α����ϼ���.");
				RequestDispatcher rd=request.getRequestDispatcher("layout.jsp?page=japaninfo/error.jsp");
				rd.forward(request, response);
			}
		}else if(cmd.equals("writeOk")){
			insert(request,response);
		}else if(cmd.equals("list")){
			list(request,response);
		}else if(cmd.equals("view")){
			view(request,response);
		}else if(cmd.equals("update")){
			update(request,response);
		}else if(cmd.equals("updateOk")){
			updateOk(request,response);
		}else if(cmd.equals("delete")){
			delete(request,response);
		}else if(cmd.equals("write_re")){
			insert_re(request,response);
		}else if(cmd.equals("delete_re")){
			delete_re(request,response);
		}else if(cmd.equals("loginForm")){
			response.sendRedirect("/japaninfo/login.jsp");
		}else if(cmd.equals("login")){
			login(request,response);
		}else if(cmd.equals("logout")){
			logout(request,response);
		}else if(cmd.equals("recommend")){
			recommend(request,response);
		}
	}
	//�α����ϱ�
	private void login(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id");
		String pwd=request.getParameter("pwd");		
		HashMap<String,String> map=new HashMap<>();
		map.put("id",id);
		map.put("pwd",pwd);		
		//LoginDao�� �̿��ؼ� ȸ�������ƴ��� �˻��� ������.
		Japaninfo_dao dao=Japaninfo_dao.getInstance();	
		int n=dao.isMember(map);
		if(n==1){//ȸ���ΰ��
			//���ǰ�ü ������
			HttpSession session=request.getSession();
			//���ǿ� ���̵���
			session.setAttribute("id",id);
			response.sendRedirect("/main.jsp");
		}else if(n==0){
			request.setAttribute("errMsg","���̵� �Ǵ� ��й�ȣ�� Ʋ����!");
			RequestDispatcher rd=request.getRequestDispatcher("/japaninfo/login.jsp");
			rd.forward(request, response);
		}else {
			request.setAttribute("err","������ ���� �۾��� ������� ���߽��ϴ�.");
			RequestDispatcher rd=request.getRequestDispatcher("/japaninfo/error.jsp");
			rd.forward(request, response);
		}
	}
	//�α׾ƿ�
	private void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect("/main.jsp");
	}
	
	//���� �����ʿ��� ���� �޼ҵ�
	private void insert(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("layout.jsp?page=japaninfo/login.jsp");
		}else if(id.equals("admin")){
			//String path=application.getRealPath("/upload");
			//�� �ڵ�� �ڹ� ��ũ��Ʈ������ ���� �ִ�. application�� servlet���� ����Ϸ��� �ؿ��� ���� ���� �ȴ�.
			ServletContext app=request.getSession().getServletContext();
			String path = app.getRealPath("/infoupload");
			MultipartRequest mr=new MultipartRequest(
					request,//request��ü
					path, //���ε�� ������ ������ ���
					1024*1024*5,//�ִ���ε�ũ��(����Ʈ����)->5MB
					"utf-8", //���ڵ����
					new DefaultFileRenamePolicy() //���ϸ��� 
					//�ߺ����� �ʵ���
			);
			String title=mr.getParameter("title");
			String content=mr.getParameter("content");
			int localnum=Integer.parseInt(mr.getParameter("localnum"));
			String orgfilename=mr.getOriginalFileName("fileup");
			String savefilename=mr.getFilesystemName("fileup");
			File f=new File(path +"\\" + savefilename);
			long filesize=f.length();		
			Japaninfo_dto dto=new Japaninfo_dto(0, id, title, content, null, localnum, 0, orgfilename, savefilename, filesize);
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			int n=dao.insert(dto);
			String result="fail";
			if(n>0){
				result="success";
			}
			request.setAttribute("result",result);
			int key=1;
			request.getRequestDispatcher("layout.jsp?page=/japaninfo/result.jsp?key="+key).forward(request, response);
		}else{
			request.setAttribute("err","�����ڷ� �α����ϼ���.");
			RequestDispatcher rd=request.getRequestDispatcher("/japaninfo/error.jsp");
			rd.forward(request, response);
		}
	}
	//��ü��� �����ִ� �޼ҵ�
	private void list(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("layout.jsp");
		}else{
			int pageNum=1;
			String spageNum=request.getParameter("pageNum");
			if(spageNum!=null){
				pageNum=Integer.parseInt(spageNum);
			}
			int startRow=(pageNum-1)*10+1;
			int endRow=startRow+9;
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			ArrayList<Japaninfo_dto> list = dao.list(startRow,endRow);
			int pageCount=(int)(Math.ceil(dao.getCount()/10.0));
			int startPageNum=((pageNum-1)/10*10)+1;
			int endPageNum=startPageNum+9;
			if(pageCount<endPageNum){
				endPageNum=pageCount;
			}
			request.setAttribute("pageCount", pageCount);
			request.setAttribute("list", list);
			request.setAttribute("startPageNum",startPageNum);
			request.setAttribute("endPageNum",endPageNum);
			request.setAttribute("pageNum",pageNum);
			request.getRequestDispatcher("layout.jsp?page=japaninfo/list.jsp").forward(request, response);
		}
	}
	//�� �󼼺���� ���� ��ü ����
	private void view(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			int pageNum_re=1;
			String spageNum_re=request.getParameter("pageNum_re");
			if(spageNum_re!=null){
				pageNum_re=Integer.parseInt(spageNum_re);
			}
			int startRow=(pageNum_re-1)*10+1;
			int endRow=startRow+9;
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			int inum=Integer.parseInt(request.getParameter("inum"));
			Japaninfo_dto dto=dao.view(inum);
			ArrayList<Japaninfo_re_dto> list_re=dao.list_re(inum,startRow,endRow);
			int pageCount_re=(int)(Math.ceil(dao.getCount_re()/10.0));
			int startPageNum_re=((pageNum_re-1)/10*10)+1;
			int endPageNum_re=startPageNum_re+9;
			if(pageCount_re<endPageNum_re){
				endPageNum_re=pageCount_re;
			}
			request.setAttribute("dto",dto);
			request.setAttribute("pageCount_re",pageCount_re);
			request.setAttribute("list_re",list_re);
			request.setAttribute("startPageNum_re",startPageNum_re);
			request.setAttribute("endPageNum_re",endPageNum_re);
			request.setAttribute("pageNum_re",pageNum_re);
			request.getRequestDispatcher("layout.jsp?page=/japaninfo/view.jsp").forward(request, response);
		
		} 		
	}
	//�����ϴ� ������ �Űܰ���
	private void update(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			int inum=Integer.parseInt(request.getParameter("inum"));
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			Japaninfo_dto dto=dao.view(inum);
			request.setAttribute("dto",dto);
			request.getRequestDispatcher("/japaninfo/update.jsp").forward(request, response);
		} 		
	}
	//�����ϱ�
	private void updateOk(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			ServletContext app=request.getSession().getServletContext();
			String path = app.getRealPath("/infoupload");
			MultipartRequest mr = new MultipartRequest(request, //request��ü
					path, //���ε�� ������ ������ ���
					1024 * 1024 * 5, //�ִ���ε�ũ��(����Ʈ����)->5MB
					"utf-8", //���ڵ����
					new DefaultFileRenamePolicy() //���ϸ��� 
			//�ߺ����� �ʵ���
			);
			int inum=Integer.parseInt(mr.getParameter("inum"));
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			Japaninfo_dto dto=dao.view(inum);
			String filename=dto.getSavefilename();
			File f=new File(path +"\\"+ filename);
			boolean s = f.delete();
			String title=mr.getParameter("title");
			String content=mr.getParameter("content");
			int localnum=Integer.parseInt(mr.getParameter("localnum"));
			String orgfilename=mr.getOriginalFileName("fileup");
			String savefilename=mr.getFilesystemName("fileup");
			File ff=new File(path +"\\"+ savefilename);
			long filesize=ff.length();
			Japaninfo_dto dto1=new Japaninfo_dto(inum, null, title, content, null, localnum, 0, orgfilename, savefilename, filesize);
			int n=dao.update(dto1);
			String result="fail";
			if(s==true || n>0){
				result="success";
			}
			request.setAttribute("result",result);
			int key=2;
			request.getRequestDispatcher("/japaninfo/result.jsp?key="+key).forward(request, response);
		}
	}//�����ϱ�
	private void delete(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			int inum=Integer.parseInt(request.getParameter("inum"));
			Japaninfo_dto dto=new Japaninfo_dto(inum, null, null, null, null, 0, 0, null, null, 0);
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			//����� ���ϸ� ������
			String filename=request.getParameter("savefilename");
			ServletContext app=request.getSession().getServletContext();
			String path = app.getRealPath("/infoupload");
			File f=new File(path +"\\" + filename);
			//������ ���ϻ���
			boolean s=f.delete();
			int n=dao.delete(dto);
			String result="fail";
			if(s==true || n>0){
				result="success";
			}
			request.setAttribute("result",result);
			int key=3;
			request.getRequestDispatcher("/japaninfo/result.jsp?key="+key).forward(request, response);
		}
	}
	//��õ�ϱ�
	private void recommend(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			int inum=Integer.parseInt(request.getParameter("inum"));
			Japaninfo_dto dto=new Japaninfo_dto(inum, null, null, null, null, 0, 0, null, null, 0);
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			int n=dao.recommend(dto);
			response.setContentType("text/xml;charset=utf-8");
			PrintWriter pw=response.getWriter();
			//xml�� �����ϱ�
			pw.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
			pw.println("<result>");
			if(n>0){
				pw.println("<info>success</info>");
			}else{
				pw.println("<info>fail</info>");
			}
			pw.println("</result>");
			pw.close();
		}
	}
	//---------------------------------------------------------------------------------------------
	//���⼭���� ���ÿ��� ���� �޼ҵ�
	//---------------------------------------------------------------------------------------------
	//���ôޱ�
	private void insert_re(HttpServletRequest request,
			HttpServletResponse response)throws ServletException, IOException{
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			int reinum=0;
			int inum=Integer.parseInt(request.getParameter("inum"));
			String comments=request.getParameter("comments");
			int ref=0;
			int lev=0;
			int step=0;
			String snum=request.getParameter("reinum");
			if(snum!=null && !snum.equals("")){
				reinum=Integer.parseInt(request.getParameter("reinum"));
				ref=Integer.parseInt(request.getParameter("ref"));
				lev=Integer.parseInt(request.getParameter("lev"));
				step=Integer.parseInt(request.getParameter("step"));
			}
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			Japaninfo_re_dto dto=new Japaninfo_re_dto(reinum,inum,id,comments,null,ref,lev,step);
			int n=dao.insert_re(dto);
			if(n>0){
				response.sendRedirect("/japaninfo?cmd=view&inum="+inum);
			}
		}
	}
	//���û����ϱ�
	private void delete_re(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//���޵� �Ķ���� ������
		HttpSession session= request.getSession();
		String id=(String)session.getAttribute("id");
		if(id==null){
			response.sendRedirect("/japaninfo/login.jsp");
		}else{
			int reinum=Integer.parseInt(request.getParameter("reinum"));
			int inum=Integer.parseInt(request.getParameter("inum"));
			Japaninfo_dao dao=Japaninfo_dao.getInstance();
			Japaninfo_re_dto dto=new Japaninfo_re_dto(reinum,0,null,null,null,0,0,0);
			//db�� ���۵� ������ �߰��ϱ�
			int n=dao.delete_re(dto);
			if(n>0){
				response.sendRedirect("/japaninfo?cmd=view&inum="+inum);
			}
			//http://localhost:8081/ajax03/comm?cmd=insert&id=song&comments=hello
		}
	}
}