package co.proj.board.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.proj.board.service.BoardService;
import co.proj.board.service.BoardVO;
import co.proj.dao.DataSource;

public class BoardServiceImpl implements BoardService {

	Connection conn;
	private DataSource dataSource = DataSource.getInstance();
	PreparedStatement psmt;
	ResultSet rs;

	@Override
	public List<BoardVO> boardList() {
		conn = dataSource.getConn();
		String sql = "select board_no, board_title, board_writer, board_date, board_hit from boards order by board_no";
		List<BoardVO> list = new ArrayList<>();

		try {
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardHit(rs.getInt("board_hit"));

				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}
	
	@Override
	public List<BoardVO> searchBoardList(String str, String choice) {
		conn = dataSource.getConn();
		String sql1 = "select board_no, board_title, board_writer, board_date, board_hit "
				+ " from boards"
				+ " where board_title like ?"
				+ " order by board_no";
		
		String sql2 = "select board_no, board_title, board_writer, board_date, board_hit "
				+ " from boards"
				+ " where board_writer = ?"
				+ " order by board_no";
		
		List<BoardVO> list = new ArrayList<>();

		try {
			switch (choice) {
			case "2":{
				psmt = conn.prepareStatement(sql1);
				psmt.setString(1, "%"+str+"%");
				rs = psmt.executeQuery();
			}
			case "3":{
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, str);
				rs = psmt.executeQuery();
			}
			}
			

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardHit(rs.getInt("board_hit"));

				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return list;
	}

	@Override
	public String showDetail(int boardNo) {
		conn = dataSource.getConn();
		String sql = "select * from boards where board_no = ?";
		String sql2 = "update boards set board_hit = board_hit + 1 where board_no = ?";

		BoardVO board = new BoardVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);

			PreparedStatement psmt2 = conn.prepareStatement(sql2);
			psmt2.setInt(1, boardNo);
			psmt2.executeUpdate();

			rs = psmt.executeQuery();

			if (rs.next()) {
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setBoardHit(rs.getInt("board_hit"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board.detailInfo();
	}

	@Override
	public String showDetail2(int boardNo) {
		conn = dataSource.getConn();
		String sql = "select * from boards where board_no = ?";

		BoardVO board = new BoardVO();
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);

			rs = psmt.executeQuery();

			if (rs.next()) {
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardContent(rs.getString("board_content"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setBoardHit(rs.getInt("board_hit"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board.detailInfo();
	}

	@Override
	public boolean boardInsert(BoardVO board) {
		conn = dataSource.getConn();
		String sql = "insert into boards(board_no, board_title, board_content, board_writer)"
				+ "		values(board_no_seq.nextval, ?,?,?)";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, board.getBoardTitle());
			psmt.setString(2, board.getBoardContent());
			psmt.setString(3, board.getBoardWriter());

			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return false;
	}

	@Override
	public boolean boardUpdate(BoardVO board) {
		conn = dataSource.getConn();
		String sql1 = "update boards set board_title = ? where board_no = ?";
		String sql2 = "update boards set board_content = ? where board_no = ?";
		String sql3 = "update boards set board_title = ?, board_content = ? where board_no = ?";

		int r = 0;
		
		try {
			switch (board.getBoardHit()) {
			case 1: {
				psmt = conn.prepareStatement(sql1);
				psmt.setString(1, board.getBoardTitle());
				psmt.setInt(2, board.getBoardNo());
				r = psmt.executeUpdate();
				break;
			}
			case 2: {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, board.getBoardContent());
//				System.out.println(board.getBoardContent());
				psmt.setInt(2, board.getBoardNo());
				r = psmt.executeUpdate();
				break;
			}
			case 3: {
				psmt = conn.prepareStatement(sql3);
				psmt.setString(1, board.getBoardTitle());
				psmt.setString(2, board.getBoardContent());
//				System.out.println(board.getBoardContent());
				psmt.setInt(3, board.getBoardNo());
				r = psmt.executeUpdate();
				break;
			}
			}
//			int r = psmt.executeUpdate();

			if (r > 0) {
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}

		return false;
	}

	@Override
	public boolean boardDelete(int boardNo) {
		conn = dataSource.getConn();
		String sql = "delete boards where board_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			
			int r = psmt.executeUpdate();
			
			if (r>0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		return false;
	}

	@Override
	public List<BoardVO> myBoardList(String userId) {
		conn = dataSource.getConn();
		String sql = "select board_no, board_title, board_writer, board_date, board_hit from boards where board_writer = ? order by board_no";
		List<BoardVO> list = new ArrayList<>();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();

			while (rs.next()) {
				BoardVO board = new BoardVO();
				board.setBoardNo(rs.getInt("board_no"));
				board.setBoardTitle(rs.getString("board_title"));
				board.setBoardWriter(rs.getString("board_writer"));
				board.setBoardDate(rs.getString("board_date"));
				board.setBoardHit(rs.getInt("board_hit"));

				list.add(board);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	@Override
	public String whoIsWriter(int boardNo) {
		conn = dataSource.getConn();
		BoardVO board = new BoardVO();
		String sql = "select board_writer from boards where board_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);

			rs = psmt.executeQuery();
			while (rs.next()) {
				board.setBoardWriter(rs.getString("board_writer"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return board.getBoardWriter();
	}

	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
