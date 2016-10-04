package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.service.INoticeService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.ModifyArticle;
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

	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ActionWriteArticle(HttpServletRequest request, 
			@RequestBody WriteArticle writeArticle
			){
		System.out.println("[ARTICLE] 게시글 작성 request");
		AppResponse response = null;
		
		response = articleService.writeArticle(writeArticle);
		
		return response;
	}
	
	/**
	 * 게시글 수정 
	 * @param request
	 * @param modifyArticle
	 * @return
	 */
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public AppResponse ActionModifyArticle(HttpServletRequest request,
			@RequestBody ModifyArticle modifyArticle
			
			){
		
		System.out.println("[ARTICLE] 게시글 수정 request");
		System.out.println(modifyArticle.getArticleContent());
		AppResponse response = null;

		response = articleService.modifyArticle(modifyArticle);
		
		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/{articleId}")
	@ResponseBody
	public AppResponse ActionDeleteArticle(HttpServletRequest request, 
			@PathVariable("articleId") int articleId
			){
		System.out.println("[ARTICLE] 게시글 삭제 request");
		
		AppResponse response = null;

		response = articleService.deleteArticle(articleId);
		
		return response;
	}
}
