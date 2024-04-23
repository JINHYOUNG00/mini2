package co.proj.user;

public class UserVO {
	private String userId;
	private String userName;
	private String userPassword;
	private String userEmail;
	private String userTel;
	
	public static String loginUserId;
	
	
	
	
	public UserVO() {
	}

	public UserVO(String userId, String userName, String userPassword, String userEmail, String userTel) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.userEmail = userEmail;
		this.userTel = userTel;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	
	@Override
	public String toString() {
		return "UserVO [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", userEmail="
				+ userEmail + ", userTel=" + userTel + "]";
	}
	
	

}
