package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;


@Controller
@RequestMapping("/article")
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;

	public HttpSession getSession(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		if(session == null){
			session = request.getSession(true);
		}
		return session;
	}
	
	/*
	public String getUserId(HttpSession session){
		String userId="";
		if(session!=null){

			userId = (String) session.getAttribute("userId");
			if(userId==null){
				return "";
			}
			else
				return userId;
		}
		else
			return "";
	}
	*/
	
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ActionWriteArticle(HttpServletRequest request, @RequestBody WriteArticle writeArticle){
		System.out.println("[ARTICLE] 게시글 작성 request");
		AppResponse response = null;
		
		response = articleService.writeArticle(writeArticle);
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/list")
	public String ActionSelectArticles(HttpServletRequest request,

			@RequestParam(value="condition", required=false, defaultValue="article_reg_dt") String condition,
			@RequestParam(value="order", required=false, defaultValue="desc") String order,
			@RequestParam(value="pageNum", required=false, defaultValue="1") int pageNum,
			@RequestParam(value="printNum", required=false, defaultValue="4") int printNum
			
			){
		System.out.println("pageunm: " + pageNum);
		System.out.println("printNum: " + printNum);
		/*
		System.out.println("[ARTICLE] 게시글 리스트 조회 request");
		AppResponse response = null;
		
		response = articleService.selectArticles(condition, order, printNum, pageNum);

		return response;
		*/
		
		return "forward:/page/board?pageNum="+pageNum+"&printNum="+printNum;
		
		
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public AppResponse ActionModifyArticle(HttpServletRequest request){
		
		AppResponse response = null;

		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public AppResponse ActionDeleteArticle(HttpServletRequest request){
		
		AppResponse response = null;

		return response;
	}
	
}
