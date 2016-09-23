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
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.Constants;


@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private IBoardService boardService;

	public HttpSession getSession(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		if(session == null){
			session = request.getSession(true);
		}
		return session;
	}
	
	/**
	 * 게시판 종류들을 조회
	 * @param request
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET, value="/category")
	@ResponseBody
	public AppResponse ActionWriteArticle(HttpServletRequest request){
		
		/*
		AppResponse response = null;
		
		Object data = boardService.selectBoard();
		
		if(data!=null){
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시판 종류 조회 성공", data);
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "게시판 종류 조회 실패", data);
		}
		
		return response;
		*/
		return null;
	}
	
	
}
