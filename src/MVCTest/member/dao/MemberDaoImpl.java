package MVCTest.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import MVCTest.member.vo.MemberVO;
import MVCTest.util.JDBCUtil2;

public class MemberDaoImpl implements IMemberDao {
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	@Override
	public int insertMember(Connection conn, MemberVO mv) {
		int cnt = 0;
		try {
			String sql = "INSERT INTO mymember (" + "mem_id,mem_name,mem_tel,mem_addr" + ") VALUES (?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, mv.getMemId());
			pstmt.setString(2, mv.getMemName());
			pstmt.setString(3, mv.getMemTel());
			pstmt.setString(4, mv.getMemAddr());

			cnt = pstmt.executeUpdate();

		} catch (SQLException ex) {
			throw new RuntimeException("회원등록 작업중 예외발생", ex); // RuntimeException => 실행중 발생하는 예외. catch를 하지 않아도 된다는 가장 큰
																// 특징이 있다.
		} finally {
			JDBCUtil2.close(null, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public boolean checkMember(Connection conn, String memId) {

		boolean chk = false;

		try {
			String sql = " select count(*) as cnt " + " from mymember " + " where mem_id = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memId);/// ?를 1위치에 memId를 넣는다.

			rs = pstmt.executeQuery();

			int cnt = 0;

			if (rs.next()) {
				cnt = rs.getInt("cnt");
			}

			if (cnt > 0) {
				chk = true; // 존재함.
			}

		} catch (SQLException ex) {
			throw new RuntimeException("회원존재여부 체크중 예외발생", ex);
		} finally {
			JDBCUtil2.close(null, stmt, pstmt, rs);
		}

		return chk; // 중복이 있으면 true, 없으면 false
	}

	@Override
	public List<MemberVO> getAllMemberList(Connection conn) {
		List<MemberVO> memList = new ArrayList<>();

		try {
			String sql = "select * from mymember";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			MemberVO mv = null;

			while (rs.next()) {
				mv = new MemberVO();
				mv.setMemId(rs.getString("mem_id"));
				mv.setMemName(rs.getNString("mem_name"));
				mv.setMemTel(rs.getNString("mem_tel"));
				mv.setMemAddr(rs.getNString("mem_addr"));

				memList.add(mv);
			}

		} catch (SQLException e) {
			throw new RuntimeException("전체회원목록 조회중 예외발생", e);
		} finally {
			JDBCUtil2.close(null, stmt, pstmt, rs);
		}
		return memList;
	}

	@Override
	public int updateMember(Connection conn, MemberVO mv) {
		
		int cnt = 0;
		
		try {
			String sql = " UPDATE MYMEMBER "
						+ " SET MEM_NAME = ? "
						+ "    , MEM_TEL = ? "
						+ "    , MEM_ADDR = ? "
						+ " WHERE MEM_ID = ? ";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, mv.getMemName());
			pstmt.setString(2, mv.getMemTel());
			pstmt.setString(3, mv.getMemAddr());
			pstmt.setString(4, mv.getMemId());

			cnt = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("회원정보 수정중 예외발생.", e);
		} finally {
			JDBCUtil2.close(null, stmt, pstmt, rs);
		}
		return cnt;
	}

	@Override
	public int deleteMember(Connection conn, String memId) {
		
		int cnt = 0;
		try {
			String sql = "DELETE FROM MYMEMBER "
					+ " WHERE MEM_ID = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new RuntimeException("회원정보 삭제중 예외발생.", e);
		} finally {
			JDBCUtil2.close(null, stmt, pstmt, rs);
		}		
		return cnt;
	}

	@Override
	public List<MemberVO> searchMember(Connection conn, MemberVO mv) {
		return null;
	}
}
