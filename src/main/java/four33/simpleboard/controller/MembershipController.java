package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.Response;
import four33.simpleboard.types.User;

@Controller
@RequestMapping("/user")
public class MembershipController {
	
//	@Autowired
//	private IMembershipService membershipService;

	@Autowired
	private ApplicationContext context;
	
	@RequestMapping(method=RequestMethod.POST)
	public void ActionSignup(@RequestBody User userInfo){
		
		IMembershipService membershipService = context.getBean(IMembershipService.class);
		membershipService.signup(userInfo);
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public Response ActionLogin(HttpServletRequest request){
		Response response = null;
		
		String userId = request.getHeader("userId");
		String userPwd = request.getHeader("userPwd");
		System.out.println("id : " + userId);
		System.out.println("pwd : " + userPwd);
		
		IMembershipService membershipService = context.getBean(IMembershipService.class);
		if(membershipService.login(new LoginForm(userId, userPwd))){
			System.out.println("로그인성공");
			response = new Response("100", "로그인성공");
			
			request.getSession().setAttribute("login", true);
			request.getSession().setAttribute("userId", userId);
			request.getSession().setAttribute("login", true);
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
	
	
	/*
	@Autowired
	private ApplicationContext context;

	private Logger logger = LoggerFactory.getLogger(getClass());

	// http://---/sample/code/
	@RequestMapping
	public String actioneIndex(Model model) {
		
		// ApplicationContext 를 통해서 Bean을 획득하는 방법
		ICodeService codeService = context.getBean(ICodeService.class);
		List<Code> codeList = codeService.getCodeList();

		model.addAttribute("codeList", codeList);

		return "code/codeList"; // src/main/webapp/WEB-INF/jsp/code/codeList.jsp
	}

	// http://---/sample/code/list?cat=2
	@RequestMapping("/list")
	public String actioneList(Model model, @RequestParam("cat") int categoryNo) {

		logger.info("input categoryNo={}", categoryNo);

		// ApplicationContext 를 통해서 Bean을 획득하는 방법
		ICodeService codeService = context.getBean(ICodeService.class);
		List<Code> codeList = codeService.getCodeList(categoryNo);

		model.addAttribute("codeList", codeList);

		return "code/codeList"; // src/main/webapp/WEB-INF/jsp/code/codeList.jsp
	}

	// http://---/sample/code/view?code=1
	@RequestMapping("/view")
	public String actioneView(Model model, @RequestParam("code") int codeNo) {

		// ApplicationContext 를 통해서 Bean을 획득하는 방법
		ICodeService codeService = context.getBean(ICodeService.class);
		Code code = codeService.getCode(codeNo);

		model.addAttribute("codeNo", codeNo);
		model.addAttribute("code", code);

		return "code/codeView"; // src/main/webapp/WEB-INF/jsp/code/codeView.jsp
	}
	*/

	
	
}
