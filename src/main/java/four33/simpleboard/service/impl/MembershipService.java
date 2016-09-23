package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.utils.AES;
import four33.simpleboard.utils.Constants;

@Service
public class MembershipService  implements IMembershipService {

	@Autowired
	private IMembershipDao membershipDao;
	
	private AES aes;
	
	public MembershipService() {
		aes = AES.getInstance();
	}
	
	@Override
	public boolean signup(SignupUser userInfo) {
		
		System.out.println(userInfo.toString());

		int result = -1;
		
		// encrypt password
		userInfo.setUserPwd(aes.encrypt(userInfo.getUserPwd()));
		
		result = membershipDao.insertUser(userInfo);
		
		System.out.println("회원가입 결과: " + result);
		
		if(result==Constants.NONE){
			return Constants.FAIL;
		}
		else{
			return Constants.SUCCESS;
		}
	}
	
	/*
	 * true : 아이디 중복 
	 * false : 아이디 중복x
	 * @see four33.simpleboard.service.membership.IMembershipService#checkIdDup(java.lang.String)
	 */
	@Override
	public boolean checkIdDup(String userId) {

		boolean result;
		User user = membershipDao.selectUser(userId);
		
		if(user!=null)
			result = true;
		else
			result = false;
		
		return result;
	}

	@Override
	public boolean checkNicknameDup(String nickname) {
		// TODO Auto-generated method stub
		
		Object result;
		
		result = membershipDao.selectUserByNickname(nickname);
		
		if(result!=null){
			// nickname 이미 존재.
			if( (int) result>0){
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	@Override
	public AppResponse login(LoginForm loginForm) {
		
		System.out.println(loginForm.getUserId());
		
		AppResponse response;
		
		User selectedUserInfo =  membershipDao.selectUser(loginForm.getUserId());
		
		if(selectedUserInfo == null){
			// 존재하지 않은 아이디.
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "존재하지 않는 아이디 입니다.");
		}
		else{
			if( aes.decrypt(selectedUserInfo.getUserPwd()).equals(loginForm.getUserPwd())){
				// 로그인 성공
				response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "로그인 성공");
			}
			else{
				// 비밀번호가 틀린 경우 
				response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "비밀번호가 틀렸습니다.");
			}
		}
		return response;
	}

	@Override
	public boolean updateUserInfo(SignupUser userInfo) {
		// TODO Auto-generated method stub
		
		System.out.println(userInfo.toString());
		
		membershipDao.updateUser(userInfo);
		
		return true;
	}

	@Override
	public boolean withdraw(String userId) {
		// TODO Auto-generated method stub
		
		int result;
		
		result = membershipDao.deleteUser(userId);
		System.out.println("회원탈퇴 결과 : " + result);
		if(result > 0){
			return Constants.SUCCESS;
		}
		else
			return Constants.FAIL;
	}

	@Override
	public void logout(String userId) {
		// none
	}

	@Override
	public User searchUserInfo(String userId) {
		// TODO Auto-generated method stub
		
		User user = membershipDao.selectUser(userId);
		
		return user;
	}
}
