package co.proj.reply;

public class ReplyVO {
	private int replyNo;
	private String replyContent;
	private String replyWriter;
	private String replyDate;
	private int boardNo;
	
	public static int currentBoardNo;
	
	
	
	public ReplyVO() {
	}



	public ReplyVO(int replyNo, String replyContent, String replyWriter, String replyDate, int boardNo) {
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.replyDate = replyDate;
		this.boardNo = boardNo;
	}



	public int getReplyNo() {
		return replyNo;
	}



	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}



	public String getReplyContent() {
		return replyContent;
	}



	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}



	public String getReplyWriter() {
		return replyWriter;
	}



	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}



	public String getReplyDate() {
		return replyDate;
	}



	public void setReplyDate(String replyDate) {
		this.replyDate = replyDate;
	}



	public int getBoardNo() {
		return boardNo;
	}



	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}



	@Override
	public String toString() {
		return "ReplyVO [replyNo=" + replyNo + ", replyContent=" + replyContent + ", replyWriter=" + replyWriter
				+ ", replyDate=" + replyDate + ", boardNo=" + boardNo + "]";
	}
	
	
	
	
}
