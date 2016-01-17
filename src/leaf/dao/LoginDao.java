package leaf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import test.dbcp.DbcpBean;
import test.dto.CommentsDto;

public class LoginDao {
	private static LoginDao instance = new LoginDao();
	private LoginDao() {	}
	public static LoginDao getInstance(){
		return instance;
	}
	
	public int login(String id, String pwd){ 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn=DbcpBean.getConn();
			String sql = "select * from users where id=? and pwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return 1;
			}else{
				return -1;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		} finally{
			DbcpBean.closeDB(conn, pstmt, rs);
		}
	}
}
