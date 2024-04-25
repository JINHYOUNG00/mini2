package co.proj.user.service;

import java.util.List;

public interface UserService {
	public boolean userInsert(UserVO user);
	public boolean userDelete(String userId);
	public boolean userUpdate(UserVO user);
	public int loginUser(UserVO user);
	
	public List<String> userIdCheck();
	
}
