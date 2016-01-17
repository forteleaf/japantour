package test.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcUtil {
	public static void closeDB(Connection conn){
		try {
			if(conn!=null)conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}
	public static void closeDB(PreparedStatement pstmt){
		try {
			if(pstmt!=null) pstmt.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void closeDB(ResultSet rs){
		try {
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	public static void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs){
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			if(conn!=null)conn.close();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}