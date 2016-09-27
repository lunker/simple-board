package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.utils.Constants;


@Controller
@RequestMapping("/user")
public class MembershipController {
	
	
	
	@Autowired
	private IMembershipService membershipService;

	public HttpSession getSession(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		if(session == null){
			session = request.getSession(true);
		}
		return session;
	}
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ActionSignup(@RequestBody SignupUser userInfo){
		System.out.println("[Request] 회원가입");
		
		AppResponse response = null;
		
		if(membershipService.signup(userInfo)){
			// 회원가입 성공 
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "회원가입 성공");
		}
		else{
			// 회원가입 실패 
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "회원가입 실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/checkid")
	@ResponseBody
	public AppResponse ActionCheckIdDup(Model model, HttpServletRequest request){
		System.out.println("[Request] 아이디 중복 체크");
		AppResponse response = null;

		String userId = request.getHeader("userId");
		
		System.out.println("id : " + userId);

		if(!membershipService.checkIdDup(userId)){
			System.out.println("사용가능한 아이디");
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "사용 가능한 ID");
		}
		else{
			System.out.println("중복된 아이디");
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "중복된 ID입니다.");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/checknickname")
	@ResponseBody
	public AppResponse ActionCheckNicknameDup(Model model, HttpServletRequest request, @RequestParam("userNickname") String userNickname){
		System.out.println("[Request] 닉네임 중복 체크");
		AppResponse response = null;

		if(!membershipService.checkNicknameDup(userNickname)){
			System.out.println("사용가능한 닉네임");
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "사용 가능한 닉네임입니다.");
		}
		else{
			System.out.println("중복된 닉네임");
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "중복된 닉네임입니다.");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public AppResponse ActionLogin(Model model, HttpServletRequest request){
		
		HttpSession session = getSession(request);
		AppResponse response = null;
		
		String userId = request.getHeader("userId");
		String userPwd = request.getHeader("userPwd");
		
		System.out.println("id : " + userId);
		System.out.println("pwd : " + userPwd);
	
		response = membershipService.login(new LoginForm(userId, userPwd));
		
		// 사용자 정보 조회
		if(response.getStatus().equals(Constants.STR_STATUS_CODE_SUCCESS)){
			
			User userInfo = membershipService.searchUserInfo(userId);

			// 세션에 정보 저장
			session.setAttribute("logined", true);
			session.setAttribute("userNumId", userInfo.getUserNumId());
			session.setAttribute("userId", userId);
			session.setAttribute("userNickname", userInfo.getUserNickname());
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public AppResponse ActionUpdateUser(@RequestBody SignupUser userInfo, HttpServletRequest request){
		System.out.println("회원정보수정 request");
		HttpSession session = getSession(request);
		AppResponse response = null;
		
		if(membershipService.updateUserInfo(userInfo)){
			User updatedUserInfo;
			updatedUserInfo = membershipService.searchUserInfo(userInfo.getUserId());
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "정보수정 성공");
			// update session info 
			session.setAttribute("userNickname", updatedUserInfo.getUserNickname());
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "정보수정 실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public AppResponse ActionWithdrawUser(HttpServletRequest request){
		System.out.println("회원탈퇴 request");
		HttpSession session = getSession(request);
		AppResponse response = null;
		
		String userId = request.getHeader("userId");
		
		if(membershipService.withdraw(userId)){
			// 탈퇴 성공
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "회원탈퇴 성공");
			session.setAttribute("logined", false);
			session.setAttribute("userId", "");
			session.setAttribute("userNickname", "");
		}
		else{
			// 탈퇴 실패 
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "회원탈퇴 실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/logout")
	@ResponseBody
	public AppResponse ActionLogout(HttpServletRequest request){
		System.out.println("로그아웃 request");
		
		HttpSession session = getSession(request);
		AppResponse response = null;
		
		// 세션 지움 
		session.invalidate();
		/*
		request.getHeader("userId");
		
		request.getSession().setAttribute("logined",false);
		request.getSession().setAttribute("userId","");
		*/
		
		response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "로그아웃 성공");
		
		return response;
	}
	
}
