package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * http://----/simple-board/page/----
 * Get Page 
 * @author dongqlee
 *
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@RequestMapping("/login")
	public String loginPage(){
		return "membership/login";
	}
	@RequestMapping("/signup")
	public String signupPage(){
		return "membership/signup";
	}
	@RequestMapping("/common/header")
	public String commonHeader(Model model, HttpServletRequest request){
		System.out.println("haewsarf");
 		
		/*
		if( ((boolean) request.getAttribute("logined")) == true){
			model.addAttribute("logined", true);
		}
		*/
		
		
		return "common/header";
	}
}
