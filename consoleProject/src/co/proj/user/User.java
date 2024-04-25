package co.proj.user;

import java.util.Scanner;
import java.util.regex.Pattern;

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
		boolean a = true;
		while (a) {
			System.out.print("아이디 >>");
			String id = scn.nextLine();
			if (userService.userIdCheck().contains(id)) {
				System.out.println("이미 가입된 아이디입니다. 다시 입력해주세요.");
			} else {
				user.setUserId(id);
				a = false;
			}
		}
		a = true;
		while (a) {
			System.out.print("비밀번호 >>");
			String password = scn.nextLine();
			System.out.print("비밀번호 확인 >>");
			String passwordCheck = scn.nextLine();
			if (password.equals(passwordCheck)) {
				user.setUserPassword(password);
				a = false;
			} else {
				System.out.println("비밀번호 불일치. 다시 입력해주세요.");
			}
		}
		a = true;
		while (a) {
			System.out.print("이메일 >>");
			String email = scn.nextLine();
			if (Pattern.matches("^[_a-zA-Z0-9-\\.]+@[_a-zA-Z0-9-\\.]+\\.[_a-zA-Z0-9-\\.]+$$", email)) {
				user.setUserEmail(email);
				a = false;
			} else {
				System.out.println("유효하지 않은 이메일입니다. 다시 입력해주세요.");
			}
		}
		System.out.print("이름 >>");
		String name = scn.nextLine();
		user.setUserName(name);
		a = true;
		while (a) {
			System.out.print("휴대전화 연락처(-없이 입력) >>");
			String tel = scn.nextLine();
			if (Pattern.matches("^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", tel)) {
				user.setUserTel(tel);
				a = false;
			} else {
				System.out.println("유효하지 않은 전화번호입니다. 다시 입력해주세요.");
			}
		}

		// 회원가입 메서드()
		if (userService.userInsert(user)) {
			Menu.clearScreen();
			System.out.println("가입 완료");
		} else {
			System.out.println("예외 발생 가입실패");
		}
	}

	public void myInfo() {
		System.out.println("======================== 내 정보 ========================");
		System.out.println(userService.userInfo(UserVO.loginUserId).toString());
		;
		System.out.println("=======================================================");
	}

	public void updateUser() {
		UserVO user = new UserVO();
		user.setUserId(UserVO.loginUserId);

		boolean run = true;
		while (run) {
			System.out.println("1.비밀번호 수정 2.email 수정 3.연락처 수정 4.돌아가기");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				boolean a = true;
				while (a) {
					System.out.println("현재 비밀번호 입력 >>");
					String currentPassword = scn.nextLine();
//					System.out.println(UserVO.loginUserId);
//					System.out.println(userService.findPassword(UserVO.loginUserId));
					if(currentPassword.equals(userService.findPassword(UserVO.loginUserId))) {
						System.out.print("수정할 비밀번호 입력 >>");
						String updatePassword = scn.nextLine();
						user.setUserPassword(updatePassword);
						user.setUserName("1"); // dao에서 email을 수정하는걸 알려주기위함
						userService.userUpdate(user);
						System.out.println("비밀번호가 변경되었습니다.");
						a = false;
						Menu.clearScreen();
						myInfo();
					} else {
						if (currentPassword.equals("~")) {
							a = false;
							Menu.clearScreen();
							myInfo();
						}
						System.out.println("비밀번호가 틀렸습니다.(~ 입력시 돌아가기)");
					}
				}
				
				break;
			}
			case "2": {
				System.out.print("수정할 email 입력 >>");
				String updateEmail = scn.nextLine();
				user.setUserEmail(updateEmail);
				user.setUserName("2"); // dao에서 email을 수정하는걸 알려주기위함
				userService.userUpdate(user);
				Menu.clearScreen();
				myInfo();
				System.out.println("email 정보가 변경되었습니다.");
				break;
			}
			case "3": {
				System.out.println("수정할 연락처 입력 >>");
				String updateTel = scn.nextLine();
				user.setUserTel(updateTel);
				user.setUserName("3");
				userService.userUpdate(user);
				Menu.clearScreen();
				myInfo();
				System.out.println("연락처 정보가 변경되었습니다.");
				break;
			}
			case "4": {
				Menu.clearScreen();
				myInfo();
				Menu.userInfoMenu();
				break;
			}
			}
		}
	}
}
