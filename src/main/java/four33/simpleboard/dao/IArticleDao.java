package four33.simpleboard.dao;

import four33.simpleboard.service.membership.IMembershipService;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;

/**
 * IDAO에 정의된 method명이 
 * 해당 mybatis xml의 query id에 정의되어 있어야 한다.
 * @author dongqlee
 *
 */
public interface IArticleDao{
	
	public void selectArticle();
	
	public void insertArticle();
	
	public void updateArticle();
	
	public void deleteArticle();
	
}
