package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;

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
import four33.simpleboard.types.Response;
import four33.simpleboard.types.User;

@Controller
@RequestMapping("/user")
public class MembershipController {
	
	@Autowired
	private IMembershipService membershipService;

	@Autowired
	private ApplicationContext context;
	
	@RequestMapping(method=RequestMethod.POST)
	public void ActionSignup(@RequestBody User userInfo){
		
		
		membershipService.signup(userInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response ActionLogin(Model model, HttpServletRequest request){
		Response response = null;
		
		String userId = request.getHeader("userId");
		String userPwd = request.getHeader("userPwd");
		
		System.out.println("id : " + userId);
		System.out.println("pwd : " + userPwd);

		if(membershipService.login(new LoginForm(userId, userPwd))){
			System.out.println("로그인성공");
			response = new Response("100", "로그인성공");
			
			request.getSession().setAttribute("logined", true);
			request.getSession().setAttribute("userId", userId);
		}
		else{
			System.out.println("로그인실패");
			response = new Response("200", "로그인실패");
		}
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	public void ActionUpdateUser(@RequestBody User userInfo){
		
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	public void ActionWithdrawUser(String userId){
		
	}
	
	@RequestMapping(method=RequestMethod.GET, name="logout")
	public void ActionLogout(HttpServletRequest request, String userId){
		
	}
	
}
