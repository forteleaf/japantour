package test.dbcp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbcpBean {
	static private DataSource ds;
	
	static{ // static멤버를 초기화 할때는 static블록으로 초기화 한다.
		Context initContext;
		try {
			initContext = new InitialContext();
			Context envContext  = (Context)initContext.lookup("java:/comp/env");
			ds = (DataSource)envContext.lookup("jdbc/myoracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static Connection getConn() throws SQLException{
		Connection con = ds.getConnection();
		return con;
	}
	public static void closeDB(Connection conn, PreparedStatement pstmt, ResultSet rs){
		try{
			if(rs!=null)rs.close();
			if(pstmt!=null)pstmt.close();
			if(rs!=null)conn.close();
			
		} catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
