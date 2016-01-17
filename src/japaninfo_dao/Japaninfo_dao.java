package japaninfo_dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import japaninfo_dto.Japaninfo_dto;
import japaninfo_dto.Japaninfo_re_dto;
import test.dbcp.DbcpBean;
import test.util.JdbcUtil;

public class Japaninfo_dao {
	private static Japaninfo_dao instance=new Japaninfo_dao();
	private Japaninfo_dao(){}
	public static Japaninfo_dao getInstance(){
		return instance;
	}
	//로그인하기
	public int isMember(HashMap<String,String> map){
		//Map담긴 정보 꺼내오기
		String id=map.get("id");
		String pwd=map.get("pwd");
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from users where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,id);
			pstmt.setString(2,pwd);
			rs=pstmt.executeQuery();
			if(rs.next()){
				return 1;//회원인경우
			}else{
				return 0;//아이디 또는 비밀번호가 틀린경우
			}
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1; //익셉션이 발생된 경우
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
	//전체 글의 갯수 구하기
	public int getCount(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(count(inum),0) from japaninfo";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum=rs.getInt(1);
			return maxNum;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	//글 입력하기
	public int insert(Japaninfo_dto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="insert into japaninfo values(japaninfo_seq.nextval,?,?,?,sysdate,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getLocalnum());
			pstmt.setInt(5, dto.getLikecnt());
			pstmt.setString(6, dto.getOrgfilename());
			pstmt.setString(7, dto.getSavefilename());
			pstmt.setLong(8, dto.getFilesize());
			int n= pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	//글 목록 보기
	public ArrayList<Japaninfo_dto> list(int startRow,int endRow){
		String sql="select * from(select aa.*,rownum rnum from(select * from japaninfo order by inum desc)aa)where rnum>=? and rnum<=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			rs=pstmt.executeQuery();
			ArrayList<Japaninfo_dto> list=new ArrayList<>();
			while(rs.next()){
				Japaninfo_dto dto=new Japaninfo_dto(
						rs.getInt("inum"),
						rs.getString("id"),
						rs.getString("title"),
						rs.getString("content"),
						rs.getDate("time"),
						rs.getInt("localnum"),
						rs.getInt("likecnt"),
						rs.getString("orgfilename"),
						rs.getString("savefilename"),
						rs.getLong("filesize"));
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
	//글 상세보기
	public Japaninfo_dto view(int num){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select * from japaninfo where inum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,num);
			rs=pstmt.executeQuery();
			if(rs.next()){
				Japaninfo_dto dto=new Japaninfo_dto(
						rs.getInt("inum"),
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
	//글 수정하기
	public int update(Japaninfo_dto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update japaninfo set title=?,content=?,localnum=?,orgfilename=?,savefilename=?,filesize=? where inum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getLocalnum());
			pstmt.setString(4, dto.getOrgfilename());
			pstmt.setString(5, dto.getSavefilename());
			pstmt.setLong(6, dto.getFilesize());
			pstmt.setInt(7, dto.getInum());
			int n= pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	//글 삭제하기
	public int delete(Japaninfo_dto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="delete from japaninfo where inum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getInum());
			int n= pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
	//추천하기
	public int recommend(Japaninfo_dto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="update japaninfo set likecnt=likecnt+1 where inum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getInum());
			int n= pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(pstmt);
			JdbcUtil.closeDB(con);
		}
	}
	//-------------------------------------------------------------------------------
	//여기서부터 리플메소드
	//------------------------------------------------------------------------------
	public int getMaxNum(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(max(reinum),0) from japaninfo_re";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum=rs.getInt(1);
			return maxNum;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	//전체 글의 갯수 구하기
	public int getCount_re(){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			String sql="select NVL(count(reinum),0) from japaninfo_re";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			rs.next();
			int maxNum=rs.getInt(1);
			return maxNum;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return -1;
		}finally {
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	//리플달기
	public int insert_re(Japaninfo_re_dto dto){
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt=null;
		int reinum=dto.getReinum();
		int ref=dto.getRef();
		int lev=dto.getLev();
		int step=dto.getStep();
		int maxNum=getMaxNum();//가장큰글번호
		try {
			con=DbcpBean.getConn();
			if(reinum==0){
				ref=maxNum+1;
			}else{
				String sql1="update japaninfo_re set step=step+1 where ref=? and step>?";
				pstmt1=con.prepareStatement(sql1);
				pstmt1.setInt(1, ref);
				pstmt1.setInt(2, step);
				pstmt1.executeUpdate();
				lev=lev+1;
				step=step+1;
			}
			String sql="insert into japaninfo_re values(?,?,?,?,sysdate,?,?,?)";
			pstmt=con.prepareStatement(sql);
			reinum=maxNum+1;
			pstmt.setInt(1, reinum);
			pstmt.setInt(2, dto.getInum());
			pstmt.setString(3, dto.getId());
			pstmt.setString(4, dto.getComments());
			pstmt.setInt(5, ref);
			pstmt.setInt(6, lev);
			pstmt.setInt(7, step);
			int n= pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(pstmt1);
			JdbcUtil.closeDB(con, pstmt,null);
		}
	}
	//리플보기
	public ArrayList<Japaninfo_re_dto> list_re(int num,int startRow,int endRow){
		String sql= "select * from( " +
				" select aa.*,rownum rnum from(" +
				"	   select * from japaninfo_re " +
				"	    order by ref desc, step asc, reinum asc)aa "+
				"	)where inum=? and rnum>=? and rnum<=?";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=DbcpBean.getConn();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setInt(2,startRow);
			pstmt.setInt(3,endRow);
			rs=pstmt.executeQuery();
			ArrayList<Japaninfo_re_dto> list_re=new ArrayList<>();
			while(rs.next()){
				Japaninfo_re_dto dto=new Japaninfo_re_dto(
						rs.getInt("reinum"),
						rs.getInt("inum"),
						rs.getString("id"),
						rs.getString("comments"),
						rs.getDate("regdate"),
						rs.getInt("ref"),
						rs.getInt("lev"),
						rs.getInt("step"));
				list_re.add(dto);
			}
			return list_re;
		}catch(SQLException se){
			System.out.println(se.getMessage());
			return null;
		}finally{
			JdbcUtil.closeDB(con, pstmt, rs);
		}
	}
	//리플삭제
	public int delete_re(Japaninfo_re_dto dto){
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=DbcpBean.getConn();
			String sql="delete from japaninfo_re where reinum=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, dto.getReinum());
			int n= pstmt.executeUpdate();
			return n;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}finally{
			JdbcUtil.closeDB(con, pstmt, null);
		}
	}
}
