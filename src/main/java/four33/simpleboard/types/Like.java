package four33.simpleboard.types;

public class Like {

	private int likeId;
	private int articleId;
	private int likeUserNumId;
	
	public Like(){}
	
	public Like(int likeId, int articleId, int likeUserNumId) {
		
		this.likeId = likeId;
		this.articleId = articleId;
		this.likeUserNumId = likeUserNumId;
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
	public int getLikeUserNumId() {
		return likeUserNumId;
	}
	public void setLikeUserNumId(int likeUserNumId) {
		this.likeUserNumId = likeUserNumId;
	}
	
	
	
	
	
	
}
