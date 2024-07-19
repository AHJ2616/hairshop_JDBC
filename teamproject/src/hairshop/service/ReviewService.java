package hairshop.service;

import java.sql.Connection;
import java.util.Scanner;

import hairshop.dao.ReviewDAO;
import hairshop.dto.MemberDTO;

public class ReviewService {

	public static ReviewDAO reviewDAO = new ReviewDAO();

	public void write(Scanner scanner, Connection connection, MemberDTO loginMember) {
		System.out.println("리뷰 작성 게시판 입니다.");
		System.out.println("수정, 삭제가 불가합니다.");

		reviewDAO.reviewList(connection, loginMember);
		// 지난예약을 보여주는 dao메서드
		System.out.println("리뷰를 작성할 예약 번호를 입력하세요");
		System.out.print(">>>>>");
		int select = scanner.nextInt();
		// select 가 있는지 검증하는 메서드 필요
		reviewDAO.reviewWrite(scanner, connection, loginMember, select);

	}

	
}
