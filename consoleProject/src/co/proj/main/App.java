package co.proj.main;

import java.util.Scanner;

import co.proj.user.UserVO;
import co.proj.user.impl.UserServiceImpl;

public class App {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		UserServiceImpl userService = new UserServiceImpl();
		UserVO user;
		
		boolean run = true;
		while (run) {
			System.out.println("1.로그인 2.회원가입 3.나가기");
			System.out.print("선택 >>");
			int choice = Integer.parseInt(scn.nextLine());
			switch (choice) {
			case 1: {
				user = new UserVO();
				System.out.println("======================== 로 그 인 ========================");
				System.out.print("아이디 >>");
				String id = scn.nextLine();
				user.setUserId(id);
				System.out.print("비밀번호 >>");
				String password = scn.nextLine();
				user.setUserPassword(password);
				
				// 로그인 메서드()
				int result = userService.loginUser(user);
				if (result == 1) {
					UserVO.loginUserId = id;
					System.out.println(UserVO.loginUserId + "계정에 접속했습니다.");
					Menu.boardMenu();
				}
				break;
			}
			case 2: {
				System.out.println("======================== 회 원 가 입 ========================");
				user = new UserVO();
				System.out.print("아이디 >>");
				String id = scn.nextLine();
				user.setUserId(id);
				System.out.print("비밀번호 >>");
				String password = scn.nextLine();
				System.out.print("비밀번호 확인 >>");
				String passwordCheck = scn.nextLine();
				if(password.equals(passwordCheck)) {
					user.setUserPassword(password);
				} else {
					System.out.println("비밀번호 불일치");
				}
				System.out.print("이메일 >>");
				String email = scn.nextLine();
				user.setUserEmail(email);
				System.out.print("이름 >>");
				String name = scn.nextLine();
				user.setUserName(name);
				System.out.print("연락처 >>");
				String tel = scn.nextLine();
				user.setUserTel(tel);
				
				userService.userInsert(user);
				// 회원가입 메서드()
				break;
			}
			case 3: {
				System.out.println("프로그램 종료");
				scn.close();
				run = false;
			}
			} // end of switch
			
			
			
			
			
			
		}
	}
}
