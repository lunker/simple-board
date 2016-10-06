package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.dao.IBoardDao;
import four33.simpleboard.dao.ILikeDao;
import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.ILikeService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.LikeWrite;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.AES;
import four33.simpleboard.utils.Constants;

@Service
public class LikeService implements ILikeService{
	
	@Autowired
	private ILikeDao likeDao;
	
	@Autowired
	private IArticleDao articleDao;
	
	@Override
	public AppResponse selectLikesCount(int articleId) {
		
		AppResponse response = null;
		
		int count = likeDao.selectLikesCount(articleId);

		response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 수 조회 성공", count);
		
		return response;
	}
	
	/**
	 * 좋아요 여부 체크 .
	 * true : 이미 좋아요 함 
	 */
	@Override
	public AppResponse selectLike(int articleId, int likeUserNumId) {
		
		AppResponse response = null;
		int result = likeDao.selectLike(articleId, likeUserNumId);
		
		if(result > 0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 중복", true);
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 중복", false);
		}
		
		return response;
	}

	@Override
	public AppResponse doLike(LikeWrite likeWrite) {
		
		AppResponse response = null;
		int result = likeDao.insertLike(likeWrite);
		
		if(result > 0){
			
			result = articleDao.updateArticleLikesInc(likeWrite.getArticleId());
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 실패");
		}
		
		return response;
	}
	
	@Override
	public AppResponse undoLike(LikeWrite likeWrite) {
		
		AppResponse response = null;
		int result = likeDao.deleteLike(likeWrite);
		
		if(result > 0){
			
			result = articleDao.updateArticleLikesDec(likeWrite.getArticleId());
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 취소 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "좋아요 취소 실패");
		}
		
		return response;
	}
}
