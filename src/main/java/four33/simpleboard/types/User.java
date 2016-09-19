package four33.simpleboard.types;

import java.sql.Date;

public class User {

	private int userNumId;
	private String userId;
	private String userPwd;
	private Date userBirthDt;
	private String userContactNum;
	private String userNickname;
	private Date userRegDt;
	
	public User(){
		
	}
	
	public User(int userNumId, String userId, String userPwd, Date userBirthDt, String userContactNum,
			String userNickname, Date userRegDt) {
	
		this.userNumId = userNumId;
		this.userId = userId;
		this.userPwd = userPwd;
		this.userBirthDt = userBirthDt;
		this.userContactNum = userContactNum;
		this.userNickname = userNickname;
		this.userRegDt = userRegDt;
	}
	
	
	public int getUserNumId() {
		return userNumId;
	}
	public void setUserNumId(int userNumId) {
		this.userNumId = userNumId;
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
	public Date getUserRegDt() {
		return userRegDt;
	}
	public void setUserRegDt(Date userRegDt) {
		this.userRegDt = userRegDt;
	}
	
	
	

	
}
