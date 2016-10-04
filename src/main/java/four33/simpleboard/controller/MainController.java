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
		return "mainPage";
	}
}
