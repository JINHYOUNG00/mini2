package co.proj.reply.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.proj.dao.DataSource;
import co.proj.reply.ReplyService;
import co.proj.reply.ReplyVO;

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
				reply.setBoardNo(boardNo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	@Override
	public boolean replyInsert() {
		// TODO Auto-generated method stub
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
