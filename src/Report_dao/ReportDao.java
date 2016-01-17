package Report_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Report_dto.ReportDto;
import Report_dto.Report_reDto;
import test.dbcp.DbcpBean;
import test.util.JdbcUtil;

public class ReportDao {

	//-------------------------------------★댓 글 파 트★  -------------------------------------------------------------//	
	//▶댓글 숫자세기
	public int getCount_re(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(count(rnum),0) from report_re";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum_re=rs.getInt(1); 
			return maxNum_re;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}

	//▶댓글 최대넘버 얻기
	public int getMaxNum_re(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(max(rnum),0) from report_re";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum_re=rs.getInt(1); 
			return maxNum_re;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}

	//▶댓글 삭제
	public int delete_re(int renum){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="delete from Report_re where renum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, renum);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;	
		}finally{
			JdbcUtil.closeDB(pstmt);
			JdbcUtil.closeDB(con);
		}
	}

	//댓글 삽입
	public int insert_re(Report_reDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="insert into Report_re values(report_re_seq.nextval,?,?,?,'',?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getRnum());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getComments());
			pstmt.setInt(4, dto.getRef());
			pstmt.setInt(5, dto.getLev());
			pstmt.setInt(6, dto.getStep());
			int n=pstmt.executeUpdate();
			return n;	
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(pstmt);
			JdbcUtil.closeDB(con);
		}
	}
	
	//▶댓글 목록
	public ArrayList<Report_reDto> list_re(int rnum){
		Connection con= null;
		PreparedStatement pstmt=null;
		ResultSet rs= null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from report_re where rnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs=pstmt.executeQuery();
			ArrayList<Report_reDto> list1=new ArrayList<>();
			while(rs.next()){
				Report_reDto dto=new Report_reDto(
						rs.getInt("renum"),
						rs.getInt("rnum"),
						rs.getString("id"),
						rs.getDate("regdate"),
						rs.getString("comments"),
						rs.getInt("ref"),
						rs.getInt("lev"),
						rs.getInt("step"));
				list1.add(dto);
			}
			return list1;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt,null);
		}
	}
//------------------------------------------------------------------------------------------------------------------//

//-------------------------------------★후 기 파 트★  -------------------------------------------------------------//
	
	//▶후기 좋아요
	public int count(ReportDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="update report set likecnt=(likecnt+1) where rnum=?"; 
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getRnum());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt,null);
		}
	}
	
	//▶ 후기 수정
	public int update(ReportDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="update report set id=?,title=?,localnum=?,content=?,orgfilename=?,savefilename=?,filesize=? where rnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setInt(3, dto.getLocalnum());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getOrgfilename());
			pstmt.setString(6, dto.getSavefilename());
			pstmt.setLong(7, dto.getFilesize());
			pstmt.setInt(8, dto.getRnum());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt,null);

		}
	}

	//▶ 후기 삭제
	public int delete(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=DbcpBean.getConn();
			String sql="delete from report where rnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	public ArrayList<ReportDto> select(int localnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from report where localnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, localnum);
			rs=pstmt.executeQuery();
			ArrayList<ReportDto> list=new ArrayList<>();
			while(rs.next()){
			ReportDto dto=new ReportDto(	
					rs.getInt("rnum"),
					rs.getString("id"),
					rs.getString("title"),
					rs.getString("content"),
					rs.getDate("time"),
					rs.getInt("localnum"),
					rs.getInt("likecnt"),
					rs.getString("orgfilename"),
					rs.getString("savefilename"),
					rs.getLong("filesize")
					);
			list.add(dto);
			}
			return list;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}

	
	//▶후기 상세
	public ReportDto detail(int rnum){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from report where rnum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, rnum);
			rs=pstmt.executeQuery();
			if(rs.next()){
				ReportDto dto=new ReportDto(
						rs.getInt("rnum"),
						rs.getString("id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("time"),
						rs.getInt("localnum"),
						rs.getInt("likecnt"),
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

	//▶후기 리스트
	public ArrayList<ReportDto> listAll(int startRow, int endRow){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from(select aa.*,rownum rm from(select * from report order by rnum desc)aa) where rm >=? and rm <=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rs=pstmt.executeQuery();
			ArrayList<ReportDto> list=new ArrayList<>();
			while(rs.next()){
				ReportDto dto=new ReportDto(
						rs.getInt("rnum"),
						rs.getString("id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("time"),
						rs.getInt("localnum"),
						rs.getInt("likecnt"),
						rs.getString("orgfilename"),
						rs.getString("savefilename"),
						rs.getInt("filesize"));
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

	//▶후기 글수 가져오기
	public int getCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(count(rnum),0) from report";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum=rs.getInt(1); 
			return maxNum;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	
	//▶후기 최대숫자가져오기
	public int getMaxNum(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(max(rnum),0) from report";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum=rs.getInt(1); 
			return maxNum;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}

	//▶후기 입력
	public int insert(ReportDto dto){
		Connection con=null;
		PreparedStatement pstmt=null;	
		try{
			con=DbcpBean.getConn();
			String sql="insert into report values(report_seq.nextval,?,?,?,sysdate,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getLocalnum());
			pstmt.setInt(5, dto.getLikecnt());
			pstmt.setString(6, dto.getOrgfilename());
			pstmt.setString(7, dto.getSavefilename());
			pstmt.setLong(8, dto.getFilesize());
			int n=pstmt.executeUpdate();
			return n;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
}
