package main;
import java.sql.*;
public class MySQLConnect {
	static Connection conn = null;
	public static Connection getConnect(String x) {
		Connection conn = null;                    
		
		// ket noi
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			String url = "jdbc:mysql://localhost/"+x;
			String user = "root";
			String pass = "vominhtuyen";
		
		conn = DriverManager.getConnection(url, user, pass);
			System.out.println("Thành công");
		} 
		
		catch (Exception ex) { 
			System.out.println("Không thành công: "+ " " + ex.getMessage());
			ex.printStackTrace();
		}
		return conn;
		////////////////
	}
	
	public static void closeConnection() throws SQLException {
		if(conn != null)
		{
			conn.close();
		}
	}
	

}
