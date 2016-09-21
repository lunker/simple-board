package four33.simpleboard.service.membership.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.corba.se.impl.orbutil.closure.Constant;

import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.ModifyUser;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.utils.Constants;

@Service
public class MembershipService  implements IMembershipService {

	@Autowired
	private IMembershipDao membershipDao;
	
	@Override
	public boolean signup(SignupUser userInfo) {
		
		System.out.println(userInfo.toString());

		int result = -1;
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
	public boolean login(LoginForm loginForm) {
		System.out.println(loginForm.getUserId());
		
		Object result;
		
		result = membershipDao.selectUserByPassword(loginForm.getUserId(), loginForm.getUserPwd());
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
		/*
		User registeredInfo = membershipDao.selectUserByPassword(loginForm.getUserId(), loginForm.getUserPwd());
		
		if(registeredInfo!=null &&  registeredInfo.getUserPwd().equals( loginForm.getUserPwd())){
			
			result = true;
		}
		else{
			result = false;
//			return null;
		}
		
		return result;
		*/
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
