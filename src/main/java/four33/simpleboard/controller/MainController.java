package four33.simpleboard.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
		*/
		
		return "mainPage";
	}
}
