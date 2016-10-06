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
import four33.simpleboard.service.ILikeService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.service.INoticeService;
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
	
	@Autowired
	private ILikeService likeService;
	
	@Autowired
	private INoticeService noticeService;
	
	/**
	 * 로그인 페이지
	 * @return
	 */
	@RequestMapping("/login")
	public String loginPage(){
		return "membership/login";
	}
	
	/**
	 * 회원가입 페이지
	 * @return
	 */
	@RequestMapping("/signup")
	public String signupPage(){
		return "membership/signup";
	}
	
	@RequestMapping("/article/write")
	public ModelAndView writeArticlePage(
			@RequestParam("boardId") int boardId
			){
		System.out.println("[PAGE] 게시글 작성 request");
		ModelAndView mv = new ModelAndView();
		
		// 일반 게시판
		if(boardId == 2){
			mv.setViewName("article/noticeWrite");
		}
		else{
			mv.setViewName("article/write");	
		}
		
		AppResponse response = boardService.selectBoard(boardId);
		if(response!=null){
			
		}
		mv.addObject("board", response.getData());
		
		return mv;
	}
	
	@RequestMapping("/header/menu")
	public ModelAndView headerMenu(){
		ModelAndView mv = new ModelAndView();
		AppResponse response = null;
		
		response = boardService.selectBoards();
		mv.setViewName("common/menuList");
		mv.addObject("response", response);
		
		return mv;
	}
	
	@RequestMapping(value="/article/likes", method=RequestMethod.GET)
	public ModelAndView likesComponent(@RequestParam("articleId") int articleId, @RequestParam("userNumId") int userNumId){
		System.out.println("[LIKE] component request");
		
		ModelAndView mv = new ModelAndView();
		AppResponse response = null;
		
		response = likeService.selectLike(articleId, userNumId);
		
		boolean isLiked = (boolean) response.getData();
		
		response = likeService.selectLikesCount(articleId);
		int likeCount = (int) response.getData();
		
		Map<String, Object> result = new HashMap<>();
		result.put("isLiked", isLiked);
		result.put("likeCount", likeCount);
		result.put("articleId", articleId);
		
		mv.setViewName("article/likes");
		mv.addObject("result",result);
		
		return mv;
	}
	
	@RequestMapping(value="/article/modify", method=RequestMethod.GET)
	public ModelAndView modifyArticlePage(
			@RequestParam("boardId") int boardId,
			@RequestParam("articleId") int articleId
			
			){
		
		ModelAndView mv = new ModelAndView();
		
		Article article = (Article) articleService.selectRawArticle(articleId);
		
		mv.setViewName("article/modify");
		
		mv.addObject("article", article);
		mv.addObject("boardId",boardId);
		
		return mv;
	}

	/**
	 * 게시글 뷰 페이지
	 * @param model
	 * @param articleId
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/article")
	public ModelAndView ArticleSelectArticle(
			HttpServletRequest request,
			@RequestParam("boardId") int boardId,
			@RequestParam("articleId") int articleId
			){
		
		System.out.println("[ARTICLE] 게시글 조회  request");
		System.out.println("[ARTICLE] 게시글 번호 : " + articleId);
		ModelAndView mv = new ModelAndView();
		HttpSession session=null;
		AppResponse response = null;
		
		mv.setViewName("article/content");
		session = Utils.getSession(request);
		int userNumId = (Integer) session.getAttribute("userNumId");
		
		response = articleService.selectArticle(articleId, userNumId);
		
		System.out.println("조회된 게시글 내용 : " + ((Article)response.getData()).getArticleContent());
		mv.addObject("response",response);
		mv.addObject("fromBoardId", boardId);

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
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param condition
	 * @param order
	 * @param pageNum
	 * @param printNum
	 * @param from : 0; none 1: P, 2: N
	 * @return
	 */
	@RequestMapping("/board")
	public ModelAndView boardPage(Model model, HttpServletRequest request,
			@RequestParam("boardId") int boardId,
			@RequestParam(value="condition", required=false, defaultValue="1") int condition,
			@RequestParam(value="order", required=false, defaultValue="1") int order,
			@RequestParam(value="pageNum", required=false, defaultValue="0") int pageNum,
			@RequestParam(value="printNum", required=false, defaultValue="10") int printNum
			
			){
		System.out.println("[PAGE] 게시판 리스트 페이지 request");

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
		
		response = articleService.selectArticles(boardId, strCondition, strOrder, printNum, pageNum);
		Object notices = null;
		// 첫 페이지 일때만 공지사항 조회 
		if(pageNum==0){
			
			notices = noticeService.selectPublicNotices();
			notices = ((AppResponse) notices).getData();
		}
		
		Object board = boardService.selectBoard(boardId);
 		
 		Map<String, Object> pagingInfo = new HashMap<String, Object>();
 		pagingInfo.put("order", order);
 		pagingInfo.put("condition", condition);
 		pagingInfo.put("pageNum", pageNum);
 		pagingInfo.put("printNum", printNum);
 		
 		
 		int count= (int) ((Map<String, Object>)response.getData()).get("count");
 		int start=0;
 		int end=0;
 		int limit = 1;
 		
 		start = (pageNum /3)*3;
 		end = (count/printNum);
 		
 		if(count - printNum * (pageNum+1) <0){
 			limit = 1;
 		}
 		else{
 			if(( count - printNum*(pageNum+1)) / printNum > 3  ){
 				limit = 3;
 			}
 			else{
 				limit = ( count - printNum*(pageNum+1)) / printNum ;
 			}
 		}
 		pagingInfo.put("start", start);
 		pagingInfo.put("end", end);
 		pagingInfo.put("limit", limit);
 		
 		
 		System.out.println("limit : " + limit);
 		ModelAndView mv = new ModelAndView();
 		mv.setViewName("board");
 		
 		// 현재 게시판에 대한 정보 
 		mv.addObject("board", board);
 		// 게시글 object list 
		mv.addObject("articles",  ((Map<String, Object>)response.getData()).get("items"));
		mv.addObject("count", ((Map<String, Object>)response.getData()).get("count"));
		
		// 공지사항 object list
		mv.addObject("notices", notices);
		
		// paging 조회정보
		mv.addObject("pagingInfo",pagingInfo);
		
		return mv;
	}
	
	/**
	 * 
	 * @param model
	 * @param request
	 * @param condition
	 * @param order
	 * @param pageNum
	 * @param printNum
	 * @param from : 0; none 1: P, 2: N
	 * @return
	 */
	@RequestMapping("/search")
	public ModelAndView boardSearchPage(
			@RequestParam("boardId") int boardId,
			@RequestParam(value="condition", required=false, defaultValue="1") int condition,
			@RequestParam(value="order", required=false, defaultValue="1") int order,
			@RequestParam(value="pageNum", required=false, defaultValue="0") int pageNum,
			@RequestParam(value="printNum", required=false, defaultValue="10") int printNum,
			@RequestParam(value="searchQuery", required=false, defaultValue="") String searchQuery,
			@RequestParam(value="searchCondition", required=false, defaultValue="0") int searchCondition,
			@RequestParam(value="searchRange", required=false, defaultValue="0") int searchRange
			){
		System.out.println("[PAGE] 게시글 검색 request");

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
		
		response = articleService.searchArticles(boardId, strCondition, strOrder, printNum, pageNum, searchQuery, searchCondition, searchRange);
		
		Object board = boardService.selectBoard(boardId);
 		
 		Map<String, Object> pagingInfo = new HashMap<String, Object>();
 		pagingInfo.put("order", order);
 		pagingInfo.put("condition", condition);
 		pagingInfo.put("pageNum", pageNum);
 		pagingInfo.put("printNum", printNum);
 		
 		Map<String, Object> searchingInfo = new HashMap<String, Object>();
 		searchingInfo.put("searchQuery", searchQuery);
 		searchingInfo.put("searchCondition", searchCondition);
 		searchingInfo.put("searchRange", searchRange);
 		
 		int count= (int) ((Map<String, Object>)response.getData()).get("count");
 		
 		int limit = 1;
 		
 		if(count - printNum * (pageNum+1) <0){
 			limit = 1;
 		}
 		else{
 			if(( count - printNum*(pageNum+1)) / printNum > 3  ){
 				limit = 3;
 			}
 			else{
 				limit = ( count - printNum*(pageNum+1)) / printNum ;
 			}
 		}
 		
 		pagingInfo.put("limit", limit);
 		System.out.println("limit : " + limit);
 		ModelAndView mv = new ModelAndView();
 		mv.setViewName("searchboard");
 		
 		// 현재 게시판에 대한 정보 
 		mv.addObject("board", board);
 		// 게시글 object list 
		mv.addObject("articles", ((Map<String, Object>)response.getData()).get("items"));
		mv.addObject("count", ((Map<String, Object>)response.getData()).get("count"));
		
		mv.addObject("searchingInfo", searchingInfo);
		// paging 조회정보
		mv.addObject("pagingInfo",pagingInfo);
		
		return mv;
	}
	
}
