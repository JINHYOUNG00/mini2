package co.proj.main;

import java.util.List;
import java.util.Scanner;

import co.proj.board.BoardService;
import co.proj.board.BoardVO;
import co.proj.board.impl.BoardServiceImpl;
import co.proj.reply.ReplyVO;
import co.proj.user.UserVO;

public class Menu {
	public static void boardMenu() {
		Scanner scn = new Scanner(System.in);
		boolean run = true;
		BoardService boardService = new BoardServiceImpl();
		
		
		
		while (run) {
			System.out.println("1.글 목록 2.글 작성 3.나의 작성 글 4.내 정보 5.로그아웃");
			System.out.println(UserVO.loginUserId);
			System.out.print("선택 >>");
			String choice = scn.nextLine();
			switch (choice) {
			case "1": {
				System.out.println("=============== 글 목 록 ==================");
				List<BoardVO> boardList = boardService.boardList();
				System.out.println("글번호 \t 제목 \t ");
				for (BoardVO board : boardList) {
					System.out.println(board.toString());
				}
				System.out.println("1.상세조회 2.돌아가기");
				System.out.print("선택 >>");
				String choice2 = scn.nextLine();
				switch (choice2) {
				case "1": {
					System.out.print("조회할 글번호 >>");
					int boardNo = Integer.parseInt(scn.nextLine());
					ReplyVO.currentBoardNo = boardNo;
					System.out.println(boardService.showDetail(boardNo));
					System.out.println("1.");
					break;
				}
				case "2": {
					break;
				}
				}
				break;
			}
			case "2": {
				System.out.println("=============== 글 작 성 ==================");
				BoardVO board = new BoardVO();
				System.out.print("제목 >>");
				String title = scn.nextLine();
				boolean input = true;
				System.out.println("내용 입력 (~입력 시 입력 완료) >>");
				String content = "";
				while(input) {
					String content2 = "";
					content2 = scn.nextLine();
					if (!content2.equals("~")) {
						content += "\n" + content2;
					} else {
						input = false;
					}
				}
				board.setBoardTitle(title);
				board.setBoardContent(content);
				board.setBoardWriter(UserVO.loginUserId);
				
			
				
				
				if(boardService.boardInsert(board)) {
					System.out.println("글 등록이 완료되었습니다.");
				} else {
					System.out.println("글 등록에 실패하였습니다.");
				}
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
	
	
	
	
	
	
}
