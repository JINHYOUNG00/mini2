package co.proj.main;

import java.util.Scanner;

import co.proj.user.User;

public class App {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		User userapp = new User();
		
		boolean run = true;
		while (run) {
			System.out.println("1.로그인 2.회원가입 3.나가기");
			System.out.print("선택 >>");
			int choice = Integer.parseInt(scn.nextLine());
			switch (choice) {
			case 1: {
				userapp.login();
				break;
			}
			case 2: {
				userapp.signUpUser();
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
