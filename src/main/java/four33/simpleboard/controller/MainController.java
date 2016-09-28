package four33.simpleboard.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import four33.simpleboard.utils.Dummy;

@Controller
@RequestMapping
/**
 * http://----/simple-board/
 * @author dongqlee
 *
 */
public class MainController {

	@RequestMapping()
	public String mainPage(Model model, HttpServletRequest request){
		
		/*
		if((boolean) request.getSession().getAttribute("logined")){
			// 로그인 되어있는 상태.
			// 게시판을 조회한다.
			
			String userId = (String) request.getSession().getAttribute("userId");
			
			// 게시판 조회
			
		}
		
		
		HttpSession session = request.getSession(false);
		Cookie[] cookies = request.getCookies();
		
		if(cookies!=null){
			for(int i=0; i < cookies.length; i++)
				System.out.println("client가 보내는 쿠키 : " + cookies[i].getValue());	
		}
		else{
			System.out.println("쿠키가 없네.");
		}
		
		if(session == null){
			session = request.getSession(true);
			
//			System.out.println("생성된 session id: " + session.getId());
			
			System.out.println(session.getMaxInactiveInterval());
		}
		else{
//			System.out.println("기존에 있던 session id: " + session.getId());
		}
		
		*/
		
		return "mainPage";
	}
}
