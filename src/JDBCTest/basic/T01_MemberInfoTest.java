package JDBCTest.basic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import MVCTest.util.JDBCUtil;
import MVCTest.util.JDBCUtil3;

/*
	회원정보를 관리하는 프로그램을 작성하는데 
	아래의 메뉴를 모두 구현하시오. (CRUD기능 구현하기)
	(DB의 MYMEMBER테이블을 이용하여 작업한다.)
	
	* 자료 삭제는 회원ID를 입력 받아서 삭제한다.
	
	예시메뉴)
	----------------------
		== 작업 선택 ==
		1. 자료 입력			---> insert
		2. 자료 삭제			---> delete
		3. 자료 수정			---> update
		4. 전체 자료 출력	---> select
		5. 작업 끝.
	----------------------
	 
	   
// 회원관리 프로그램 테이블 생성 스크립트 
create table mymember(
    mem_id varchar2(8) not null,  -- 회원ID
    mem_name varchar2(100) not null, -- 이름
    mem_tel varchar2(50) not null, -- 전화번호
    mem_addr varchar2(128),    -- 주소
    CONSTRAINT MYMEMBER_PK PRIMARY KEY (mem_id)
);

*/
public class T01_MemberInfoTest {
	
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	private Scanner scan = new Scanner(System.in); 
	
	/**
	 * 메뉴를 출력하는 메서드
	 */
	public void displayMenu(){
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 작 업 선 택 ===");
		System.out.println("  1. 자료 입력");
		System.out.println("  2. 자료 삭제");
		System.out.println("  3. 자료 수정");
		System.out.println("  4. 전체 자료 출력");
		System.out.println("  5. 작업 끝.");
		System.out.println("----------------------");
		System.out.print("원하는 작업 선택 >> ");
	}
	
	/**
	 * 프로그램 시작메서드
	 */
	public void start(){
		
		int choice;
		do{//do{} while()문: do를 먼저 실행하고 while문의 조건식이 true이면 중괄호 내부를 반복 실행한다
			displayMenu(); //메뉴 출력
			choice = scan.nextInt(); // 메뉴번호 입력받기
			switch(choice){
				case 1 :  // 자료 입력
					insertMember();
					break;
				case 2 :  // 자료 삭제
					deleteMember();
					break;
				case 3 :  // 자료 수정
					updateMember();
					break;
				case 4 :  // 전체 자료 출력
					displayMemberAll();
					break;
				case 5 :  // 작업 끝
					System.out.println("작업을 마칩니다.");
					break;
				default :
					System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}
		}while(choice!=5);
	}
	
