package hairshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import hairshop.Main;
import hairshop.dto.MemberDTO;

public class MemberDAO {
	
	public void login(Connection connection, MemberDTO loginMember) throws SQLException {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM MEMBER WHERE MID = ? AND MPW = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, loginMember.getMid());
			preparedStatement.setString(2, loginMember.getMpw());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				loginMember.setMno(resultSet.getInt("MNO"));
				loginMember.setMid(resultSet.getString("MID"));
				loginMember.setMpw(resultSet.getString("MPW"));
				loginMember.setMname(resultSet.getString("MNAME"));
				loginMember.setMphone(resultSet.getString("MPHONE"));
				loginMember.setMgrade(resultSet.getInt("MGRADE"));
				Main.loginMember = loginMember;

			} else {
				System.out.println("아이디, 비밀번호 입력오류");
			}

		} catch (SQLException e) {
			System.out.println("id,비밀번호 입력 오류");

		} finally {
			resultSet.close();
			preparedStatement.close();
		}

	} // login() end

	public void join(Connection connection, MemberDTO newMember) throws SQLException {//회원가입
		PreparedStatement preparedStatement = null;

		String idcheck = "SELECT MID FROM MEMBER WHERE MID = ?";
		preparedStatement = connection.prepareStatement(idcheck);
		preparedStatement.setString(1, newMember.getMid());
		ResultSet resultSetCheck = preparedStatement.executeQuery();
		if (resultSetCheck.next()) {
			System.out.println("아이디 중복 오류");
			
		

		resultSetCheck.close();
		preparedStatement.close();
		return;
		}
		String sql = "INSERT INTO MEMBER VALUES (MEMBER_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, newMember.getMid());
			preparedStatement.setString(2, newMember.getMpw());
			preparedStatement.setString(3, newMember.getMname());
			preparedStatement.setString(4, newMember.getMphone());
			preparedStatement.setInt(5, newMember.getMgrade());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개의 값이 추가됨.");
				System.out.println(newMember.getMid() + "님 회원가입을 축하드립니다.");
				
				preparedStatement.close();
				
				
			} else {
				System.out.println("회원가입 실패");
			}

		} catch (SQLException e) {
			System.out.println("회원가입 실패");

		} 
		

	}//method end

	public void modPhone(Connection connection,String modPhone) {//전화번호 수정

		String sql = "UPDATE MEMBER SET MPHONE = ? WHERE MID = ? AND MPW = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modPhone);
			preparedStatement.setString(2, Main.loginMember.getMid());
			preparedStatement.setString(3, Main.loginMember.getMpw());
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println("결과 : " + result);
				System.out.println("수정 완료 !");
			}
			preparedStatement.close();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}

	}

	public void modPw(Connection connection, String modPw) { //비번수정
		String sql = "UPDATE MEMBER SET MPW = ? WHERE MID = ? AND MPW = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, modPw);
			preparedStatement.setString(2, Main.loginMember.getMid());
			preparedStatement.setString(3, Main.loginMember.getMpw());
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println("결과 : " + result);
				System.out.println("수정 완료 !");
			}
			preparedStatement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	}

	public void delete(Connection connection) {//회원탈퇴
		PreparedStatement preparedStatement = null;
		String sql = "DELETE FROM MEMBER WHERE MID = ? AND MPW = ?";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, Main.loginMember.getMid());
			preparedStatement.setString(2, Main.loginMember.getMpw());

			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println(result + "개 객체 삭제 완료");

			} else {
				System.out.println("회원탈퇴 실패");
			}
			preparedStatement.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}//method end

	public void designer(Connection connection) {
		String sql = "SELECT MNO, MNAME FROM MEMBER WHERE MGRADE = 2";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.print(resultSet.getInt("MNO") + ". ");
				System.out.println(resultSet.getString("MNAME"));
			}
			preparedStatement.close();
		} catch (SQLException e) {
			System.out.println("디자이너가 없습니다.");
			e.printStackTrace();
		}
	}

	public void refreshLogin(Connection connection) {//회원정보 수정이 일어날때마다 로그인session 업데이트 시켜주기
		MemberDTO mdto = new MemberDTO();
		String query ="select * from member where mno=?";
		try {
			PreparedStatement pst = connection.prepareStatement(query);
			pst.setInt(1, Main.loginMember.getMno());
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
			mdto.setMgrade(rs.getInt("mgrade"));
			mdto.setMid(rs.getString("mid"));
			mdto.setMpw(rs.getString("mpw"));
			mdto.setMphone(rs.getString("mphone"));
			mdto.setMname(rs.getString("mname"));
			mdto.setMno(rs.getInt("mno"));
			Main.loginMember = mdto;
			} else {System.out.println("오류 : 데이터 입력오류발생");}
			rs.close();
			pst.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		
	}//method end

}//class end
