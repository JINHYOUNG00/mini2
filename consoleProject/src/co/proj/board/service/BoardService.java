package co.proj.board.service;

import java.util.List;

public interface BoardService {
	// 게시판 리스트 조회
	public List<BoardVO> boardList();
	
	// 게시판 상세조회
	public String showDetail(int boardNo);
	
	// 댓글 등록시 상세조회 다시 올리기 위해 필요함
	public String showDetail2(int boardNo);
	// 게시판 글 등록
	public boolean boardInsert(BoardVO board);
	
	// 게시판 글 수정
	public boolean boardUpdate(BoardVO board);
	
	// 게시판 글 삭제
	public boolean boardDelete(BoardVO board);
	
	// 내 작성글 조회
	public List<BoardVO> myBoardList(String userId); 
}
