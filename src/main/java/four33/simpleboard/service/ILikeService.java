package four33.simpleboard.service;

import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.LikeWrite;

public interface ILikeService {
	
	/**
	 * 게시판 종류 조회 
	 * @return
	 */
	public AppResponse selectLikesCount(int articleId);
	
	public AppResponse selectLike(int articleId, int likeUserNumId);
	
	public AppResponse doLike(LikeWrite likeWrite);
	
	public AppResponse undoLike(LikeWrite likeWrite);
	
}
