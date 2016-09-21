package four33.simpleboard.types;

public class Like {

	private int likeId;
	private int articleId;
	private String userId;
	
	public Like(){}
	
	public Like(int likeId, int articleId, String userId) {
		super();
		this.likeId = likeId;
		this.articleId = articleId;
		this.userId = userId;
	}
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
	
	
}
