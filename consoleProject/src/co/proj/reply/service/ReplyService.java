package co.proj.reply.service;

import java.util.List;

public interface ReplyService {
	public List<ReplyVO> replyList(int boardNo);
	public boolean replyInsert(ReplyVO reply);
	public boolean replyUpdate();
	public boolean replyDelete();
}
