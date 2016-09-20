package four33.simpleboard.types;

import java.sql.Date;

public class SignupUser {
	
	private String userId;
	private String userPwd;
	private Date userBirthDt;
	private String userContactNum;
	private String userNickname;
	
	public SignupUser(){
		
	}
	
	public SignupUser(String userId, String userPwd, Date userBirthDt, String userContactNum,
			String userNickname) {
	
		
		this.userId = userId;
		this.userPwd = userPwd;
		this.userBirthDt = userBirthDt;
		this.userContactNum = userContactNum;
		this.userNickname = userNickname;
		
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public Date getUserBirthDt() {
		return userBirthDt;
	}
	public void setUserBirthDt(Date userBirthDt) {
		this.userBirthDt = userBirthDt;
	}
	public String getUserContactNum() {
		return userContactNum;
	}
	public void setUserContactNum(String userContactNum) {
		this.userContactNum = userContactNum;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	
		StringBuilder sb = new StringBuilder();
		
		sb.append("user id : " + userId);
		sb.append("\nuser pwd : " + userPwd);
		sb.append("\nuser nickname : " + userNickname);
		sb.append("\nser contact num : " + userContactNum);
		sb.append("\nuser birth dt : " + userBirthDt);
		
		return sb.toString();
	}
	
	
}
