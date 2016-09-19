package four33.simpleboard.service.membership;

import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.User;

public interface IMembershipService {

	// 회원가입
	public boolean signup(User userInfo);
	
	// 로그인
	public boolean login(LoginForm loginForm);
	
	// 정보 수정
	public boolean updateUserInfo(User userInfo);
	
	// 탈퇴
	public boolean withdraw(String userId); 
	
}
