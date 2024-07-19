package hairshop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import hairshop.Main;
import hairshop.dao.BookDAO;
import hairshop.dao.MemberDAO;
import hairshop.dto.BookDTO;
import hairshop.dto.MemberDTO;

public class BookService {

	public static BookDAO bookDAO = new BookDAO();
	public static MemberDAO memberDAO = new MemberDAO();
	private static Scanner scanner = new Scanner(System.in);

	public void booking(Connection connection) throws SQLException {
		BookDTO newBook = new BookDTO();
		System.out.println("예약 가능한 매장 리스트 입니다.");
		// shop.list() - shop 번호랑 이름 위치만 전체출력
		System.out.println("예약 할 매장 선택");
		System.out.print(">>>");
		int select = scanner.nextInt();
		if (select == newBook.getBno()) {
			// shop.read() - shop 하나의 모든정보 출력.

			System.out.print("예약 날짜 입력(YYYY/MM/DD) : ");
			newBook.setBdate(scanner.next());
			System.out.println("예약 불가능한 시간 목록(제외하고 입력해주세요)");
			bookDAO.time(connection, newBook.getBdate());
			System.out.print("예약 시간 입력(TT:MM) : ");
			newBook.setBtime(scanner.next());

			memberDAO.designer(connection);
			System.out.print("디자이너 선택 : ");
			newBook.setBdesigenrNum(scanner.nextInt());
			

			System.out.print("서비스 선택");
			// shop.cut.list() - 커트 번호, 커트 이름, 커트 설명, 커트 가격 전체출력

			int select1 = scanner.nextInt(); // select 1 == scode(커트번호)
			newBook.setBuserNum(Main.loginMember.getMno());

			bookDAO.insertBook(connection, newBook, select, select1);

		}
	}

	public void checkBook(Connection connection) throws SQLException {
		System.out.println("예약관리 화면 입니다.");

		bookDAO.list(connection);

		System.out.println("확인 할 예약번호를 입력하세요(메뉴복귀는 99번)");
		System.out.print(">>>>>");

		int select = scanner.nextInt();
		if (select == 99) {
			System.out.println("메뉴로 복귀합니다.");
			return;
		} else {
			bookDAO.read(connection, select);
		}
		System.out.print("1.예약수정 | 2.예약삭제 | 3.메뉴복귀");
		int select2 = scanner.nextInt();

		if (select2 == 1) {

			System.out.print("1.예약날짜,시간 | 2.디자이너 | 3.커트");
			int select3 = scanner.nextInt();
			switch (select3) {
			case 1:
				System.out.println("변경할 날짜(YYYY/MM/DD) : ");
				String date = scanner.next();
				System.out.println("변경할 시간(TT:MM) : ");
				String time = scanner.next();

				bookDAO.modifyDate(connection, select, date, time);
				break;
			case 2:
				System.out.println("변경할 디자이너 번호 선택");
				memberDAO.designer(connection);
				System.out.print(">>>>>");
				int dSelect = scanner.nextInt();
				bookDAO.modifyDesigner(connection, select, dSelect);
				break;
			case 3:
				System.out.println("수정할 서비스 번호 선택");
				// shop.cut.list() - 커트 번호, 커트 이름, 커트 설명, 커트 가격 전체출력
				System.out.print(">>>>>");
				int cSelect = scanner.nextInt();
				bookDAO.modifyCut(connection,select, cSelect);
				break;
			}

		} else if (select2 == 2) {
			System.out.println("예약취소시 위약금이 발생할수 있습니다.");
			System.out.println("정말 취소 하시겠습니까?");
			System.out.print("1.YES | 2.NO");
			int yesNo = scanner.nextInt();
			if(yesNo == 1) {
				bookDAO.delete(connection, select);
			}
			// delete 메서드로 이동(select가지고 가야함)

		} else if (select2 == 3) {

		} else {
			System.out.println("번호입력 오류");
		}

	}

	
}
