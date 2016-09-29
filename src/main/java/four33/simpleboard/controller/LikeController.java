package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.ILikeService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.LikeWrite;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.Constants;


@Controller
@RequestMapping("/like")
public class LikeController {
	
	@Autowired
	private ILikeService likeService;
	
	@RequestMapping(value="/do",method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ActionDoLike(HttpServletRequest request, @RequestBody LikeWrite likeWrite){
		
		AppResponse response = likeService.doLike(likeWrite);
		
		return response;
	}
	@RequestMapping(value="/undo", method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ActionUndoLike(HttpServletRequest request, @RequestBody LikeWrite likeWrite){
		
		AppResponse response = likeService.undoLike(likeWrite);
		
		return response;
	}
}
