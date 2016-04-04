package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.BoardVO;

public class BoardDAO {
	private DBConnection dbConnection;

	public BoardDAO(DBConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public void updateCount(Long no) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = dbConnection.getConnection();
			
			String sql = "UPDATE board SET viewCount = viewCount+1 WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.executeUpdate();
			
		}catch(SQLException ex){
			System.out.println("error: "+ex);
		}finally { 
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public int delete(int no){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try{
			conn = dbConnection.getConnection();
	
			String sql = "DELETE FROM board WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			
			if(no==0){
				System.out.println("번호가 잘 못 들어왔습니다.");
			}
			
			pstmt.setLong(1, no);
			
			cnt = pstmt.executeUpdate();	// 1 이상 성공
			
		} catch(SQLException ex){
			System.out.println("error:"+ex);
			ex.printStackTrace();
		} finally{
			try{
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		return cnt;
	}
	
	
	public void insert(BoardVO vo){		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try{
			conn = dbConnection.getConnection();
			
			 // 새 글인 경우	
			String sql = "insert into board values(null,?,?,now(),0, "+
						"(select ifnull(max(group_no),0)+1 from board as b),1,0,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			System.out.println("Board Insert"+vo.getTitle()+":"+vo.getContent()+":"+vo.getUser_no());
			
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getUser_no());
			
			pstmt.executeUpdate();
			
		} catch(SQLException ex){
			System.out.println("error:"+ex);
			ex.printStackTrace();
		} finally{
			try{
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	
	public BoardVO get(Long no){	//게시글 번호
		BoardVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbConnection.getConnection();			
			String sql = "select title, content from board WHERE no = ?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				String title = rs.getString(1);
				String content = rs.getString(2);
				
				vo = new BoardVO(title, content);
			}
			
		} catch(SQLException ex){
			System.out.println("error:"+ex);
		} finally{
			try{
				if(rs != null)		rs.close();
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public List<BoardVO> getList(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT b.no, b.title, b.content, u.no as user_no, u.name, b.viewCount, DATE_FORMAT(b.reg_date,'%Y-%m-%d %h:%i:%s') "+
						"from board b, user u where b.user_no = u.no "+
						"ORDER BY b.group_no DESC, b.order_no ASC";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String title = rs.getString(2);
				String content = rs.getString(3);
				Long user_no = rs.getLong(4);
				String user_name = rs.getString(5);
				Long viewCount = rs.getLong(6);
				String reg_date = rs.getString(7);				
				
				BoardVO vo = new BoardVO(no, title, content, user_no, user_name, viewCount, reg_date);
				list.add(vo);
			}
			
		} catch(SQLException ex){
			System.out.println("error:"+ex);
		} finally{
			try{
				if(rs != null)		rs.close();
				if(stmt != null)	stmt.close();
				if(conn != null)	conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
		
		return list;
	}

}
