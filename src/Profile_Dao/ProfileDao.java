package Profile_Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import Profile_Dto.ProfileDto;
import test.dbcp.DbcpBean;
import test.util.JdbcUtil;

public class ProfileDao {
	public ProfileDto profile(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from profile where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				ProfileDto dto=new ProfileDto(rs.getInt("pnum"), id, 
												rs.getString("comments"), 
												rs.getString("orgfilename"),
												rs.getString("savefilename"),
												rs.getInt("filesize"));
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
	public int p_pupdate(ProfileDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="update profile set orgfilename=?, savefilename=?, filesize=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getOrgfilename());
			pstmt.setString(2, dto.getSavefilename());
			pstmt.setLong(3, dto.getFilesize());
			pstmt.setString(4, dto.getId());
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return 0;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	public int c_update(HashMap<String, String> map){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="update profile set comments=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, map.get("comments"));
			pstmt.setString(2, map.get("id"));			
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return 0;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
}
