package co.proj.user;

public interface UserService {
	public boolean userInsert(UserVO user);
	public boolean userDelete(String userId);
	public boolean userUpdate(UserVO user);
	public int loginUser(UserVO user);
	
}
