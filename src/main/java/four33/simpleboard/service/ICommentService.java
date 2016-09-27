package four33.simpleboard.service;

import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.ModifyUser;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.CommentWrite;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;

public interface ICommentService {
	
	/**
	 * 댓글 쓰기
	 * @return
	 */
	public AppResponse writeComment(CommentWrite commentWrite);
	
	/**
	 * 댓글 수정
	 * @return
	 */
	public AppResponse modifyComment();
	
	/**
	 * 댓글 삭제 
	 * @return
	 */
	public AppResponse deleteComment();
	
	// 댓글 리스트 조회 
	public AppResponse selectComments(@Param("articleId") int articleId);
	
}
