package hairshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hairshop.dto.BookDTO;

public class BookDAO {

	public void insertBook(Connection connection, BookDTO newBook, int select, int select1)
			throws SQLException, ParseException {

		String bdateStr = newBook.getBdate();
		String btimeStr = newBook.getBtime();

		if (bdateStr == null || btimeStr == null) {
			System.out.println("예약 날짜와 시간을 입력하세요");
			return;
		}

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		Date bdate = sdfDate.parse(bdateStr);
		Date bTime = sdfTime.parse(btimeStr);
		Date today = new Date();

		if (bdate.before(today)) {
			System.out.println("예약날짜는 오늘 날짜 이후로 설정해야합니다.");
			return;
		}

		String sqlTime = "SELECT SOPEN, SCLOSE FROM SHOP WHERE SNO = ?";
		PreparedStatement preparedStatementTime = connection.prepareStatement(sqlTime);
		preparedStatementTime.setInt(1, select);
		ResultSet resultSet = preparedStatementTime.executeQuery();

		if (resultSet.next()) {
			String openTimeStr = resultSet.getString("SOPEN");
			String closeTimeStr = resultSet.getString("SCLOSE");
			Date open = sdfTime.parse(openTimeStr);
			Date close = sdfTime.parse(closeTimeStr);

			if (bTime.before(open) || bTime.after(close)) {
				System.out.println("예약시간은 매장의 오픈시간과 마감 시간 사이여야 합니다.");
				return;
			}
		} else {
			System.out.println("매장이 없습니다.");
		}

		resultSet.close();
		preparedStatementTime.close();

		String sql = "INSERT INTO BOOK VALUES(BOOK_SEQ.NEXTVAL, (SELECT SNAME FROM SHOP WHERE SNO = ?), ?, ?, ?, (SELECT CCUTNAME FROM CUT WHERE CCODE = ?), (SELECT MNAME FROM MEMBER WHERE MNO = ?), ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(sql);
		// 1. seq 번호 - book_seq.nextval
		// 2. 매장이름 select sname from shop where sno = ?
		// 3.날짜 newBook.getBdate()
		// 4.시간 newBook.getBtime
		// 5.디자이너번호 newBook.getbdesignernum
		// 6.커트명 select ccutname from cut where ccode = select1
		// 7디자이너명 select mname frome member where mno = newBook.getbdesignernum
		// 8예약한사용자번호
		preparedStatement.setInt(1, select);
		preparedStatement.setString(2, bdateStr);
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
			//preparedStatement.setInt(1, Main.loginMember.getMno());
			ResultSet resultSet = preparedStatement.executeQuery();

			System.out.println("예약번호\t매장이름\t예약날짜");
			System.out.println("===============================");
			while (resultSet.next()) {
				System.out.print(resultSet.getInt("BNO") + "\t");
				System.out.print(resultSet.getString("BSNAME") + "\t");
				System.out.println(resultSet.getString("BDATE") + "\t");
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
				System.out.println("예약번호\t매장이름\t예약날짜\t예약시간\t");
				System.out.print(resultSet.getInt("BNO") + "\t");
				System.out.print(resultSet.getString("BSNAME") + "\t");
				System.out.print(resultSet.getString("BDATE") + "\t");
				System.out.print(resultSet.getString("BTIME") + "\t\n");
				System.out.println("커트명\t디자이너");
				System.out.print(resultSet.getString("BCUT") + "\t");
				System.out.println(resultSet.getString("BNAME") + "\t");

			}

		} catch (SQLException e) {
			System.out.println("번호 입력 오류");
			return;

		}
	}

	public void modifyDate(Connection connection, int select, String date, String time) {

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
