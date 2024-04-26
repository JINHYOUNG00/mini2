package co.proj.user.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.proj.dao.DataSource;
import co.proj.main.Menu;
import co.proj.user.service.UserService;
import co.proj.user.service.UserVO;

public class UserServiceImpl implements UserService{

	Connection conn;
	private DataSource dataSource = DataSource.getInstance();
	PreparedStatement psmt;
	ResultSet rs;
	
	@Override
	public boolean userInsert(UserVO user) {
		conn = dataSource.getConn();
		String sql = "insert into users (user_id, user_name, user_password, user_email, user_tel)"
				+ "	values(?,?,?,?,?)";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			psmt.setString(2, user.getUserName());
			psmt.setString(3, user.getUserPassword());
			psmt.setString(4, user.getUserEmail());
			psmt.setString(5, user.getUserTel());
			int r = psmt.executeUpdate();
			
			if (r>0) {
				return true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return false;
	}
	
	@Override
	public List<String> userIdCheck(int num) {
		conn = dataSource.getConn();
		UserVO user = new UserVO();
		String sql1 = "select user_id from users";
		String sql2 = "select user_tel from users";
		List<String> list = new ArrayList<>();
		
		try {
			switch (num) {
			case 1: {
				psmt = conn.prepareStatement(sql1);
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					user.setUserId(rs.getString("user_id"));
					list.add(user.getUserId());
				}
			}
			case 2: {
				psmt = conn.prepareStatement(sql2);
				rs = psmt.executeQuery();
				
				while (rs.next()) {
					user.setUserTel(rs.getString("user_tel"));
					list.add(user.getUserTel());
				}
			}
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
	public UserVO userInfo(String userId) {
		conn = dataSource.getConn();
		String sql = "select user_id, user_name, user_email, user_tel from users where user_id = ?";
		UserVO user = new UserVO();
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				user.setUserId(rs.getString("user_id"));
				user.setUserName(rs.getString("user_name"));
				user.setUserEmail(rs.getString("user_email"));
				user.setUserTel(rs.getString("user_tel"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	
	
	@Override
	public int loginUser(UserVO user) {
		conn = dataSource.getConn();
		String sql = "SELECT USER_PASSWORD FROM USERS WHERE USER_ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, user.getUserId());
			rs = psmt.executeQuery();
			if (rs.next()) {
				if (rs.getString(1).contentEquals(user.getUserPassword())) {
					Menu.clearScreen();
					System.out.println();
					System.out.println("### 로그인 성공! 계정 "+ user.getUserId() + "로 접속했습니다. ###");
					System.out.println();
					return 1;
				} else {
					Menu.clearScreen();
					System.out.println();
					System.out.println("### 비밀번호 불일치 ###");
					System.out.println();
					return 0;
				}
			}
			System.out.println();
			System.out.println("### 아이디가 없음 ###");
			System.out.println();
			return -1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return -2;
	}

	@Override
	public boolean userDelete(String userId) {
		conn = dataSource.getConn();
		String sql = "delete users where user_id = ?";
		
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return false;
	}
	
	
	@Override
	public String findInfo(UserVO user, int num) {
		conn = dataSource.getConn();
		String sql1 = "select user_password from users where user_id = ? and user_tel = ?";
		String sql2 = "select user_id from users where user_tel = ?";
		
		// 비밀번호 찾기 시 임시비밀번호로 변경하도록
//		String sql3 = "update users set user_password = ? where user_id = ? user_tel = ?";
//		UserVO user = new UserVO();
		try {
			
			switch (num) {
			case 1: {
				psmt = conn.prepareStatement(sql1);
				psmt.setString(1, user.getUserId());
				psmt.setString(2, user.getUserTel());
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					user.setUserPassword(rs.getString("user_password"));
				}
				return user.getUserPassword();
			}
			case 2: {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, user.getUserTel());
				
				rs = psmt.executeQuery();
				
				if(rs.next()) {
					user.setUserId(rs.getString("user_id"));
				}
				return user.getUserId();
			}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return " ";
	}

	@Override
	public boolean userUpdate(UserVO user) {
		conn = dataSource.getConn();
		String sql1 = "update users set user_password = ? where user_id = ?";
		String sql2 = "update users set user_email = ? where user_id  = ?";
		String sql3 = "update users set user_tel  = ? where user_id  = ?";
		int r = 0;
		try {
			switch(user.getUserName()){
			case "1" : {
				psmt = conn.prepareStatement(sql1);
				psmt.setString(1, user.getUserPassword());
				psmt.setString(2, user.getUserId());
				
				r = psmt.executeUpdate();
				break;
			}
			case "2" : {
				psmt = conn.prepareStatement(sql2);
				psmt.setString(1, user.getUserEmail());
				psmt.setString(2, user.getUserId());
				
				r = psmt.executeUpdate();
				break;
			}
			case "3" : {
				psmt = conn.prepareStatement(sql3);
				psmt.setString(1, user.getUserTel());
				psmt.setString(2, user.getUserId());
				
				r = psmt.executeUpdate();
				break;
			}
			}
			
			if (r > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
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
