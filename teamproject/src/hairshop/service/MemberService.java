package hairshop.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import hairshop.Main;
import hairshop.dao.MemberDAO;
import hairshop.dto.MemberDTO;

public class MemberService {
	
	private static Scanner scanner = new Scanner(System.in);
	
	
	public void login(Connection connection) throws SQLException {//로그인
		MemberDAO memberDAO = new MemberDAO();
		MemberDTO memberDTO = new MemberDTO();
		System.out.println("로그인 화면 입니다.");
		System.out.print("ID 입력 : ");
		memberDTO.setMid(scanner.next());
		System.out.print("PW 입력 : ");
		memberDTO.setMpw(scanner.next());

		memberDAO.login(connection, memberDTO);

	}

	public void join(Connection connection) throws SQLException {//회원가입
		MemberDTO newMember = new MemberDTO();
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("회원가입 화면 입니다.");
		System.out.print("이름 : ");
		newMember.setMname(scanner.next());
		System.out.print("핸드폰 : ");
		newMember.setMphone(scanner.next());
		System.out.print("ID : ");
		newMember.setMid(scanner.next());
		System.out.print("PW : ");
		newMember.setMpw(scanner.next());
		newMember.setMgrade(3);
		memberDAO.join(connection, newMember);
	}
	
	public void MemberMenu(Scanner scanner, Connection connection) throws SQLException, ParseException {
		MemberDTO memberDTO = new MemberDTO();
		BookService bookService = new BookService();
		
		
		boolean run = true;
		while (run) {
			if(Main.loginMember.getMid()==null) {System.out.println("로그인 후 이용가능한 메뉴 입니다.");
			return;
			}
			System.out.println("1.예약하기 | 2.예약관리 | 3.회원정보수정 | 4.회원탈퇴 | 5.종료");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				bookService.booking(connection);
				break;

			case 2:
				bookService.checkBook(connection);
				break;

			case 3:
				modify(connection);
				break;

			case 4:
				delete(connection);
				break;

			case 5:
				run = false;
				break;
			}
		}
	} // MemberMenu() end.

	public void modify(Connection connection) {//회원정보 수정
		MemberDAO memberDAO = new MemberDAO();
		System.out.println("회원정보 수정 화면 입니다.");
		System.out.println("본인 확인을 위해 ID, PW를 입력해주세요.");
		System.out.print("ID : ");
		String id = scanner.next();
		System.out.print("PW : ");
		String pw = scanner.next();

		if (id.equals(Main.loginMember.getMid()) && pw.equals(Main.loginMember.getMpw())) {
			System.out.println("수정할 정보 선택");
			System.out.println("1.핸드폰 번호 | 2.비밀번호");
			System.out.print(">>>>");
			int select = scanner.nextInt();
			if (select == 1) {
				System.out.println("현재 핸드폰 번호 : " + Main.loginMember.getMphone());
				System.out.print("변경할 핸드폰 번호 입력 : ");
				String modPhone = scanner.next();

				memberDAO.modPhone(connection, modPhone);
				memberDAO.refreshLogin(connection);

			} else if (select == 2) {
				System.out.println("현재 비밀번호 : " + Main.loginMember.getMpw());
				System.out.println("변경할 비밀번호 입력 : ");
				String modPw = scanner.next();

				memberDAO.modPw(connection, modPw);
				memberDAO.refreshLogin(connection);
			} else {
				System.out.println("번호 입력 오류");
			}
		} else {
			System.out.println("id,pw 입력오류");
		}
	}

	public void delete(Connection connection) {
		System.out.println("회원 탈퇴 화면 입니다.");
		System.out.println("본인확인을 위해 ID,PW를 입력해주세요");
		System.out.print("ID : ");
		String id = scanner.next();
		System.out.print("PW : ");
		String pw = scanner.next();

		if (id.equals(Main.loginMember.getMid()) && pw.equals(Main.loginMember.getMpw())) {
			MemberDAO memberDAO = new MemberDAO();
			System.out.println("정말 탈퇴 하시겠습니까?");
			System.out.println("1.YES | 2.NO");
			int select = scanner.nextInt();
			if (select == 1) {
				memberDAO.delete(connection);
				Main.loginMember=null;
				return;
			} else if (select == 2) {
				System.out.println("메뉴로 돌아갑니다.");
				return;
			} else {
				System.out.println("번호 입력 오류");
				return;
			}
		}

	}//method end
	

	

}//class end
