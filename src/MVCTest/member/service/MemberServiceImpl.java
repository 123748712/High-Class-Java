package MVCTest.member.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import MVCTest.member.dao.IMemberDao;
import MVCTest.member.dao.MemberDaoImpl;
import MVCTest.member.vo.MemberVO;
import MVCTest.util.JDBCUtil2;


public class MemberServiceImpl implements IMemberService {

	// 사용할 DAO객체를 위한 객체변수 선언
	private IMemberDao memDao;
	// 커넥션 객체를 위한 객체변수 선언
	private Connection conn;

	public MemberServiceImpl() {
		memDao = new MemberDaoImpl();
	}

	@Override
	public int insertMember(MemberVO mv) {

		int cnt = 0;

		try {
			conn = JDBCUtil2.getConnection();
			conn.setAutoCommit(false); // 자동커밋 비활성화 (default => true)
			
			cnt = memDao.insertMember(conn, mv);
			
			// 관리자에게 베일발송 기능 호출
			
			// 여기까지 왔다면 예외는 발생하지 않았기 때문에 이때 커밋을 해준다.
			conn.commit(); // 커밋
			
		} catch (Exception e) {
			try {
				conn.rollback(); // 예외가 발생했으면 예외가 발생되기 전으로 롤백한다.
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			JDBCUtil2.close(conn, null, null, null); // connection 밖에 없기 때문에 나머지는 null 처리
		}
		                           
		return cnt;
	}

	@Override
	public boolean checkMember(String memId) {
		conn = JDBCUtil2.getConnection();
		return memDao.checkMember(conn, memId);
	}

	@Override
	public List<MemberVO> getAllMemberList() {
		conn = JDBCUtil2.getConnection();
		return memDao.getAllMemberList(conn);
	}

	@Override
	public int updateMember(MemberVO mv) {
		conn = JDBCUtil2.getConnection();
		return memDao.updateMember(conn, mv);
	}

	@Override
	public int deleteMember(String memId) {
		conn = JDBCUtil2.getConnection();
		return memDao.deleteMember(conn, memId);
	}
}