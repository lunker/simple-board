package four33.simpleboard.service;

import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.ModifyArticle;
import four33.simpleboard.types.ModifyUser;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;

public interface IArticleService {
	
	// 글 쓰기
	// 글 수정
	// 글 삭제 
	/**
	 * 게시글 쓰기 
	 * @return
	 */
	public AppResponse writeArticle(WriteArticle writeArticle);
	
	/**
	 * 게시글 수정
	 * @return
	 */
	public AppResponse modifyArticle(ModifyArticle writeArticle);
	
	/**
	 * 게시글 삭제
	 * @return
	 */
	public AppResponse deleteArticle(@Param("articleId") int articleId);
	
	/**
	 * 게시글 조회 
	 * @return
	 */
	public AppResponse selectArticle(int articleId, int userNumId);
	
	public Object selectRawArticle(int articleId);
	
	/**
	 * 게시글 리스트 조회
	 * @return
	 */
	public AppResponse selectArticles(String condition, String order, int printNum, int pageNum); 
	
}
