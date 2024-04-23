package co.proj.user.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
					System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("로그인 성공");
					System.out.println(user.getUserId() + " 계정에 접속했습니다.");
					System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					return 1;
				} else {
					Menu.clearScreen();
					System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					System.out.println("비밀번호 불일치");
					System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
					return 0;
				}
			}
			System.out.println("아이디가 없음");
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
		dataSource.getConn();
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean userUpdate(UserVO user) {
		dataSource.getConn();
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
