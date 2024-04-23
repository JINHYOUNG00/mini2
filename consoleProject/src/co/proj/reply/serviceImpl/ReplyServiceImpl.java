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

public class ReplyServiceImpl implements ReplyService{
	
	Connection conn;
	private DataSource dataSource = DataSource.getInstance();
	PreparedStatement psmt;
	ResultSet rs;
	
	@Override
	public List<ReplyVO> replyList(int boardNo) {
		conn = dataSource.getConn();
		String sql = "select * from replies where board_no = ?";
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
		}
		
		return list;
	}
	
	
	@Override
	public boolean replyInsert(ReplyVO reply) {
		int replyNo = 0;
		conn = dataSource.getConn();
		String sql = "insert into replies (reply_no , reply_content, reply_writer, board_no)"
				+ "		values(?,?,?,?)";
		String sql2 = "select count(*) as counts from replies where board_no = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			PreparedStatement psmt2 = conn.prepareStatement(sql2);
			psmt2.setInt(1, reply.getBoardNo());
			
			ResultSet rs2 = psmt2.executeQuery();
			
			if(rs2.next()) {
				replyNo = rs2.getInt("counts") + 1;
			} else {
				replyNo = 1;
			}
			
			psmt.setInt(1, replyNo);
			psmt.setString(2, reply.getReplyContent());
			psmt.setString(3, reply.getReplyWriter());
			psmt.setInt(4, reply.getBoardNo());
			
			int r = psmt.executeUpdate();
			
			if(r>0) {
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
	public boolean replyUpdate() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean replyDelete() {
		// TODO Auto-generated method stub
		return false;
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
