package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.Board;
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
	
	@Autowired
	private IBoardService boardService;
	
	@Autowired
	private IArticleService articleService;
	
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
	public ModelAndView boardPage(Model model, HttpServletRequest request,
			
			@RequestParam(value="condition", required=false, defaultValue="article_reg_dt") String condition,
			@RequestParam(value="order", required=false, defaultValue="desc") String order,
			@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value="printNum", required=false, defaultValue="4") int printNum
			
			){
		System.out.println("[PAGE] 게시판 페이지 request");
		
		AppResponse response = null;
		
		response = articleService.selectArticles(condition, order, printNum, pageNum);
		
		System.out.println("조회된 게시글 수 : " + ((Article[])response.getData()).length);
 		ModelAndView mv = new ModelAndView();
 		mv.setViewName("board");
 		
		Object data = boardService.selectBoard();
		
		if(data!=null){
			Board[] tmp = (Board[])data;
			for(int i=0; i<tmp.length; i++)
				System.out.println(tmp[i].toString());
		}
		
		mv.addObject("response", response);
		return mv;
	}
}
