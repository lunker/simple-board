package four33.simpleboard.dao;

import org.apache.ibatis.annotations.Param;

import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.ModifyArticle;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;

/**
 * IDAO에 정의된 method명이 
 * 해당 mybatis xml의 query id에 정의되어 있어야 한다.
 * @author dongqlee
 *
 */
public interface ICommentDao{
	
	public void selectComment(@Param("articleId") int articleId);
	
	public void insertComment(Article article);
	
	public void updateComment(ModifyArticle modifyArticle);
	
	public void deleteComment(@Param("articleId") int articleId);
	
}
