package co.proj.board;

public class BoardVO {
	private int boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardDate;
	private String boardWriter;
	private int boardHit;
	
	
	public BoardVO() {
	}
	
	public BoardVO(int boardNo, String boardTitle, String boardContent, String boardDate, String boardWriter,
			int boardHit) {
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardDate = boardDate;
		this.boardWriter = boardWriter;
		this.boardHit = boardHit;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardDate() {
		return boardDate;
	}
	public void setBoardDate(String boardDate) {
		this.boardDate = boardDate;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
	}
	public int getBoardHit() {
		return boardHit;
	}
	public void setBoardHit(int boardHit) {
		this.boardHit = boardHit;
	}

	@Override
	public String toString() {
		return boardNo + "\t\t" + boardTitle + "\t\t\t" + boardDate + "\t" + boardWriter + "\t" + boardHit;
	}
	
	public String detailInfo() {
		return "----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
				+ "\n글번호 |" + boardNo + "\t|글쓴이 |" + boardWriter + "\t|날짜 |" + boardDate + "\t|조회수 |" + boardHit + ""
						+ "\n-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
				+ "\n제목\t|" + boardTitle + ""
						+ "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------"
				+ boardContent + ""
						+ "\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------";
	}

	
}