	/*
	 * 회원정보를 삭제하는 메서드
	 * (입력받은 회원ID를 이용하여 삭제한다.)
	 */
	private void deleteMember() {
		
		System.out.println();
		System.out.println("삭제할 회원 정보를 입력하세요.");
		System.out.print("회원ID >> ");		
		
		String memId = scan.next();
		
		try {
			conn = JDBCUtil.getConnection();//1.connection연결
			
			String sql = " delete from mymember "
						+" where mem_id =? ";
			
			pstmt = conn.prepareStatement(sql);//2. statement로 쿼리 만들기 / pstmt는 SQL을 미리 준비해 ?연산자를 통해 반복되는 SQL처리
			pstmt.setString(1, memId);//preparedstatement는 ?를 replace하는 작업을 해줌
			
			int cnt = pstmt.executeUpdate();//3.executeupdate()는 int으로 리턴
			if (cnt > 0) {//insert, delete, update는 레코드 건수만큼 반환  / create, drop은 -1로 반환
				System.out.println(memId + "회원 정보를 삭제했습니다.");
			}else {
				System.out.println(memId + "회원정보 삭제 실패!!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {//4.자원반납
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 회원정보를 수정하는 메서드
	 */
	private void updateMember() {
		
		boolean chk = false;
		
		String memId;
		
		do {
			System.out.println();
			System.out.println("수정할 회원 정보를 입력하세요.");
			System.out.print("회원ID >> ");
			
			memId = scan.next();
			
			chk = checkMember(memId);
			
			if (chk == false) {
				System.out.println("회원ID가" + memId + "인 회원은 이미 존재하지 않습니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(chk == false);
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원의 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.println("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			String sql = " update mymember " + 
					" set mem_name = ? " + 
					"    ,mem_tel= ? " + 
					"    ,mem_addr = ? " + 
					" where mem_id= ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memName);
			pstmt.setString(2, memTel);
			pstmt.setString(3, memAddr);
			pstmt.setString(4, memId);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(memId + " 회원의 정보를 수정했습니다.");
			}else {
				System.out.println(memId + "회원의 정보를 수정실패!!!");
			}
			
		}catch(SQLException ex){
			ex.printStackTrace();
		} finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 전체 회원을 출력하는 메서드
	 */
	private void displayMemberAll() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("ID\t이 름\t전화번호\t\t주소 ");
		System.out.println("-------------------------------------------------");
		
		try {
			conn = JDBCUtil3.getConnection();
			
			String sql = "select * from mymember";///날릴쿼리 지정
			
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				String memId = rs.getString("mem_Id");
				String memName = rs.getString("mem_name");
				String memTel = rs.getString("mem_tel");
				String memAddr = rs.getString("mem_addr");///resultset으로 데이터 꺼내오기
				
				System.out.println(memId + "\t" + memName + "\t" + memTel + "\t\t" + memAddr);
			}
			System.out.println("-------------------------------------------------");
			System.out.println("출력 작업 끝.");
		} catch (SQLException ex) {
			System.out.println("회원자료 가져오기 실패.");
			ex.printStackTrace();
		}finally {
			JDBCUtil3.close(conn, stmt, pstmt, rs);
		}
	}

	/*
	 * 회원정보 추가하기
	 */
	private void insertMember() {
		
		boolean chk = false;
		
		String memId;
		
		do {
			System.out.println();
			System.out.println("추가할 회원 정보를 입력하세요.");
			System.out.print("회원ID >> ");
			
			memId = scan.next();
			
			chk = checkMember(memId);
			System.out.println("chk : " + chk);
			
			if (chk == true) {
				System.out.println("회원ID가" + memId + "인 회원은 이미 존재합니다.");
				System.out.println("다시 입력하세요.");
			}
		}while(chk == true);
		
		System.out.println("회원 이름 >> ");
		String memName = scan.next();
		
		System.out.println("회원의 전화번호 >> ");
		String memTel = scan.next();
		
		scan.nextLine(); //입력버퍼 비우기
		
		System.out.println("회원 주소 >> ");
		String memAddr = scan.nextLine();
		
		try {
			conn = JDBCUtil.getConnection();
			
			///SQL2번
			String sql = "INSERT INTO mymember ("
				    + "mem_id,mem_name,mem_tel,mem_addr"
				+") VALUES (?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);
			pstmt.setString(2, memName);
			pstmt.setString(3, memTel);
			pstmt.setString(4, memAddr);
			
			int cnt = pstmt.executeUpdate();
			
			if (cnt > 0) {
				System.out.println(memId + "회원 추가 성공");
			}else {
				System.out.println(memId + "회원 추가 실패!!!");
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
	}
	
	/*
	 * 회원ID를 이용하여 회원이 존재하는지 확인하는 메서드
	 * @param memId
	 * @return true : 존재함, false: 존재하지 않음.
	 */
	private boolean checkMember(String memId) {
		boolean chk = false;
		
		try {
			conn = JDBCUtil.getConnection();
			
			///SQL 1번
			String sql = " select count(*) as cnt "  
						+" from mymember "
						+ " where mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);///?를 1위치에 memId를 넣는다.
			
			rs = pstmt.executeQuery();
			
			int cnt = 0;
			
			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}
			
			if (cnt > 0) {
				chk = true; // 존재함.
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}finally {
			JDBCUtil.close(conn, stmt, pstmt, rs);
		}
		return chk;
	}

	public static void main(String[] args) {
		T01_MemberInfoTest memObj = new T01_MemberInfoTest();
		memObj.start();
	}

}






