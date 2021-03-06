package four33.simpleboard.dao;

import org.apache.ibatis.annotations.Param;

import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.ArticleView;
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
public interface IArticleDao{
	
	/**
	 * 게시글 내용 조회 
	 * @param articleId
	 * @return
	 */
	public Article selectArticle(@Param("articleId") int articleId);
	
	public int insertArticle(WriteArticle article);
	
	public int insertPastArticle(WriteArticle article);
	
	public int updateArticle(ModifyArticle modifyArticle);
	
	public int updateArticleHits(@Param("articleId") int articleId, @Param("articleUserNumId") int articleUserNumId);
	
	public int updateArticleCommentsInc(@Param("articleId") int articleId);
	
	public int updateArticleCommentsDec(@Param("articleId") int articleId);
	
	public int updateArticleLikesInc(@Param("articleId") int articleId);
	
	public int updateArticleLikesDec(@Param("articleId") int articleId);

	public int deleteArticle(@Param("articleId") int articleId);
	
	/**
	 * 게시글 리스트 조회 
	 * @return
	 */
	public Article[] selectArticles(
			@Param("boardId") int boardId,
			@Param("condition") String condition,
			@Param("order") String order,
			@Param("printNum") int printNum,
			@Param("offset") int offset 
			);
	
	public int selectArticleCount(@Param("boardId") int boardId);
	
	public Article[] searchArticlesByCondition(
			
			@Param("boardId") int boardId,
			@Param("condition") String condition,
			@Param("order") String order,
			@Param("printNum") int printNum,
			@Param("offset") int offset ,
			@Param("searchQuery") String searchQuery, 
			@Param("searchCondition") int searchCondition, 
			@Param("searchRange") int searchRange
			);
	
	
}

