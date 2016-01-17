package leaf.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import test.dbcp.DbcpBean;

// 최근 글 항목을 가져오는 목록
@WebServlet("/recentBoard")
public class recentController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/xml;charset=utf-8");
		String cmd = request.getParameter("board");
		if(cmd.equals("japaninfo")){
			recentJapaninfo(request,response);
		}else if (cmd.equals("report")){
			recentReport(request,response);
		}else if(cmd.equals("japanintro")){
			recentJapanintro(request,response);
		}
	}
	// japaninfo에 대한 최근 글을 올리기. 
	private void recentJapanintro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DbcpBean.getConn();
			String sql = "select a.*,rownum from (select * from japanintro order by num desc)a where rownum<6";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while (rs.next()){
				int num = rs.getInt("num");
				String title = rs.getString("title");
				JSONObject json = new JSONObject();
				json.put("num", num);
				json.put("title", title);
				arr.add(json);
			}
			JSONObject result = new JSONObject();
			result.put("recentintro", arr);
			PrintWriter pw = response.getWriter();
			//System.out.println("recentreport 를 보내라");
			pw.print(result.toString());
			pw.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			DbcpBean.closeDB(conn, pstmt, rs);
		}
	}
	private void recentJapaninfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbcpBean.getConn();
			// 전체 조인을 해서 결과값을 가지고 오려는 시도
			//String sql = "select a.*,rownum from (select * from japaninfo order by inum desc) a where rownum<=5";
			//select it.num,it.title a,if.inum,if.title b,ir.rnum,ir.title c from japanintro it,japaninfo if, report ir order by it.num desc, if.inum desc, jr.rnum desc
			//String sql = "select * from (select it.num,it.title a,if.inum,if.title b,jr.rnum,jr.title c from japanintro it,japaninfo if, report jr order by it.num desc, if.inum desc, jr.rnum desc ) a where rownum <= 7";
			String sql = "select a.*,rownum from (select * from japaninfo order by inum desc)a where rownum<6";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while (rs.next()){
				int inum = rs.getInt("inum");
				String title = rs.getString("title");
				//int localnum = rs.getInt("localnum");
				JSONObject json = new JSONObject();
				json.put("inum", inum);
				json.put("title", title);
				arr.add(json);
			}
			JSONObject result = new JSONObject();
			result.put("recentInfo", arr);
			PrintWriter pw = response.getWriter();
			//System.out.println("recentjapaninfo 를 보내라");
			pw.print(result.toString());
			pw.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			DbcpBean.closeDB(conn, pstmt, rs);
		}
	}
	// 후기의 최근 글에 대한 json 보내기
	private void recentReport(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DbcpBean.getConn();
			String sql = "select a.*,rownum from (select * from report order by rnum desc)a where rownum<6";
			pstmt= conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			JSONArray arr = new JSONArray();
			while (rs.next()){
				int rnum = rs.getInt("rnum");
				String title = rs.getString("title");
				//int localnum = rs.getInt("localnum");
				JSONObject json = new JSONObject();
				json.put("rnum", rnum);
				json.put("title", title);
				arr.add(json);
			}
			JSONObject result = new JSONObject();
			result.put("recentInfo", arr);
			PrintWriter pw = response.getWriter();
//			System.out.println("recentreport 를 보내라");
			pw.print(result.toString());
			pw.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally{
			DbcpBean.closeDB(conn, pstmt, rs);
		}
	}
}
