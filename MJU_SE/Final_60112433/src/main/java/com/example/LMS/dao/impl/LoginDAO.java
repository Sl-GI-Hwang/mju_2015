package com.example.LMS.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.stereotype.Repository;

import com.example.LMS.model.LoginInfo;
import com.example.LMS.model.UserInfo;

@Repository
public class LoginDAO{

	public static UserInfo login(LoginInfo logininfo){
		// TODO Auto-generated method stub
		/* 로그인하고 user객체 반환
		 * 로그인 실패하면 thow exception 
		 */
		UserInfo user = null;
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
			String dbUser = "revenge";
			String dbPassword = "123";
			
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			
			stmt = conn.prepareStatement("select * from user where id = ?");
			stmt.setString(1,  logininfo.getID());
			rs = stmt.executeQuery();
			
			if(rs.next() == false){
				user = new UserInfo();
				user.setUid(0);
				user.setId("");
				user.setName("");
				user.setPassword("");
				user.setCategory("");
				user.setLogin(0);
			}else{
				if(rs.getString("password").equals(logininfo.getPassword())){
					user = new UserInfo();
					user.setUid(rs.getInt("uid"));
					user.setId(rs.getString("id"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setCategory(rs.getString("category"));
					user.setLogin(2);
				}else{
					user = new UserInfo();			
					user.setUid(0);
					user.setId("");
					user.setName("");
					user.setPassword("");
					user.setCategory("");
					user.setLogin(1);
				}
					
			}
			rs.close();
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e){
			
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (rs != null) try{rs.close();} catch(SQLException e) {}
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}
		return user;	
	}

	public void registForLMS(String userID, String userName, String userPassword, String category) {
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int result;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String dbUrl = "jdbc:mysql://localhost:3306/mjulms";
			String dbUser = "revenge";
			String dbPassword = "123";
			
			conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			
			stmt = conn.prepareStatement("insert into user(id, name, password, category) values(?, ?, ? ,?)");
			stmt.setString(1, userID);
			stmt.setString(2, userName);
			stmt.setString(3, userPassword);
			stmt.setString(4, category);
			
			result = stmt.executeUpdate();
			
			stmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e){
			
		} finally {
			// 무슨 일이 있어도 리소스를 제대로 종료
			if (stmt != null) try{stmt.close();} catch(SQLException e) {}
			if (conn != null) try{conn.close();} catch(SQLException e) {}
		}	
	}

}
