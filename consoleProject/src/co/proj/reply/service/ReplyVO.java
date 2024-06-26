package co.proj.reply.service;

public class ReplyVO {
	private int replyId;
	private int replyNo;
	private String replyContent;
	private String replyWriter;
	private String replyDate;
	private int boardNo;
	
	public static int currentBoardNo;
	
	
	
	public ReplyVO() {
	}



	public ReplyVO(int replyId, int replyNo, String replyContent, String replyWriter, String replyDate, int boardNo) {
		this.replyId = replyId;
		this.replyNo = replyNo;
		this.replyContent = replyContent;
		this.replyWriter = replyWriter;
		this.replyDate = replyDate;
		this.boardNo = boardNo;
	}

	

	public int getReplyId() {
		return replyId;
	}



	public void setReplyId(int replyId) {
		this.replyId = replyId;
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
		return String.format("No.%-3d|    %-10s %60s \n %-200s\n"
				+ "-------------------------------------------------------------------------------------------", replyNo, replyWriter, replyDate, replyContent);
//				"NO." + replyNo + " | "  + "|" + replyWriter + "|" + replyDate + "|" + " \n"  + "|" + replyContent + "|";
	}
	
	
	
	
}
