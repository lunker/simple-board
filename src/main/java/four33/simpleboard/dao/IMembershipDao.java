package four33.simpleboard.dao;

import org.apache.ibatis.annotations.Param;

import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;


/**
 * IDAO에 정의된 method명이 
 * 해당 mybatis xml의 query id에 정의되어 있어야 한다.
 * @author dongqlee
 *
 */
public interface IMembershipDao{
	
	public User selectUser(String userId);
	
	public int selectUserByNickname(String nickname);
	
	public int selectUserByPassword(@Param("userId") String userId, @Param("userPwd") String userPwd);
	
	public int insertUser(SignupUser userInfo);
	
	public int updateUser(SignupUser userInfo);
	
	public int deleteUser(@Param("userId") String userId);
}
