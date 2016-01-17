package japanintro_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import japanintro_dto.JapanIntroDto;
import test.dbcp.DbcpBean;
import test.util.JdbcUtil;

public class JapanIntroDao {
	Connection con=null;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
		public int insert(JapanIntroDto dto){
			
			try {
				con=DbcpBean.getConn();
				String sql="insert into japanintro values(japanintro_seq.nextval,?,?,?,sysdate,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getTitle());
				pstmt.setString(3, dto.getContent());
				pstmt.setInt(4, dto.getLocalnum());
				int n=pstmt.executeUpdate();
				return n;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
				JdbcUtil.closeDB(con, pstmt, null);
				
			}
			
		}
		public int getCount() {
			try {
				con=DbcpBean.getConn();
				String sql="select NVL(count(num),0) from japanintro";
				pstmt=con.prepareStatement(sql);
				rs=pstmt.executeQuery();
				rs.next();
				int maxN=rs.getInt(1);
				return maxN;
						
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
				JdbcUtil.closeDB(con, pstmt, rs);
				
			}
			
		}
		public ArrayList<JapanIntroDto> alist(int localnum) {
			ArrayList<JapanIntroDto> alist=new ArrayList<>();
			try {
				con=DbcpBean.getConn();
				String sql="select * from japanintro where localnum=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, localnum);
				rs=pstmt.executeQuery();
				while(rs.next()){
					JapanIntroDto dto=new JapanIntroDto(
							rs.getInt("num"),
							rs.getString("id"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getDate("time"),
							rs.getInt("localnum")
						
							);
					
					alist.add(dto);
				}
				
				return alist;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				JdbcUtil.closeDB(con, pstmt, rs);
				
			}
			
		}
		public ArrayList<JapanIntroDto> list(int startRow,int endRow) {
			ArrayList<JapanIntroDto> list=new ArrayList<>();
			try {
				con=DbcpBean.getConn();
				String sql="select * from (select a.*, rownum rnum from (select * from japanintro order by num desc)a) where rnum>=? and rnum<=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);
				rs=pstmt.executeQuery();
				while (rs.next()) {
					JapanIntroDto dto=new JapanIntroDto(
							rs.getInt("num"),
							rs.getString("id"),	
							rs.getString("title"),
							rs.getString("content"),
							rs.getDate("time"),
							rs.getInt("localnum"));
					list.add(dto);
				}
				return list;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				JdbcUtil.closeDB(con, pstmt, rs);
				
			}	
		}
		public int delete(int num) {
			try {
				con=DbcpBean.getConn();
				String sql="delete from japanintro where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				int n=pstmt.executeUpdate();
				return n;
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
					JdbcUtil.closeDB(con, pstmt, null);
					
			}
		}
		public JapanIntroDto getInfo(int num) {
			try {
				con=DbcpBean.getConn();
				String sql="select * from japanintro where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				if(rs.next()){
					JapanIntroDto dto=new JapanIntroDto(
							rs.getInt("num"),
							rs.getString("id"),
							rs.getString("title"),
							rs.getString("content"),
							rs.getDate("time"),
							rs.getInt("localnum")
							);
					return dto;
				}else {
					return null;
				}
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				JdbcUtil.closeDB(con, pstmt, rs);
				
			}
			
		}
		public JapanIntroDto update(int num) {
			try {
				con=DbcpBean.getConn();
				String sql="select * from japanintro where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, num);
				rs=pstmt.executeQuery();
				rs.next();
				String title=rs.getString("title");
				String content=rs.getString("content");
				int localnum=rs.getInt("localnum");
				JapanIntroDto dto=new JapanIntroDto(num, null, title, content, null, localnum);
				
				return dto;
				
				
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return null;
			}finally {
				JdbcUtil.closeDB(con, pstmt, rs);
				
			}
		}
		public int updateOk(JapanIntroDto dto) {
			try {
				con=DbcpBean.getConn();
				String sql="update japanintro set title=?,content=?,localnum=? where num=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, dto.getTitle());
				pstmt.setString(2, dto.getContent());
				pstmt.setInt(3, dto.getLocalnum());
				pstmt.setInt(4, dto.getNum());
				int n=pstmt.executeUpdate();
				return n;
				
			} catch (SQLException se) {
				System.out.println(se.getMessage());
				return -1;
			}finally {
				JdbcUtil.closeDB(con, pstmt, null);
				
			}
			
		}
		
}
