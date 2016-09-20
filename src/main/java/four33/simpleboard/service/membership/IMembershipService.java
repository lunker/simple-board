package four33.simpleboard.service.membership;

import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;

public interface IMembershipService {

	// 회원가입
	public boolean signup(SignupUser userInfo);
	
	/**
	 * true : 중복 
	 * false : 중복 x
	 * @param userId
	 * @return
	 */
	public boolean checkIdDup(String userId);
	
	/**
	 * true: 중복
	 * false: 중복x
	 * @param nickname
	 * @return
	 */
	public boolean checkNicknameDup(String nickname);
	
	// 로그인
	public boolean login(LoginForm loginForm);
	
	public void logout(String userId);
	
	// 정보 수정
	public boolean updateUserInfo(User userInfo);
	
	// 탈퇴
	public boolean withdraw(String userId); 
	
	
}
