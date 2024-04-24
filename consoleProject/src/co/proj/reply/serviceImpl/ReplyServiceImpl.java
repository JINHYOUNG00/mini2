package co.proj.reply.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.proj.dao.DataSource;
import co.proj.reply.service.ReplyService;
import co.proj.reply.service.ReplyVO;

public class ReplyServiceImpl implements ReplyService {

	Connection conn;
	private DataSource dataSource = DataSource.getInstance();
	PreparedStatement psmt;
	ResultSet rs;

	@Override
	public List<ReplyVO> replyList(int boardNo) {
		conn = dataSource.getConn();
		String sql = "select * from replies where board_no = ? order by reply_no";
		List<ReplyVO> list = new ArrayList<>();

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();

			while (rs.next()) {
				ReplyVO reply = new ReplyVO();
				reply.setReplyNo(rs.getInt("reply_no"));
				reply.setReplyContent(rs.getString("reply_content"));
				reply.setReplyDate(rs.getString("reply_date"));
				reply.setReplyWriter(rs.getString("reply_writer"));
				reply.setBoardNo(rs.getInt("board_no"));

				list.add(reply);
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
	public boolean replyInsert(ReplyVO reply) {
		conn = dataSource.getConn();
		int replyNo = 0;
		String sql = "insert into replies (reply_id, reply_no , reply_content, reply_writer, board_no)"
				+ "		values(reply_id_seq.nextval,?,?,?,?)";
//		String sql2 = "select count(*) as counts from replies where board_no = ?";
		String sql3 = "select reply_no from replies where board_no = ? order by reply_no";

		try {
			psmt = conn.prepareStatement(sql);
//			PreparedStatement psmt2 = conn.prepareStatement(sql2);
			PreparedStatement psmt3 = conn.prepareStatement(sql3);

//			psmt2.setInt(1, reply.getBoardNo());
			psmt3.setInt(1, reply.getBoardNo());

//			ResultSet rs2 = psmt2.executeQuery();
			ResultSet rs3 = psmt3.executeQuery();

			while (rs3.next()) {
				if (rs3.getRow() == 1) {
//				replyNo = rs2.getInt("counts") + 1;
					replyNo = 1;
				} else {
					System.out.println("확인" + rs3.getInt("reply_no"));
					replyNo = rs3.getInt("reply_no") + 1; // 해당 글의 가장 아래쪽 댓글넘버
				}
			}

			psmt.setInt(1, replyNo);
			psmt.setString(2, reply.getReplyContent());
			psmt.setString(3, reply.getReplyWriter());
			psmt.setInt(4, reply.getBoardNo());

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
	public boolean replyUpdate(ReplyVO reply) {
		conn = dataSource.getConn();
		String sql = "Update replies set reply_content = ? where board_no = ? and reply_no = ?";

		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, reply.getReplyContent());
			psmt.setInt(2, reply.getBoardNo());
			psmt.setInt(3, reply.getReplyNo());

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
	public boolean replyDelete(ReplyVO reply) {
		conn = dataSource.getConn();
		String sql = "delete replies where board_no = ? and reply_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, reply.getBoardNo());
			psmt.setInt(2, reply.getReplyNo());
			
			int r = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		
		
		return false;
	}

	@Override
	public String whoIsWriter(ReplyVO reply) {
		conn = dataSource.getConn();
		String sql = "select reply_writer from replies where board_no = ? and reply_no = ?";
		String writer = "";

		try {
			psmt = conn.prepareStatement(sql);

			psmt.setInt(1, reply.getBoardNo());
			psmt.setInt(2, reply.getReplyNo());

			rs = psmt.executeQuery();

			while (rs.next()) {
				writer = rs.getString("reply_writer");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return writer;
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
