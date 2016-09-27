package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.dao.IBoardDao;
import four33.simpleboard.dao.ICommentDao;
import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.ICommentService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.Comment;
import four33.simpleboard.types.CommentWrite;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.AES;
import four33.simpleboard.utils.Constants;

@Service
public class CommentService implements ICommentService{

	
	@Autowired
	private ICommentDao commentDao;

	@Override
	public AppResponse writeComment(CommentWrite commentWrite) {
		
		AppResponse response = null;
		
		int result = 0;
		result = commentDao.insertComment(commentWrite);
		
		if(result>0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "댓글 작성 실패");
		}
		
		return response;
	}

	@Override
	public AppResponse modifyComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse deleteComment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse selectComments(int articleId) {
		AppResponse response = null;
		
		Object comments = null;
		comments = commentDao.selectComments(articleId);
		
		if(comments != null){
			int count = ((Comment[])comments).length;
			System.out.println("댓글 조회 결과 : " + count);

			if(count !=0){
				response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공", comments);	
			}
			else{
				response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공", null);
			}
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "댓글 작성 실패", null);
		}
		
		return response;
	}

}
