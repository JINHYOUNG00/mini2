package co.proj.board;

import java.util.List;
import java.util.Scanner;

import co.proj.board.service.BoardService;
import co.proj.board.service.BoardVO;
import co.proj.board.serviceImpl.BoardServiceImpl;
import co.proj.main.Menu;
import co.proj.reply.service.ReplyService;
import co.proj.reply.service.ReplyVO;
import co.proj.reply.serviceImpl.ReplyServiceImpl;
import co.proj.user.service.UserVO;

public class Board {
	BoardVO board;
	Scanner scn = new Scanner(System.in);
	BoardService boardService = new BoardServiceImpl();
	ReplyService replyService = new ReplyServiceImpl();

	// 글 목록
	public void showBoardList() {
		System.out.println("=============== 글 목 록 ==================");
		List<BoardVO> boardList = boardService.boardList();
		System.out.println("글번호 \t\t 제목 \t\t\t\t 날짜 \t\t 글쓴이 \t조회수 ");
		for (BoardVO board : boardList) {
			System.out.println(board.toString());
		}
		Menu.boardDetailMenu();
	}

	// 글 작성
	public void showBoardInsert() {
		System.out.println("=============== 글 작 성 ==================");
		BoardVO board = new BoardVO();
		System.out.print("제목 >>");
		String title = scn.nextLine();
		boolean input = true;
		System.out.println("내용 입력 (~입력 시 입력 완료) >>");
		String content = "";
		while (input) {
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

		if (boardService.boardInsert(board)) {
			System.out.println("글 등록이 완료되었습니다.");
		} else {
			System.out.println("글 등록에 실패하였습니다.");
		}
	}
	
	public void updateBoard(int boardNo) {
		BoardVO board = new BoardVO();
//		System.out.println("수정할 글번호 >>");
//		int boardNo = Integer.parseInt(scn.nextLine());
		board.setBoardNo(boardNo);
		if (UserVO.loginUserId.equals(boardService.whoIsWriter(board.getBoardNo()))) {
			boolean run = true;
			while(run) {
				System.out.println("1.제목 수정 2.내용 수정 3.모두 수정 4.돌아가기");
				String choice = scn.nextLine();
				switch (choice) {
				case "1": {
					System.out.print("수정할 제목 입력 >>");
					String UpdateTitle = scn.nextLine();
					board.setBoardTitle(UpdateTitle);
					board.setBoardHit(1); // 얘가 1일때 제목수정하는 쿼리
					boardService.boardUpdate(board);
					showMyBoardDetail(boardNo);
					break;
				}
				
				case "2": {
					System.out.print("수정할 내용 입력 (~입력 시 입력 완료) >>");
					boolean input = true;
					String updateContent = "";
					while (input) {
						String updateContent2 = "";
						updateContent2 = scn.nextLine();
						if(!updateContent2.equals("~")) {
							updateContent += "\n" + updateContent2;
						} else {
							input = false;
						}
					}
					board.setBoardContent(updateContent);
					board.setBoardHit(2); // 얘가 2일때 내용수정하는 쿼리
					boardService.boardUpdate(board);
					showMyBoardDetail(boardNo);
					break;
				}
				case "3": {
					System.out.print("수정할 제목 입력 >>");
					String UpdateTitle = scn.nextLine();
					board.setBoardTitle(UpdateTitle);
					System.out.print("수정할 내용 입력 (~입력 시 입력 완료) >>");
					boolean input = true;
					String updateContent = "";
					while (input) {
						String updateContent2 = "";
						updateContent2 = scn.nextLine();
						if(!updateContent2.equals("~")) {
							updateContent += "\n" + updateContent2;
						} else {
							input = false;
						}
					}
					board.setBoardContent(updateContent);
					board.setBoardHit(3); // 얘가 3일때 제목 내용수정하는 쿼리
					boardService.boardUpdate(board);
					showMyBoardDetail(boardNo);
					break;
				}
				case "4": {
					run = false;
					break;
				}
				}
			}
		} else {
			System.out.println("잘못된 입력입니다.");
		}
		
		Menu.clearScreen();
		
	}
	
	public void deleteBoard(int boardNo) {
		System.out.println("삭제하시겠습니까? (Y/N)");
		String confirm = scn.nextLine();
		if(confirm.equals("Y") || confirm.equals("y")) {
			boardService.boardDelete(boardNo);
		} else {
			System.out.println("취소하였습니다.");
		}
	}

	public int showBoardDetail() {
		System.out.print("조회할 글번호 >>");
		int boardNo = Integer.parseInt(scn.nextLine());
		Menu.clearScreen();
//		ReplyVO.currentBoardNo = boardNo;
		System.out.println(boardService.showDetail(boardNo));
		List<ReplyVO> replyList = replyService.replyList(boardNo);
		System.out.println(
				"================================================================ 댓 글 ================================================================");
		for (ReplyVO reply : replyList) {
			System.out.println(reply.toString());
		}
		return boardNo;
	}

	public void showMyBoardList() {
		System.out.println("=============== 글 목 록 ==================");
		List<BoardVO> boardList = boardService.myBoardList(UserVO.loginUserId);
		System.out.println("글번호 \t\t 제목 \t\t\t\t 날짜 \t\t 글쓴이 \t조회수 ");
		for (BoardVO board : boardList) {
			System.out.println(board.toString());
		}
		Menu.myBoardDetailMenu();
	}

	public int showMyBoardDetail(int boardNo) {
		if (UserVO.loginUserId.equals(boardService.whoIsWriter(boardNo))) {
			Menu.clearScreen();
			System.out.println(boardService.showDetail(boardNo));
			List<ReplyVO> replyList = replyService.replyList(boardNo);
			System.out.println(
					"================================================================ 댓 글 ================================================================");
			for (ReplyVO reply : replyList) {
				System.out.println(reply.toString());
			}
			return boardNo;
		} else {
			
			return 0;
		}
	}

}
