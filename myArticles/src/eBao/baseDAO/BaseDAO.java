package eBao.baseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BaseDAO {
	// Get database connection by DBCP
	public Connection getConnection() {
		Connection conn = null;
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context
					.lookup("java:comp/env/jdbc/eBaoTech");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	// Get database connection by connection string
	public Connection getConnection2() throws ClassNotFoundException,
			SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String url = "jdbc:oracle:thin://@localhost:1521:orcl";
		String user = "yudi_liu";
		String pwd = "123";
		Connection conn = DriverManager.getConnection(url, user, pwd);
		return conn;
	}

	public void closeConnection(ResultSet rs, PreparedStatement ps,
			Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
