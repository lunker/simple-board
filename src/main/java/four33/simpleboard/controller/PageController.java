package four33.simpleboard.controller;

import org.springframework.stereotype.Controller;
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
	public String sginupPage(){
		return "membership/signup";
	}
	
}
