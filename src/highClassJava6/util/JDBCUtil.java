package highClassJava6.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * JDBC드라이버를 로딩하고 Connection객체를 생성 및 사용한 객체반납하는 기능 제공함.
 */
public class JDBCUtil {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); ///classObject를 가져올때 사용
			System.out.println("드라이버 로딩 완료!");
		}catch(ClassNotFoundException ex) {
			System.out.println("드라이버 로딩 실패!!!");
		}
	}
	
public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ddit","java");
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	/*
	 * 자원반납
	 */
public static void close(Connection conn, 
						Statement stmt,
						PreparedStatement pstmt,
						ResultSet rs) {
	if (rs != null) try{rs.close();}catch(SQLException ex) {}
	if (stmt != null) try{rs.close();}catch(SQLException ex) {}
	if (pstmt != null) try{rs.close();}catch(SQLException ex) {}
	if (conn != null) try{rs.close();}catch(SQLException ex) {}
		
	}
}


