package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.User;


/**
 * http://----/simple-board/page/----
 * Get Page 
 * @author dongqlee
 *
 */
@Controller
@RequestMapping("/page")
public class PageController {

	@Autowired
	private IMembershipService membershipService;
	
	
	@RequestMapping("/login")
	public String loginPage(){
		return "membership/login";
	}
	
	@RequestMapping("/signup")
	public String signupPage(){
		return "membership/signup";
	}
	
	@RequestMapping("/article/write")
	public String writearticlePage(){
		return "article/write";
	}
	
	
	@RequestMapping("/mypage")
	public ModelAndView mypagePage(Model model, HttpServletRequest request ){
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("membership/mypage");
		
		Object attr = request.getSession().getAttribute("logined");
		
		if(attr!=null){
			boolean isLogined = (boolean) attr;
			if( isLogined){
				// 로그인 되어 있는 경우 
				
				String userId = (String) request.getSession().getAttribute("userId");
				User userInfo = membershipService.searchUserInfo(userId);
				
				
				mv.addObject("userInfo",userInfo);
			}
		}
		
		return mv;
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
	
	@RequestMapping("/board")
	public String boardPage(Model model, HttpServletRequest request){
		System.out.println("게시판 페이지 ");
 		
		/*
		if( ((boolean) request.getAttribute("logined")) == true){
			model.addAttribute("logined", true);
		}
		*/
		return "board";
	}
}
