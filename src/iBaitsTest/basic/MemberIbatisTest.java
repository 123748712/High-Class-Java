package iBaitsTest.basic;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.SQLException;

import javax.annotation.Resources;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import MVCTest.member.vo.MemberVO;

public class MemberIbatisTest {
	public static void main(String[] args) {

		// iBatis를 이용하여 DB데이터를 처리하는 작업 순서
		// 1. iBatis의 환경설정파일을 읽어와 실핸시킨다.
		
		try {
			// 1-1. xml문서 읽어오기
			// 설정파일의 인코딩 설정
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			Reader rd = Resources.getResourceAsReader("SqlMapConfig.xml");
			
			// 1-2. 위에서 읽어온 Reader객체를 이용하여 실제 작업을 진행할 객체 생성하기
			SqlMapClient smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			rd.close(); // 스트림 객체 닫기
			
			// 2. 실행할 SQL문에 맞는 쿼리문을 호출해서 원하는 작업을 수행한다.
			// 2-1. insert작업 연습
			System.out.println("insert작업 시작...");
			
			// 1) 저장할 데이터를 VO에 넣는다.
			MemberVO mv = new MemberVO();
			mv.setMemId("d001");
			mv.setMemName("강감찬");
			mv.setMemTel("010-2222-3333");
			mv.setMemAddr("경남 김해시");
			
			// 2) SqlMapClient객체 변수를 이용하여 해당 쿼리문을 실행한다.
			// 형식) smc.insert("namespace값.id값", 파라미터클래스객체) // 반환값 : 성공하면 'null' 반환
			Object obj;
				obj = smc.insert("memberTest.insertMember", mv);
			
			if(obj == null) {
				System.out.println("insert 작업 성공.");
			} else {
				System.out.println("insert 작업 실패.");
			}
			System.out.println("============================");
			
		} catch (IOException e) {
		} catch (SQLException e) {
		}
	}
}
