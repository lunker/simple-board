package four33.simpleboard.dao;

import org.apache.ibatis.annotations.Param;

import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.ArticleView;
import four33.simpleboard.types.Like;
import four33.simpleboard.types.LikeWrite;
import four33.simpleboard.types.ModifyArticle;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;

/**
 * IDAO에 정의된 method명이 
 * 해당 mybatis xml의 query id에 정의되어 있어야 한다.
 * @author dongqlee
 *
 */
public interface ILikeDao{
	
	/**
	 * 게시글의 좋아요 수 조회
	 * @return
	 */
	public int selectLikesCount(@Param("articleId") int articleId);
	
	/**
	 * 
	 * 좋아요 여부 조회
	 * @param articleId
	 * @return
	 */
	public int selectLike(@Param("articleId") int articleId, @Param("likeUserNumId") int likeUserNumId);
	
	/**
	 * 
	 * @return
	 */
	public Like[] selectLikes(@Param("articleId") int articleId);
	
	/**
	 * 좋아요 
	 * @param likeWrite
	 * @return
	 */
	public int insertLike(LikeWrite likeWrite);
	
	/**
	 * 좋아요 취소
	 * @param likeWrite
	 * @return
	 */
	public int deleteLike(LikeWrite likeWrite);
}
