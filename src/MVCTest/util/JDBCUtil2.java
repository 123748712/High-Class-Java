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

/*
 * JDBC드라이버를 로딩하고 Connection객체를 생성 및 사용한 객체반납하는 기능 제공함.
 */

// db.properties파일의 내용으로 DB정보를 설정하는 방법
// 방법 1) Properties객체 이용하기
public class JDBCUtil2 {
	static Properties prop; // 객체변수 선언

	static {
		try {
			prop = new Properties();

			// 파일 읽기를 수행할 FileInputStream객체 생성
			FileInputStream fis = new FileInputStream("res/db.properties");

			// Properties객체로 파일 내용 읽기
			prop.load(fis); // 파일 내용을 읽어와 속성이름 및 속성값으로 분류하여 Properties객체에 담아준다.
			
			Class.forName(prop.getProperty("driver")); /// classObject를 가져올때 사용
			System.out.println("드라이버 로딩 완료!");


		} catch (ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
		} catch (IOException e) {
			System.out.println("파일이 없거나 입출력 오루입니다.");
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("user"),prop.getProperty("password"));
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
