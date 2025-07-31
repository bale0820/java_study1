package comon;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {
	String url = "jdbc:mysql://127.0.0.1:3306/hrdb2019";
	String user = "root";
	String password = "mysql1234";
	Connection connection;
	
	
	
	public DBConn() {
		try {
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("연결 성공!!");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
