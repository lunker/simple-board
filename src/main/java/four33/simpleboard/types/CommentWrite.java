package four33.simpleboard.types;

public class CommentWrite {

	private int articleId;
	private String commentContent;
	private String commentUserNumId;
	
	public CommentWrite(){}
	
	public CommentWrite(int articleId, String commentContent, String commentUserNumId) {
		
		this.articleId = articleId;
		this.commentContent = commentContent;
		this.commentUserNumId = commentUserNumId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public String getCommentUserNumId() {
		return commentUserNumId;
	}
	public void setCommentUserNumId(String commentUserNumId) {
		this.commentUserNumId = commentUserNumId;
	}
	
//	private String commentRegDt;
	
	
	
	
}
