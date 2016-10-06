package four33.simpleboard.types;

public class LikeWrite {


	private int articleId;
	private int likeUserNumId;
	
	public LikeWrite(){}
	
	public LikeWrite(int articleId, int likeUserNumId) {
		
		this.articleId = articleId;
		this.likeUserNumId = likeUserNumId;
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
