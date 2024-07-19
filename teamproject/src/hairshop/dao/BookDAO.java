package hairshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import hairshop.Main;
import hairshop.dto.BookDTO;
import hairshop.dto.MemberDTO;

public class BookDAO {

	public void insertBook(Connection connection, BookDTO newBook, int select, int select1)
			throws SQLException {
		String sql = "INSERT INTO BOOK VALUES(BOOK_SEQ.NEXTVAL, (SELECT SNAME FROM SHOP WHERE SNO = ?), ?, ?, ?, (SELECT CCUTNAME FROM CUT WHERE CCODE = ?), (SELECT MNAME FROM MEMBER WHERE MNO = ?), ?);";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setInt(1, select);
		preparedStatement.setString(2, newBook.getBdate());
		preparedStatement.setString(3, newBook.getBtime());
		preparedStatement.setInt(4, newBook.getBdesignerNum());
		preparedStatement.setInt(5, select1);
		preparedStatement.setInt(6, newBook.getBdesignerNum());
		preparedStatement.setInt(7, newBook.getBuserNum());
		int result = preparedStatement.executeUpdate();

		if (result > 0) {
			System.out.println("예약 완료");
		} else {
			System.out.println("예약 실패");
		}

	}

	public void list(Connection connection) {
		String sql = "SELECT * FROM BOOK WHERE BUSERNUM = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, Main.loginMember.getMno());
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getInt("BNO"));
				System.out.println(resultSet.getString("BSNAME"));
				System.out.println(resultSet.getString("BDATE"));
			}
		} catch (SQLException e) {
			System.out.println("예약이 없습니다.");

		}
	}

	public void read(Connection connection, int select) {
		String sql = "SELECT * FROM BOOK WHERE BNO = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, select);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				System.out.println(resultSet.getInt("BNO"));
				System.out.println(resultSet.getString("BSNAME"));
				System.out.println(resultSet.getString("BDATE"));
				System.out.println(resultSet.getString("BTIME"));
				System.out.println(resultSet.getInt("BDESIGNERNUM"));
				System.out.println(resultSet.getString("BCUT"));
				System.out.println(resultSet.getString("BNAME"));
				System.out.println(resultSet.getInt("BUSERNUM"));
			}

		} catch (SQLException e) {
			System.out.println("번호 입력 오류");
			return;

		}
	}

	public void modifyDate(Connection connection, int select, String date,
			String time) {

		String sql = "UPDATE BOOK SET BDATE = ?, BTIME = ? WHERE BNO = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, date);
			preparedStatement.setString(2, time);
			preparedStatement.setInt(3, select);
			int result = preparedStatement.executeUpdate();

			if (result > 0) {
				System.out.println("수정 완료");
			} else {
				System.out.println("수정 실패");
				return;
			}
		} catch (SQLException e) {
			System.out.println("예약 번호 오류");
			return;
		}

	}

	public void time(Connection connection, String bdate) {
		String sql = "SELECT BTIME FROM BOOK WHERE BDATE = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bdate);
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				System.out.println(resultSet.getString("BTIME") + "\t");
			}
		} catch (SQLException e) {
			System.out.println("예약 불가능한 시간이 없습니다.");
		}

	}

	public void modifyDesigner(Connection connection, int select, int dSelect) {
		String sql = "UPDATE BOOK SET BDESIGNERNUM = ? , BNAME = (SELECT MNAME FROM MEMBER WHERE MNO = ?) WHERE BNO = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, dSelect);
			preparedStatement.setInt(2, dSelect);
			preparedStatement.setInt(3, select);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("수정 완료!");
			} else {
				System.out.println("수정 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void modifyCut(Connection connection, int select, int cSelect) {
		String sql = "UPDATE BOOK SET BCUT = (SELECT CCUTNAME FROM CUT WHERE CCODE = ?) WHERE = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cSelect);
			preparedStatement.setInt(2, select);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("수정완료");
			} else {
				System.out.println("수정실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void delete(Connection connection, int select) {
		String sql = "DELETE FROM BOOK WHERE BNO = ?";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, select);
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				System.out.println("삭제완료.");
			} else {
				System.out.println("삭제 실패");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
