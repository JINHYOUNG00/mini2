package co.proj.main;

import java.util.Scanner;

import co.proj.board.Board;
import co.proj.reply.Reply;
import co.proj.user.User;
import co.proj.user.service.UserVO;

public class Menu {

	static Scanner scn = new Scanner(System.in);
	static User userapp = new User();
	static Board boardapp = new Board();
	static Reply replyapp = new Reply();

	public static void startMenu() {
		boolean run = true;
		while (run) {
			System.out.println("                                 Home Menu               ");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("■                                                                          ■");
			System.out.println("■           1.로그인   2.회원가입   3.ID찾기   4.비밀번호찾기    5.나가기              ■");
			System.out.println("■                                                                          ■");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				userapp.login();
				break;
			}
			case "2": {
				userapp.signUpUser();
				break;
			}
			case "3": {
				userapp.findUserId();
				break;
			}
			case "4": {
				userapp.findUserPassword();
				break;
			}
			case "5": {
				System.out.println("프로그램 종료");
				run = false;
				System.exit(0);
				break;
			} default: {
				System.out.println("잘못된 입력");
			}
			} // end of switch
		}
	}

	public static void boardMenu() {
		boolean run = true;
		while (run) {
			System.out.println("                                 Board Menu               ");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.println("■                                                                          ■");
			System.out.println("■     1.글 목록   2.글 작성   3.나의 작성 글   4.내 정보   5.로그아웃                  ■");
			System.out.println("■                                                                          ■");
			System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				clearScreen();
				boardapp.showBoardList();
				boardDetailMenu();

				break;
			}
			case "2": {
				clearScreen();
				boardapp.showBoardInsert();
				break;
			}
			case "3": {
				clearScreen();
				boardapp.showMyBoardList();
				boardDetailMenu();

				break;
			}
			case "4": {
				userapp.myInfo();
				userInfoMenu();
				break;
			}
			case "5": {
				UserVO.loginUserId = null;
				Menu.clearScreen();
				System.out.println("### 로그아웃 되었습니다. ###");
				startMenu();
			}
			default : {
				System.out.println("잘못된 입력");
			}
			}
		}
	}
	
	public static void userInfoMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1.정보 수정 2.회원 탈퇴 3.돌아가기");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				userapp.updateUser();
				break;
			}
			case "2": {
				userapp.leaveUser();
				break;
			}
			case "3": {
				clearScreen();
				boardMenu();
				break;
			}
			default : {
				System.out.println("잘못된 입력");
			}

			}
		}
	}

	public static void myBoardMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1.상세조회 2.돌아가기");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				int boardNo = boardapp.showBoardDetail();
				replyMenu(boardNo);
				break;
			}
			case "2": {
				clearScreen();
				boardMenu();
				break;
			}
			default : {
				System.out.println("잘못된 입력");
			}
			}
		}
	}

	public static void boardDetailMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1.상세조회 2.제목 검색 3.작성자 검색 4.돌아가기");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				int boardNo = boardapp.showBoardDetail();
				replyMenu(boardNo);
				break;
			}
			case "2": {
				boardapp.showSearchBoardList(choice);
				break;
			}
			case "3": {
				boardapp.showSearchBoardList(choice);
				break;
			}
			case "4": {
				clearScreen();
				boardMenu();
				break;
			}
			default : {
				System.out.println("잘못된 입력");
			}
			}
		}
	}

	public static void myBoardDetailMenu() {
		boolean run = true;
		while (run) {
			System.out.println("1.상세조회 2.돌아가기");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				System.out.print("조회할 글번호 >>");
				int boardNo = boardapp.showMyBoardDetail(Integer.parseInt(scn.nextLine()));

				if(boardNo != 0) {
					MyBoardreplyMenu(boardNo);
					break;
				} else {
					System.out.println("잘못된 입력입니다.");
				}

			}
			case "2": {
				clearScreen();
				boardMenu();
				break;
			}
			default : {
				System.out.println("잘못된 입력");
			}
			}
		}
	}

	public static void MyBoardreplyMenu(int boardNo) {
		boolean run = true;
		while (run) {
			System.out.println("1.글수정 2.글삭제 3.댓글작성 4.댓글수정 5.댓글삭제 6.돌아가기");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				boardapp.updateBoard(boardNo);
				break;
			}
			case "2": {
				boardapp.deleteBoard(boardNo);
				break;
			}
			case "3": {
				replyapp.insertReply(boardNo);
				break;
			}
			case "4": {
				replyapp.updateReply(boardNo);
				break;
			}
			case "5": {
				replyapp.deleteReply(boardNo);
				break;
			}
			case "6": {
				clearScreen();
				boardapp.showMyBoardList();
				;
				break;
			}
			default : {
				System.out.println("잘못된 입력");
			}
			}
		}
	}

	public static void replyMenu(int boardNo) {
		boolean run = true;
		while (run) {
			System.out.println("1.댓글작성 2.댓글수정 3.댓글삭제 4.돌아가기");
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				replyapp.insertReply(boardNo);
				break;
			}
			case "2": {
				replyapp.updateReply(boardNo);
				break;
			}

			case "3": {
				replyapp.deleteReply(boardNo);
				break;
			}
			case "4": {
				clearScreen();
				boardapp.showBoardList();
				break;
			}
			default : {
				System.out.println("잘못된 입력");
			}
			}
		}
	}
	
	

	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

}
