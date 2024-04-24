package co.proj.user;

import java.util.Scanner;

import co.proj.main.Menu;
import co.proj.user.service.UserService;
import co.proj.user.service.UserVO;
import co.proj.user.serviceImpl.UserServiceImpl;

public class User {
	UserVO user;
	Scanner scn = new Scanner(System.in);
	UserService userService = new UserServiceImpl();
	
	public void login() {
		user = new UserVO();
		Menu.clearScreen();
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
			
			Menu.boardMenu();
		}
		
	}
	
	public void signUpUser() {
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
	}
}
