package co.proj.main;

import java.util.List;

import co.proj.board.service.BoardService;
import co.proj.board.service.BoardVO;
import co.proj.board.serviceImpl.BoardServiceImpl;
import co.proj.reply.service.ReplyService;
import co.proj.reply.service.ReplyVO;
import co.proj.reply.serviceImpl.ReplyServiceImpl;

public class AppTest {
	public static void main(String[] args) {
		BoardService svc = new BoardServiceImpl();
		List<BoardVO> list = svc.boardList();

		ReplyService replyService = new ReplyServiceImpl();
		List<ReplyVO> rlist = replyService.replyList(5);

		for (int i = 0; i < list.size(); i++) {
			System.out.println("no" + (i + 1) + " => " + list.get(i).toString());
		}
		for (int i = 0; i < rlist.size(); i++) {
			System.out.println("no" + (i + 1) + " => " + rlist.get(i).toString());
		}
	}
}
