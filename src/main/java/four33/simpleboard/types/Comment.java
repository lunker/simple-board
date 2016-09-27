package four33.simpleboard.types;

import java.sql.Timestamp;

public class Comment {

	private int commentId;
	private int articleId;
	private int commentUserNumId;
	private String commentUserId;
	private String commentUserNickname;
	
	private String commentContent;
	private Timestamp commentRegDt;
	
	public Comment(){}
	
	public Comment(int commentId, int articleId, int commentUserNumId, String commentUserId, String commentUserNickname,
			String commentContent, Timestamp commentRegDt) {
		
		this.commentId = commentId;
		this.articleId = articleId;
		this.commentUserNumId = commentUserNumId;
		this.commentUserId = commentUserId;
		this.commentUserNickname = commentUserNickname;
		this.commentContent = commentContent;
		this.commentRegDt = commentRegDt;
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getCommentUserNumId() {
		return commentUserNumId;
	}
	public void setCommentUserNumId(int commentUserNumId) {
		this.commentUserNumId = commentUserNumId;
	}
	public String getCommentUserId() {
		return commentUserId;
	}
	public void setCommentUserId(String commentUserId) {
		this.commentUserId = commentUserId;
	}
	public String getCommentUserNickname() {
		return commentUserNickname;
	}
	public void setCommentUserNickname(String commentUserNickname) {
		this.commentUserNickname = commentUserNickname;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Timestamp getCommentRegDt() {
		return commentRegDt;
	}
	public void setCommentRegDt(Timestamp commentRegDt) {
		this.commentRegDt = commentRegDt;
	}
	
	
	
}
