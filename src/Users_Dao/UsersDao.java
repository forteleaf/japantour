package Users_Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import Users_Dto.UsersDto;
import test.dbcp.DbcpBean;
import test.util.JdbcUtil;

public class UsersDao {
	public int login(String id, String pwd){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from users where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return 1;
			}else{
				return 0;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public int insert(UsersDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		try{
			con=DbcpBean.getConn();
			String sql="insert into users values(?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getBirth());
			pstmt.setString(5, dto.getEmail());
			int n=pstmt.executeUpdate();
			String sql2="insert into profile values(profile_seq.nextval,?,null,null,null,0)";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setString(1, dto.getId());
			pstmt2.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	public int idcheck(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from users where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return 1;
			}else{
				return 0;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public HashMap<String, String> searchid(String name){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from users where name=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			HashMap<String, String> map=new HashMap<>();
			if(rs.next()){
				map.put("id", rs.getString("id"));
				map.put("name", rs.getString("name"));
				return map;
			}else{
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public HashMap<String, String> searchpwd(String id, String name, String birth){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from users where id=? and name=? and birth=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, birth);
			rs=pstmt.executeQuery();
			HashMap<String, String> map=new HashMap<>();
			if(rs.next()){
				map.put("name", rs.getString("name"));
				map.put("pwd", rs.getString("pwd"));
				map.put("email", rs.getString("email"));
				return map;
			}else{
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public HashMap<String, String> mypage(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			
			String sql="select * from users u, profile p where u.id=p.id(+) and u.id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				HashMap<String, String> map=new HashMap<>();
				map.put("id", rs.getString("id"));
				map.put("comments", rs.getString("comments"));
				map.put("savefilename", rs.getString("savefilename"));
				map.put("pwd", rs.getString("pwd"));
				map.put("name", rs.getString("name"));
				map.put("birth", rs.getString("birth"));
				map.put("email", rs.getString("email"));
				return map;
			}else{
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public UsersDto pwdcheck(String pwd){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from users where pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				UsersDto dto=new UsersDto(rs.getString("id"), rs.getString("pwd"), rs.getString("name"), rs.getString("birth"), rs.getString("email"));
				return dto;
			}else{
				return null;
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public int usersupdate(UsersDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="update users set pwd=?, name=?, email=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getPwd());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getId());
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return 0;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
}
