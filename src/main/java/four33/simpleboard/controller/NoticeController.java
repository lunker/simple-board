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
import four33.simpleboard.service.INoticeService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.NoticeWrite;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.LikeWrite;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.Constants;


@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Autowired
	private IArticleService articleService;
	
	@Autowired
	private INoticeService noticeService;
	
	
	/**
	 * 
	 * 1. article write 
	 * 2. notice write
	 * @param request
	 * @param writeArticle : 공지사항 내용 
	 * @param noticeRange : 공지사항 노출 범위 
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST)
	@ResponseBody
	public AppResponse ActionWriteNotice(
			HttpServletRequest request, 
			@RequestBody WriteArticle writeArticle, 
			@RequestParam(value="noticeRange", required=false) char noticeRange){
		
		System.out.println("[NOTICE] 공지사항 작성 request");
		System.out.println("[NOTICE] 공지사항 작성 request : 공지 범위 : " + noticeRange);
		AppResponse response = null;
		
		int noticeArticleId = (int) articleService.writeArticle(writeArticle).getData();
		System.out.println("작성된 공지사항의 id : " + noticeArticleId);
		int numNoticeRange = 0;
		
		// c : 공지게시판만 노출  => boardId
		// a: 전체 게시판 노출  => 0 
		if(noticeRange == 'c'){
			// noticeRange <= boardid
			numNoticeRange = writeArticle.getBoardId();
		}
		
		System.out.println("[NOTICE] 공지사항 작성 request : 공지 범위 : " + noticeRange);
		response = noticeService.writeNotice(new NoticeWrite(noticeArticleId, numNoticeRange));

		return response;
	}
	
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public AppResponse ActionSelectWholeNotice(HttpServletRequest request){
		System.out.println("[NOTICE] 공지게시글(전체범위) 조회 request");
		
		AppResponse response = noticeService.selectPublicNotices();
		
		return response;
	}
}
