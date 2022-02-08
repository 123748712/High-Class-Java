package MVCTest.member.dao;

import java.sql.Connection;
import java.util.List;

import MVCTest.member.vo.MemberVO;

// 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성한 후 서비스에 전달하는 DAO의 인터페이스
public interface IMemberDao {

	// MemberVO에 담겨진 자료를 db에 insert하는 메서드
	// conn => 커넥션 객체
	// mv => DB에 INSERT할 자료가 저장된 MemberVO객체
	// return DB작업이 성공하면 1이상의 값, 실패하면 0
	public int insertMember(Connection conn, MemberVO mv);

	// 주어진 회원 ID를 이용하여 존재여부를 알아내는 메서드
	// conn => 커넥션 객체
	// memId => 검색할 회원ID
	// return => 해당 회원 아이디가 있으면 true, 없으면 false
	public boolean checkMember(Connection conn, String memId);

	// DB의 mymember테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메서드
	// conn => 커넥션 객체
	// MemberVO객체를 담고 있는 List객체
	public List<MemberVO> getAllMemberList(Connection conn);

	// 하나의 MemberVO객체 데이터를 이용하여 DB를 update하는 메서드
	// conn => 커넥션 객체
	// mv => update할 회원정보가 들어있는 memberVO객체
	// return => 작업성공 : 1, 작업실패 : 0
	public int updateMember(Connection conn, MemberVO mv);

	// 회원ID를 매개변수로 받아서 그 회원 정보를 삭제하는 메서드
	// conn => 커넥션 객체
	// memId => 삭제할 회원ID
	// return => 작업성공 : 1, 작업실패 : 0
	public int deleteMember(Connection conn, String memId);

	// MemberVO에 담긴 자료를 이용하여 회원을 검색하는 메서드
	// conn => 커넥션 객체
	// mv => 검색할 자료가 들어있는 MemberVO 객체
	// return => 검색 결과를 담은 List 객체
	public List<MemberVO> searchMember(Connection conn, MemberVO mv);
}

