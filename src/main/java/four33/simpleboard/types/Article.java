package four33.simpleboard.types;

import java.sql.Timestamp;

public class Article {

	private int articleId;
	private int boardId;
	private int articleUserNumId;
	private String articleUserNickname;
	
	private String articleTitle;
	private String articleContent;
	private int articleComments;
	private Timestamp articleRegDt;
	private Timestamp articleModifyDt;
	private int articleHits;
	private int articleLikes;
	
	public Article(){}
	
	public Article(int articleId, int boardId, int articleUserNumId, String articleUserNickname, String articleTitle,
			String articleContent, int articleComments, Timestamp articleRegDt, Timestamp articleModifyDt, int articleHits,
			int articleLikes) {
		this.articleId = articleId;
		this.boardId = boardId;
		this.articleUserNumId = articleUserNumId;
		this.articleUserNickname =articleUserNickname;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleComments = articleComments;
		this.articleRegDt = articleRegDt;
		this.articleModifyDt = articleModifyDt;
		this.articleHits = articleHits;
		this.articleLikes = articleLikes;
	}
	
	public String getArticleUserNickname() {
		return articleUserNickname;
	}

	public void setArticleUserNickname(String articleUserNickname) {
		this.articleUserNickname = articleUserNickname;
	}

	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getBoardId() {
		return boardId;
	}
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public int getArticleUserNumId() {
		return articleUserNumId;
	}
	public void setArticleUserNumId(int articleUserNumId) {
		this.articleUserNumId = articleUserNumId;
	}
	
	public String getArticleTitle() {
		return articleTitle;
	}
	public void setArticleTitle(String articleTitle) {
		this.articleTitle = articleTitle;
	}
	public String getArticleContent() {
		return articleContent;
	}
	public void setArticleContent(String articleContent) {
		this.articleContent = articleContent;
	}
	public int getArticleComments() {
		return articleComments;
	}
	public void setArticleComments(int articleComments) {
		this.articleComments = articleComments;
	}
	public Timestamp getArticleRegDt() {
		return articleRegDt;
	}
	public void setArticleRegDt(Timestamp articleRegDt) {
		this.articleRegDt = articleRegDt;
	}
	public Timestamp getArticleModifyDt() {
		return articleModifyDt;
	}
	public void setArticleModifyDt(Timestamp articleModifyDt) {
		this.articleModifyDt = articleModifyDt;
	}
	public int getArticleHits() {
		return articleHits;
	}
	public void setArticleHits(int articleHits) {
		this.articleHits = articleHits;
	}
	public int getArticleLikes() {
		return articleLikes;
	}
	public void setArticleLikes(int articleLikes) {
		this.articleLikes = articleLikes;
	}
	
	
	
}
