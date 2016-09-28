package four33.simpleboard.controller;


import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.User;
import four33.simpleboard.utils.Constants;
import four33.simpleboard.utils.Utils;


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
	public String writeArticlePage(){
		return "article/write";
	}
	
	@RequestMapping(value="/article/modify/{articleId}", method=RequestMethod.GET)
	public ModelAndView modifyArticlePage(@PathVariable("articleId") int articleId){
		
		ModelAndView mv = new ModelAndView();
		
		Article article = (Article) articleService.selectRawArticle(articleId);
		
		mv.setViewName("article/modify");
		mv.addObject("article", article);
		
		return mv;
	}
	
/*
	@RequestMapping("/common/header")
	public String commonHeader(Model model, HttpServletRequest request){
		System.out.println("haewsarf");
		
		return "common/header";
	}*/
	
	/**
	 * 게시글 뷰 페이지
	 * @param model
	 * @param articleId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/article/{articleId}")
	public ModelAndView ArticleSelectArticle(Model model, HttpServletRequest request, @PathVariable("articleId") int articleId){
		
		System.out.println("[ARTICLE] 게시글 조회  request");
		System.out.println("[ARTICLE] 게시글 번호 : " + articleId);
		ModelAndView mv = new ModelAndView();
		HttpSession session=null;
		AppResponse response = null;
		
		mv.setViewName("article/content");
		session = Utils.getSession(request);
//		String userNumId = (String) session.getAttribute("userNumId");
		int userNumId = (Integer) session.getAttribute("userNumId");
		
		response = articleService.selectArticle(articleId, userNumId);
		
		System.out.println("조회된 게시글 내용 : " + ((Article)response.getData()).getArticleContent());
		mv.addObject("response",response);

		return mv;
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

	@RequestMapping("/searchboard")
	public ModelAndView searchboardPage(Model model, HttpServletRequest request,
			
			@RequestParam(value="condition", required=false, defaultValue="1") int condition,
			@RequestParam(value="order", required=false, defaultValue="1") int order,
			@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value="printNum", required=false, defaultValue="10") int printNum
			
			){
		System.out.println("[PAGE] 게시판 페이지 request");

		AppResponse response = null;
		String strCondition="";
		String strOrder="";
		
		if(condition == Constants.NUM_PAGING_CONDITION_REG_DT){
			strCondition = Constants.STR_PAGING_CONDITION_REG_DT;
		}
		else if(condition == Constants.NUM_PAGING_CONDITION_HITS){
			strCondition = Constants.STR_PAGING_CONDITION_HITS;
		}
		else{
			strCondition = Constants.STR_PAGING_CONDITION_LIKES;
		}
		
		if(order == Constants.NUM_PAGING_ORDER_DESC){
			strOrder = Constants.STR_PAGING_ORDER_DESC;
		}
		else{
			strOrder = Constants.STR_PAGING_ORDER_ASC;
		}
		
		response = articleService.selectArticles(strCondition, strOrder, printNum, pageNum);
		
		// send response
 		ModelAndView mv = new ModelAndView();
 		mv.setViewName("board");
 		
 		/*
		Object data = boardService.selectBoard();
		
		if(data!=null){
			Board[] tmp = (Board[])data;
			for(int i=0; i<tmp.length; i++)
				System.out.println(tmp[i].toString());
		}
		*/
 		
 		
 		System.out.println("게시글 조회 결과 : " + ((Article[])((Map<String, Object>)response.getData()).get("articles")).length + "개");
 		
 		Map<String, Object> pagingInfo = new HashMap<String, Object>();
 		pagingInfo.put("order", order);
 		pagingInfo.put("condition", condition);
 		pagingInfo.put("pageNum", pageNum);
 		pagingInfo.put("printNum", printNum);
 		
		
		mv.addObject("response", response);
		mv.addObject("pagingInfo",pagingInfo);
		
		return mv;
	}
	@RequestMapping("/board")
	public ModelAndView boardPage(Model model, HttpServletRequest request,
			
			@RequestParam(value="condition", required=false, defaultValue="1") int condition,
			@RequestParam(value="order", required=false, defaultValue="1") int order,
			@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value="printNum", required=false, defaultValue="10") int printNum
			
			){
		System.out.println("[PAGE] 게시판 페이지 request");

		AppResponse response = null;
		String strCondition="";
		String strOrder="";
		
		if(condition == Constants.NUM_PAGING_CONDITION_REG_DT){
			strCondition = Constants.STR_PAGING_CONDITION_REG_DT;
		}
		else if(condition == Constants.NUM_PAGING_CONDITION_HITS){
			strCondition = Constants.STR_PAGING_CONDITION_HITS;
		}
		else{
			strCondition = Constants.STR_PAGING_CONDITION_LIKES;
		}
		
		if(order == Constants.NUM_PAGING_ORDER_DESC){
			strOrder = Constants.STR_PAGING_ORDER_DESC;
		}
		else{
			strOrder = Constants.STR_PAGING_ORDER_ASC;
		}
		
		response = articleService.selectArticles(strCondition, strOrder, printNum, pageNum);
		
		// send response
 		ModelAndView mv = new ModelAndView();
 		mv.setViewName("board");
 		
 		/*
		Object data = boardService.selectBoard();
		
		if(data!=null){
			Board[] tmp = (Board[])data;
			for(int i=0; i<tmp.length; i++)
				System.out.println(tmp[i].toString());
		}
		*/
 		
 		
 		System.out.println("게시글 조회 결과 : " + ((Article[])((Map<String, Object>)response.getData()).get("articles")).length + "개");
 		
 		Map<String, Object> pagingInfo = new HashMap<String, Object>();
 		pagingInfo.put("order", order);
 		pagingInfo.put("condition", condition);
 		pagingInfo.put("pageNum", pageNum);
 		pagingInfo.put("printNum", printNum);
 		
		
		mv.addObject("response", response);
		mv.addObject("pagingInfo",pagingInfo);
		
		return mv;
	}
}
