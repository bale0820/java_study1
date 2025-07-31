package comon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBConn {
	private String url =  "jdbc:mysql://127.0.0.1:3306/hrdb2019";
	private String user = "root";
	private String password = "mysql1234";
	
	
	protected Connection connection;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	public DBConn() {
		try {
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("연결 성공!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getPreparedStatement(String sql) {
		try {
		if( pstmt != null && !pstmt.isClosed()) {
			pstmt.close();
		}
		pstmt = connection.prepareStatement(sql);
		System.out.println("택시 생성!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void close() {
		try {
			if(rs != null && !rs.isClosed()) {
				rs.close();
			}
			if(pstmt != null && !pstmt.isClosed()) {
				pstmt.close();
			}
			if(connection != null && !connection.isClosed()) {
				connection.close();
			}
			
		}catch(Exception e) {
			e.printStackTrace();;
		}
	}
	
	
}
