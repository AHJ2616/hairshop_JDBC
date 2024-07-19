package hairshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import hairshop.dto.MemberDTO;
import hairshop.service.MemberService;

public class Main {
	public static Connection connection = null;
	private static Scanner scanner = new Scanner(System.in);
	public static MemberDTO loginMember;
	
	Main() throws SQLException{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.0.120:1521:orcl","ann","8422");
		} catch (ClassNotFoundException e) {System.out.println("드라이버 위치오류");
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws SQLException {
		Main main = new Main();
		mainMenu();
		

	}//main method end
	
	public static void mainMenu() throws SQLException {//메인메뉴
		
		System.out.println("www.MBC헤어샵.com");
		System.out.println("-----------------------");
		MemberService memberSV = new MemberService();
		boolean run = true;
		while (run) {
			System.out.println("1.로그인 | 2.회원가입 | 3.종료");
			int select = scanner.nextInt();
			switch (select) {
			case 1:
				memberSV.login(connection);
				try {
				if (loginMember.getMid() != null) {
					System.out.println(loginMember.getMid() + "님 환영 합니다.");
				
			
					if (loginMember.getMgrade() == 1) {
						// 관리자 메뉴
					} else if (loginMember.getMgrade() == 2) {
						// 디자이너 메뉴
					} else {
						memberSV.MemberMenu(scanner, connection);
					}
				
				}}catch(NullPointerException e) {
					System.out.println("로그인 실패 : id, pw 입력오류");
					
				}
				

				break;

			case 2:
				memberSV.join(connection);
				break;

			case 3: System.out.println("MBC헤어샵 페이지를 종료합니다.");
				run = false;
				break;

			} // switch end
		} // while end

	} // mainMenu() end

	

}//class end
