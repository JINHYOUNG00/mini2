package co.proj.reply;

import java.util.List;
import java.util.Scanner;

import co.proj.board.service.BoardService;
import co.proj.board.serviceImpl.BoardServiceImpl;
import co.proj.main.Menu;
import co.proj.reply.service.ReplyService;
import co.proj.reply.service.ReplyVO;
import co.proj.reply.serviceImpl.ReplyServiceImpl;
import co.proj.user.service.UserVO;

public class Reply {
	Scanner scn = new Scanner(System.in);
	BoardService boardService = new BoardServiceImpl();
	ReplyService replyService = new ReplyServiceImpl();
	
	public void insertReply(int boardNo) {
		System.out.println("=============== 리 플 작 성 ==================");
		ReplyVO reply = new ReplyVO();
		System.out.print("댓글 내용 입력 >>");
		String replyContent = scn.nextLine();
		reply.setBoardNo(boardNo);
		reply.setReplyContent(replyContent);
		reply.setReplyWriter(UserVO.loginUserId);
		replyService.replyInsert(reply);
		Menu.clearScreen();
		
		System.out.println(boardService.showDetail2(boardNo));
		List<ReplyVO> replyList = replyService.replyList(boardNo);
		System.out.println("================================================================ 댓 글 ================================================================");
		for (ReplyVO reply2 : replyList) {
			System.out.println(reply2.toString());
		}
		
	}
}
