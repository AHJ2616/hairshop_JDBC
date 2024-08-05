package hairshop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.Connection_pool;
import hairshop.dto.ReviewDTO;

public class ReviewDAO extends Connection_pool {

	// 리뷰 목록 불러오기
	public List<ReviewDTO> getReviewList(int start, int end, String searchField, String searchWord) {
		List<ReviewDTO> reviewList = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT @ROWNUM := @ROWNUM + 1 AS ROWNUM, R.* "
				+ "FROM (SELECT * FROM REVIEW WHERE " + searchField + " LIKE ? ORDER BY RNO DESC) R) "
				+ "WHERE ROWNUM BETWEEN ? AND ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + searchWord + "%");
			pst.setInt(2, start);
			pst.setInt(3, end);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				ReviewDTO review = new ReviewDTO();
				review.setRno(rs.getInt("RNO"));
				review.setRwriter(rs.getString("RWRITER"));
				review.setRcontents(rs.getString("RCONTENTS"));
				review.setRdate(rs.getDate("RDATE"));
				reviewList.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reviewList;
	}

	// 전체 리뷰 수 가져오기
	public int getTotalReviewCount(String searchField, String searchWord) {
		int totalCount = 0;
		String sql = "SELECT COUNT(*) FROM REVIEW WHERE " + searchField + " LIKE ?";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, "%" + searchWord + "%");
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalCount;
	}

	// 리뷰 작성
	public void writeReview(ReviewDTO review) {
		String sql = "INSERT INTO REVIEW (RNO, RDATE, RWRITER, RCONTENTS, VISITCOUNT) "
				+ "VALUES (RNO_SEQ.NEXTVAL, SYSDATE, ?, ?, 0)";
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, review.getRwriter());
			pst.setString(2, review.getRcontents());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
