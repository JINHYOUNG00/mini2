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
		System.out.println("글번호 \t 제목 \t ");
		for (BoardVO board : boardList) {
			System.out.println(board.toString());
		}
		Menu menu = new Menu();
		menu.boardDetailMenu();
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
	}
	
	public int showBoardDetail() {
		System.out.print("조회할 글번호 >>");
		int boardNo = Integer.parseInt(scn.nextLine());
		Menu.clearScreen();
		ReplyVO.currentBoardNo = boardNo;
		System.out.println(boardService.showDetail(boardNo));
		List<ReplyVO> replyList = replyService.replyList(boardNo);
		for (ReplyVO reply : replyList) {
			System.out.println(reply.toString());
		}
		return boardNo;
	}
	
	
}
