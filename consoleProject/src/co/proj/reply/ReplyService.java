package co.proj.reply;

import java.util.List;

public interface ReplyService {
	public List<ReplyVO> replyList(int boardNo);
	public boolean replyInsert();
	public boolean replyUpdate();
	public boolean replyDelete();
}
