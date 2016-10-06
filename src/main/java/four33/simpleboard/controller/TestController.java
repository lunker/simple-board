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

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.Dummy;


@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private IArticleDao articleDao;

	public HttpSession getSession(HttpServletRequest request){
		
		HttpSession session = request.getSession(false);
		
		if(session == null){
			session = request.getSession(true);
		}
		return session;
	}
	
	/**
	 * 게시글 더미 데이터 생성
	 * @param request
	 */
	@RequestMapping(method=RequestMethod.GET, value="/dummy/article")
	public void ActionWriteArticle(HttpServletRequest request){
		
		String title = "테스트용 게시글";
		String content = "테스트 중입니다";
		
		WriteArticle article = null;
		
		for(int i=10000; i<100000;i++){
			article = new WriteArticle(1,2, title+i, content+i);
			articleDao.insertArticle(article);
		}
		System.out.println("더미 데이터 생성완료");
		return;
	}
	
	/**
	 * 게시글 더미 데이터 생성
	 * @param request
	 */
	@RequestMapping(method=RequestMethod.GET, value="/dummy/dayarticle")
	public void dayarticle(HttpServletRequest request){
		
		String title = "테스트용 이전 날짜 게시글";
		String content = "테스트 중입니다";
		
		WriteArticle article = null;
		
		for(int i=0; i<300;i++){
			article = new WriteArticle(1,2, title+i, content);
			articleDao.insertPastArticle(article);
		}
		
		System.out.println("더미 데이터 생성완료");
		return;
	}
	
	/**
	 * 게시글 더미 데이터 생성
	 * @param request
	 */
	@RequestMapping(method=RequestMethod.GET, value="/dummy/weekarticle")
	public void weekarticle(HttpServletRequest request){
		
		String title = "테스트용 이전 날짜 게시글";
		String content = "테스트 중입니다";
		
		WriteArticle article = null;
		
		for(int i=0; i<300;i++){
			article = new WriteArticle(1,2, title+i, content);
			articleDao.insertPastArticle(article);
		}
		
		System.out.println("더미 데이터 생성완료");
		return;
	}
	
	/**
	 * 게시글 더미 데이터 생성
	 * @param request
	 */
	@RequestMapping(method=RequestMethod.GET, value="/dummy/montharticle")
	public void montharticle(HttpServletRequest request){
		
		String title = "테스트용 이전 날짜 게시글";
		String content = "테스트 중입니다";
		
		WriteArticle article = null;
		
		for(int i=0; i<300;i++){
			article = new WriteArticle(1,2, title+i, content);
			articleDao.insertPastArticle(article);
		}
		
		System.out.println("더미 데이터 생성완료");
		return;
	}
	
	
	
}
