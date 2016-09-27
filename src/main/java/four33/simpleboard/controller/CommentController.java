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
import org.springframework.web.servlet.ModelAndView;

import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.ICommentService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.CommentModify;
import four33.simpleboard.types.CommentWrite;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;


@Controller
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	private ICommentService commentService;
	
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ArticleWriteComment(HttpServletRequest request, @RequestBody CommentWrite commentWrite){
		System.out.println("[COMMENT] 댓글 작성 request");
		AppResponse response = null;
		
		response = commentService.writeComment(commentWrite);
		
		return response;
	}

	@RequestMapping(method=RequestMethod.GET, value="/{articleId}")
	public ModelAndView ArticleSelectComment(HttpServletRequest request, @PathVariable("articleId") int articleId){
		System.out.println("[COMMENT][게시글] "+ articleId +" 댓글 조회 request");
		ModelAndView mv = new ModelAndView();
		
		AppResponse response = null;
		
		response = commentService.selectComments(articleId);
		
		mv.addObject("response", response);
		mv.setViewName("article/comment");
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.PUT)
	@ResponseBody
	public AppResponse ActionModifyComment(HttpServletRequest request, @RequestParam("commentId") int commentId, @RequestParam("commentContent") String commentContent){
		System.out.println("[COMMENT]댓글 수정 request");
		AppResponse response = null;
		
		response = commentService.modifyComment(new CommentModify(commentId, commentContent));

		return response;
	}
	
	@RequestMapping(method=RequestMethod.DELETE)
	@ResponseBody
	public AppResponse ActionDeleteComment(
			HttpServletRequest request, 
			@RequestParam("articleId") int articleId , 
			@RequestParam("commentId") int commentId){
		System.out.println("[COMMENT] 댓글 삭제");
		
		AppResponse response = null;
		
		response = commentService.deleteComment(articleId, commentId);

		return response;
	}
	
}
