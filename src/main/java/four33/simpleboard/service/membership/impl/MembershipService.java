package four33.simpleboard.service.membership.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.User;
import four33.simpleboard.utils.Constants;

@Service
public class MembershipService  implements IMembershipService {

	@Autowired
	private IMembershipDao membershipDao;
	
	@Override
	public boolean signup(User userInfo) {

		int result = -1;
//		result = membershipDao.insertUser(userInfo);
		
		if(result==Constants.NONE){
			return Constants.FAIL;
		}
		else{
			return Constants.SUCCESS;
		}
	}

	@Override
	public boolean login(LoginForm loginForm) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean updateUserInfo(User userInfo) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean withdraw(String userId) {
		// TODO Auto-generated method stub
		return true;
	}

}
