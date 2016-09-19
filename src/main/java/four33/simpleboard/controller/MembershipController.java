package four33.simpleboard.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.LoginForm;
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
	public void ActionLogin(@RequestBody LoginForm loginForm){
		IMembershipService membershipService = context.getBean(IMembershipService.class);
		membershipService.login(null);
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
