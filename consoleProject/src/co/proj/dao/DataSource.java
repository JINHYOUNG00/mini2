package co.proj.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataSource {
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private static DataSource dataSource = new DataSource();

	private DataSource() {
	};
	
	public static DataSource getInstance() {
		return dataSource;
	}

	public Connection getConn() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, "mini2", "1234");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
