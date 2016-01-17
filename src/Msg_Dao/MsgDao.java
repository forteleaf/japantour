package Msg_Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Msg_Dto.MsgDto;
import test.dbcp.DbcpBean;
import test.util.JdbcUtil;

public class MsgDao {
	public int sendMsg(MsgDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="insert into msg values(msg_seq.nextval,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getToid());
			pstmt.setString(3, dto.getTitle());
			pstmt.setString(4, dto.getContents());
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return 0;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	public ArrayList<MsgDto> msglist(String id, int startrow, int endrow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from (select aa.*, rownum rnum from (select * from msg order by msgnum desc)aa) where rnum>=? and rnum<=? and toid=?";
			pstmt=con.prepareStatement(sql);			
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			pstmt.setString(3, id);
			rs=pstmt.executeQuery();
			ArrayList<MsgDto> list=new ArrayList<>();
			while(rs.next()){
				MsgDto dto=new MsgDto(rs.getInt("msgnum"),rs.getString("id"), rs.getString("toid"), rs.getString("title"), rs.getString("contents"), rs.getString("regdate"));
				list.add(dto);
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	public int getCount(String id){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(count(msgnum),0) from msg where toid=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			rs.next();
			int max=rs.getInt(1);
			return max;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			try{
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			}catch(SQLException se){
				System.out.println(se.getMessage());
			}
		}
	}
	public MsgDto msgread(int msgnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from msg where msgnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, msgnum);
			rs=pstmt.executeQuery();
			if(rs.next()){
				MsgDto dto=new MsgDto(rs.getInt("msgnum"), rs.getString("id"), rs.getString("toid"), rs.getString("title"), rs.getString("contents"), rs.getString("regdate"));
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
	public int msgdelete(int msgnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="delete from msg where msgnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, msgnum);
			return pstmt.executeUpdate();
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return 0;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
}
