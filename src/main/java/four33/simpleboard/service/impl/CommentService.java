package four33.simpleboard.service.impl;

import java.util.HashMap;
import java.util.Map;

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
import four33.simpleboard.types.CommentModify;
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
	
	@Autowired
	private IArticleDao articleDao;
	

	@Override
	public AppResponse writeComment(CommentWrite commentWrite) {
		
		AppResponse response = null;
		
		int insertResult = 0;
		int updateResult = 0;
		
		insertResult = commentDao.insertComment(commentWrite);
		
		if(insertResult>0){
			
			updateResult = articleDao.updateArticleCommentsInc(commentWrite.getArticleId());
			if(updateResult>0){
				response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공");	
			}
			else{
				// delete.
				// rollback?
			}
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "댓글 작성 실패");
		}
		
		return response;
	}

	@Override
	public AppResponse modifyComment(CommentModify commentModify) {
		
		AppResponse response = null;
		
		int result = 0;
		
		result = commentDao.updateComment(commentModify);
		
		if(result>0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "수정 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "수정 실패");
		}
		return response;
	}

	@Override
	public AppResponse deleteComment(int articleId, int commentId) {
		
		AppResponse response = null;
		
		int result = 0;
		result = commentDao.deleteComment(commentId);
				
		
		if(result > 0){
			System.out.println("댓글 삭제 성공");
			
			articleDao.updateArticleCommentsDec(articleId);
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공");	
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "댓글 작성 실패");
		}
		
		return response;
	}

	@Override
	public AppResponse selectComments(int articleId, int commentPageNum, int commentPrintNum) {
		AppResponse response = null;
		Map<String, Object> result = new HashMap<>();
		
		Object comments = null;
		comments = commentDao.selectComments(articleId, 0, commentPrintNum*(commentPageNum+1));
		
		if(comments != null){
			int count = ((Comment[])comments).length;
			System.out.println("댓글 조회 결과 : " + count);
			
			count = commentDao.selectCommentsCount(articleId);
			if(count !=0){
				result.put("comments", comments);
				result.put("count", count);
				
				response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공", result);	
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
	
	public AppResponse selectCommenstCount(int articleId){
		AppResponse response = null;
		Map<String, Object> result = new HashMap<>();
		
		int count = commentDao.selectCommentsCount(articleId);
		
		if(count !=0 ){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "댓글 작성 성공", count);	
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "댓글 작성 실패", count);
		}
		
		return response;
	}

}

