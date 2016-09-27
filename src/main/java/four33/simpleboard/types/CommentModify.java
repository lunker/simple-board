package four33.simpleboard.types;

public class CommentModify {

	private int commentId;
	private String commentContent;

	public CommentModify() {
	}

	public CommentModify(int commentId, String commentContent) {
		this.commentId = commentId;
		this.commentContent = commentContent;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

}
