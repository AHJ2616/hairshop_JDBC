package hairshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import hairshop.dto.MemberDTO;

public class ReviewDAO {
	
	public void reviewList(Connection connection, MemberDTO loginMember) {
		String sql = "SELECT BNO, BSNAME, BDATE, BCUT FROM BOOK WHERE BUSERNUM = ? AND BDATE < SYSDATE";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, loginMember.getMno());
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println("예약번호\t매장이름\t예약날짜\t커트명");
			System.out.println("=================================");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("BNO") + "\t");
				System.out.print(resultSet.getString("BSNAME") + "\t");
				System.out.print(resultSet.getDate("BDATE") + "\t");
				System.out.println(resultSet.getString("BCUT") + "\t");
			}
		} catch (SQLException e) {
			System.out.println("지난 예약이 없습니다.");
			return;
		}

	}

	public void reviewWrite(Scanner scanner, Connection connection, MemberDTO loginMember, int select) {
		String check = "SELECT * FROM WHERE BNO = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(check);
			preparedStatement.setInt(1, select);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.print("리뷰 내용 작성 : ");
				String contents = scanner.next();
				String sql = "INSERT INTO REVIEW (RNO, RDATE, RWRITER, RCONTENTS, RDESIGNERNUM) VALUES "
						+ "(RNO_SEQ.NEXTVAL, SYSDATE, ?, ?, (SELECT BDESIGNERNUM FROM BOOK WHERE BNO = ?)";
				try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, loginMember.getMname());
					preparedStatement.setString(2, contents);
					preparedStatement.setInt(3, select);

				} catch (SQLException e) {
					System.out.println("reviewDAO.reviewWrite()의 sql문 확인");
				}

			} else {
				System.out.println("번호 입력 오류");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
