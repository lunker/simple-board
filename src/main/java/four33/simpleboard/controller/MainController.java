package four33.simpleboard.controller;

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
	public String mainPage(Model model){
		return "mainPage";
	}
}
