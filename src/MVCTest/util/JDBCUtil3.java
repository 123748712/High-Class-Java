package MVCTest.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/*
 * JDBC드라이버를 로딩하고 Connection객체를 생성 및 사용한 객체반납하는 기능 제공함.
 */

// db.properties파일의 내용으로 DB정보를 설정하는 방법
// 방법 2) ResourceBundle객체 이용하기
public class JDBCUtil3 {
	static ResourceBundle bundle; // 객체변수 선언

	static {
		try {
			bundle = ResourceBundle.getBundle("db");

			Class.forName(bundle.getString("driver")); /// classObject를 가져올때 사용
			System.out.println("드라이버 로딩 완료!");

		} catch (ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(bundle.getString("url"), bundle.getString("user"),
					bundle.getString("password"));
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	/*
	 * 자원반납
	 */
	public static void close(Connection conn, Statement stmt, PreparedStatement pstmt, ResultSet rs) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (stmt != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (pstmt != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (conn != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}

	}
}
