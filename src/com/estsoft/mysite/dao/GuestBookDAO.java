package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.GuestBookVO;

public class GuestBookDAO {
private DBConnection dbConnection;
	
	public GuestBookDAO(DBConnection dbConnection){
		this.dbConnection = dbConnection;
	}
	
	public int delete(int no, String password){
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = -1;
		
		try{
			conn = dbConnection.getConnection();
			
			String sql = "DELETE FROM guestbook WHERE no=? AND passwd = password(?)";
			pstmt = conn.prepareStatement(sql);
			
			if(no==0 || password==null){
				System.out.println("번호나 패스워드 값이 잘 못 들어왔습니다.");
			}
			
			pstmt.setLong(1, no);
			/*pstmt.setLong(1, Long.parseLong(no));*/
			pstmt.setString(2, password);
			
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
	
	public GuestBookVO get(Long no){
		GuestBookVO vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dbConnection.getConnection();
			
			String sql = "SELECT no, name, reg_date, message, passwd FROM guestbook WHERE no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			if(rs.next()){
				Long No = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);
				String passwd = rs.getString(5);
				vo = new GuestBookVO(No, name, reg_date, message, passwd);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if( rs != null) 	rs.close();
				if (pstmt != null)	pstmt.close();
				if (conn != null)	conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	// long값 return하게..
	public Long insert(GuestBookVO vo){
		Long no = 0L;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbConnection.getConnection();
			
			String sql = "INSERT INTO guestbook	VALUES(null,?,now(),?,password(?))";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMessage());
			pstmt.setString(3, vo.getPasswd());
			pstmt.executeUpdate();
			
			//------------------------------------
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT LAST_INSERT_ID()");
			if(rs.next()){
				no = rs.getLong(1);
			}
			return no;
		} catch(SQLException ex){
			System.out.println("error:"+ex);
			ex.printStackTrace();
			return 0L;
		} finally{
			try{
				if(rs != null)	rs.close();
				if(pstmt != null)	pstmt.close();
				if(conn != null)	conn.close();
			}catch(SQLException ex){
				ex.printStackTrace();
			}
		}
	}
	
	public List<GuestBookVO> getList(){
		List<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			
			String sql = "SELECT no,name,DATE_FORMAT(reg_date,'%Y-%m-%d %h:%i:%s'),message from guestbook "+
							"ORDER BY reg_date desc";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);
				
				GuestBookVO vo = new GuestBookVO(no, name, reg_date, message);
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
	//오버로딩
	public List<GuestBookVO> getList(int page){
		List<GuestBookVO> list = new ArrayList<GuestBookVO>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT no,name,DATE_FORMAT(reg_date,'%Y-%m-%d %h:%i:%s'),message from guestbook " +
						"ORDER BY reg_date desc LIMIT " + (page-1)*5 +",5";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()){
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);
				
				GuestBookVO vo = new GuestBookVO(no, name, reg_date, message);
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
