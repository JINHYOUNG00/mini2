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
		System.out.println(
	"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 작 성 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
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
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		for (ReplyVO reply2 : replyList) {
			System.out.println(reply2.toString());
		}
		
	}
	
	public void updateReply(int boardNo) {
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 수 정 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		ReplyVO reply = new ReplyVO();
		System.out.print("수정할 리플번호 입력 >>");
		reply.setReplyNo(Integer.parseInt(scn.nextLine()));
		reply.setBoardNo(boardNo);
		System.out.print("수정할 내용 입력 >>");
		reply.setReplyContent(scn.nextLine());
		
		if (replyService.whoIsWriter(reply).equals(UserVO.loginUserId)) {
			Menu.clearScreen();
			replyService.replyUpdate(reply);
			System.out.println(boardService.showDetail2(boardNo));
			List<ReplyVO> replyList = replyService.replyList(boardNo);
			System.out.println(
					"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			for (ReplyVO reply2 : replyList) {
				System.out.println(reply2.toString());
			}
			System.out.println("수정 완료");
			
		} else {
			Menu.clearScreen();
			System.out.println(boardService.showDetail2(boardNo));
			List<ReplyVO> replyList = replyService.replyList(boardNo);
			System.out.println(
					"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			for (ReplyVO reply2 : replyList) {
				System.out.println(reply2.toString());
			}
			System.out.println("### 본인이 작성한 댓글만 수정할 수 있습니다. ###");
		}
		
	}
	
	public void deleteReply(int boardNo) {
		System.out.println(
				"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 삭 제 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
		ReplyVO reply = new ReplyVO();
		System.out.println("삭제할 리플번호 입력 >>");
		reply.setBoardNo(boardNo);
		reply.setReplyNo(Integer.parseInt(scn.nextLine()));
		
		if (replyService.whoIsWriter(reply).equals(UserVO.loginUserId)) {
			Menu.clearScreen();
			replyService.replyDelete(reply);
			System.out.println(boardService.showDetail2(boardNo));
			List<ReplyVO> replyList = replyService.replyList(boardNo);
			System.out.println(
					"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			for (ReplyVO reply2 : replyList) {
				System.out.println(reply2.toString());
			}
			System.out.println("삭제 완료");
			
		} else {
			Menu.clearScreen();
			System.out.println(boardService.showDetail2(boardNo));
			List<ReplyVO> replyList = replyService.replyList(boardNo);
			System.out.println(
					"■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■ 댓 글 ■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
			for (ReplyVO reply2 : replyList) {
				System.out.println(reply2.toString());
			}
			System.out.println("### 본인이 작성한 댓글만 삭제할 수 있습니다. ###");
		}
		
	}
	
	
}
