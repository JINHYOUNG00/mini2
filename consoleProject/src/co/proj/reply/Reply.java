package co.proj.reply;

import java.util.Scanner;

import co.proj.reply.service.ReplyService;
import co.proj.reply.service.ReplyVO;
import co.proj.reply.serviceImpl.ReplyServiceImpl;
import co.proj.user.service.UserVO;

public class Reply {
	Scanner scn = new Scanner(System.in);
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
	}
}
