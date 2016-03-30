package com.estsoft.mysite.dao;

import java.sql.*;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.UserVO;

public class UserDAO {
	private DBConnection dbConnection;
	
	public UserDAO(DBConnection dbConnection){
		this.dbConnection = dbConnection;
	}
	
	// method는 테이블에 CRUD하는 이름으로 지어주는 게 좋다.
	
	public void insert(UserVO vo){
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = dbConnection.getConnection();
			String sql = "INSERT INTO user VALUES(null,?,?,password(?),?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("error:"+e);
		} finally{
			try{
				if(pstmt!=null)		pstmt.close();
				if(conn!=null)		conn.close();	
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	} 
}
