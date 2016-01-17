package leaf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
                                                                                                                                                                                                                                                                                                                                                                                         
import leaf.dao.ChatDao;
import leaf.dto.ChatDto;

@WebServlet("/chat")
public class ChatController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		if(cmd.equals("write")){
			insertchat(request,response);
			//list(request,response);
		}else if(cmd.equals("list")){
			list(request,response);
		}
	}
	private void insertchat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 세션 객체 얻어오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String msg = request.getParameter("msg");
		ChatDao dao = ChatDao.getInstance();
		dao.insertChat(id, msg);
		//System.out.println(id+msg);
	}
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ChatDao dao = ChatDao.getInstance();
		ArrayList<ChatDto> list = dao.list();
		JSONArray chats = new JSONArray();
		for (ChatDto dto : list){
			JSONObject json = new JSONObject();
			json.put("no", dto.getNo());
			json.put("id", dto.getId());
			json.put("msg", dto.getMsg());
			//json.put("time", dto.getTime().toString());
			chats.add(json);
		}
		JSONObject result = new JSONObject();
		result.put("chats", chats);
		response.setContentType("text/plain;charset=utf-8");
		PrintWriter pw = response.getWriter();
		//System.out.println(result+"잘되나");
		pw.write(result.toString());
		pw.close();
	}
}
