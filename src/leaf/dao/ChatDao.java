package leaf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import leaf.dto.ChatDto;
import test.dbcp.DbcpBean;

public class ChatDao {
	private static ChatDao instance = new ChatDao();
	private ChatDao() {}
	public static ChatDao getInstance(){
		return instance;
	}

	public int insertChat(String id, String msg){
		Connection conn= null;
		PreparedStatement pstmt = null;

		try{
			conn= DbcpBean.getConn();
			String sql = "insert into chat values (chat_seq.nextval,?,?,sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, msg);
			return pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally{
			DbcpBean.closeDB(conn, pstmt, null);
		}
	}
	public ArrayList<ChatDto> list() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DbcpBean.getConn();
			String sql = "select * from chat order by no asc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			ArrayList<ChatDto> list = new ArrayList<>();
			while (rs.next()){
				ChatDto dto = new ChatDto(rs.getInt("no"), rs.getString("id"), rs.getString("msg"), rs.getDate("time"));
				list.add(dto);
			}
			return list;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		} finally{
			DbcpBean.closeDB(conn, pstmt, rs);
		}
	}
	
	public int lastlist(){ 	//채팅창 제일 나중 번호
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DbcpBean.getConn();
			String sql = "select max(no) from chat";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			return rs.getInt("no");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally{
			DbcpBean.closeDB(conn, pstmt, rs);
		}
	}
}
