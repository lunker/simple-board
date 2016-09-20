package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.ModifyUser;
import four33.simpleboard.types.Response;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;


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
	public Response ActionSignup(@RequestBody SignupUser userInfo){
		System.out.println("[Request] 회원가입");
		
		Response response = null;
		
		if(membershipService.signup(userInfo)){
			// 회원가입 성공 
			response = new Response("100", "회원가입 성공");
		}
		else{
			// 회원가입 실패 
			response = new Response("200", "회원가입 실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/checkid")
	@ResponseBody
	public Response ActionCheckIdDup(Model model, HttpServletRequest request){
		System.out.println("[Request] 아이디 중복 체크");
		Response response = null;

		String userId = request.getHeader("userId");
		
		System.out.println("id : " + userId);

		if(!membershipService.checkIdDup(userId)){
			System.out.println("사용가능한 아이디");
			response = new Response("100", "사용 가능한 ID");
		}
		else{
			System.out.println("중복된 아이디");
			response = new Response("200", "중복된 ID입니다.");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/checknickname")
	@ResponseBody
	public Response ActionCheckNicknameDup(Model model, HttpServletRequest request){
		System.out.println("[Request] 닉네임 중복 체크");
		Response response = null;

		String nickname = request.getHeader("nickname");
		
		System.out.println("id : " + nickname);

		if(!membershipService.checkNicknameDup(nickname)){
			System.out.println("사용가능한 닉네임");
			response = new Response("100", "사용 가능한 닉네임입니다.");
		}
		else{
			System.out.println("중복된 닉네임");
			response = new Response("200", "중복된 닉네임입니다.");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response ActionLogin(Model model, HttpServletRequest request){
		
		HttpSession session = getSession(request);
		Response response = null;
		
		String userId = request.getHeader("userId");
		String userPwd = request.getHeader("userPwd");
		
		System.out.println("id : " + userId);
		System.out.println("pwd : " + userPwd);
		
		User userInfo;
		
		if(membershipService.login(new LoginForm(userId, userPwd))){
			System.out.println("로그인성공");
			
			userInfo = membershipService.searchUserInfo(userId);
			
			response = new Response("100", "로그인성공");
			session.setAttribute("logined", true);
			session.setAttribute("userId", userId);
			session.setAttribute("userNickname", userInfo.getUserNickname());
		}
		else{
			System.out.println("로그인실패");
			response = new Response("200", "로그인실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public Response ActionUpdateUser(@RequestBody SignupUser userInfo, HttpServletRequest request){
		System.out.println("회원정보수정 request");
		HttpSession session = getSession(request);
		Response response = null;
		
		if(membershipService.updateUserInfo(userInfo)){
			User updatedUserInfo;
			updatedUserInfo = membershipService.searchUserInfo(userInfo.getUserId());
			
			response = new Response("100", "정보수정 성공");
			// update session info 
			session.setAttribute("userNickname", updatedUserInfo.getUserNickname());
		}
		else{
			response = new Response("200", "정보수정 실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public Response ActionWithdrawUser(HttpServletRequest request){
		System.out.println("회원탈퇴 request");
		HttpSession session = getSession(request);
		Response response = null;
		
		String userId = request.getHeader("userId");
		
		if(membershipService.withdraw(userId)){
			// 탈퇴 성공
			response = new Response("100", "회원탈퇴 성공");
			session.setAttribute("logined", false);
			session.setAttribute("userId", "");
			session.setAttribute("userNickname", "");
		}
		else{
			// 탈퇴 실패 
			response = new Response("200", "회원탈퇴 실패");
		}
		
		return response;
	}
	
	
	@RequestMapping(method=RequestMethod.GET, value="/logout")
	@ResponseBody
	public Response ActionLogout(HttpServletRequest request){
		System.out.println("로그아웃 request");
		
		HttpSession session = getSession(request);
		Response response = null;
		
		request.getHeader("userId");
		
		request.getSession().setAttribute("logined",false);
		request.getSession().setAttribute("userId","");
		response = new Response("100", "로그아웃 성공");
		
		return response;
	}
	
}
