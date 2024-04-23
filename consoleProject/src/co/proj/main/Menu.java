package co.proj.main;

import java.util.Scanner;

import co.proj.board.Board;
import co.proj.reply.Reply;
import co.proj.user.service.UserVO;

public class Menu {
	
	Scanner scn = new Scanner(System.in);
	Board boardapp = new Board();
	Reply replyapp = new Reply();
	
	

	public void boardMenu() {
		boolean run = true;
		

		while (run) {
			System.out.println("1.글 목록 2.글 작성 3.나의 작성 글 4.내 정보 5.로그아웃");
			System.out.println(UserVO.loginUserId);
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

			}
			case "4": {

			}
			case "5": {

			}
			}
		}
	}

	public void boardDetailMenu() {
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
			}
		}
	}
	
	public void replyMenu(int boardNo) {
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
				clearScreen();
				boardMenu();
				break;
			}
			case "3": {
				clearScreen();
				boardMenu();
				break;
			}
			case "4": {
				clearScreen();
				boardapp.showBoardList();;
				break;
			}
			}
		}
	}
	
	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

}
