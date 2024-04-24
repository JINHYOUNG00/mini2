package co.proj.reply.service;

import java.util.List;

public interface ReplyService {
	public List<ReplyVO> replyList(int boardNo);
	public boolean replyInsert(ReplyVO reply);
	public boolean replyUpdate(ReplyVO reply);
	public boolean replyDelete(ReplyVO reply);
	
	// 댓글 쓴 유저 찾기
	public String whoIsWriter(ReplyVO reply);
}
