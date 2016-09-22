package four33.simpleboard.types;

import java.sql.Date;

public class WriteArticle {

	private int articleId;
	private int boardId;
	private int articleUserNumId;
	private int articleNo;
	private String articleTitle;
	private String articleContent;
	private int articleComments;
	private Date articleRegDt;
	private Date articleModifyDt;
	private int articleHits;
	private int articleLikes;
	
	public WriteArticle(){}
	
	public WriteArticle(int articleId, int boardId, int articleUserNumId, int articleNo, String articleTitle,
			String articleContent, int articleComments, Date articleRegDt, Date articleModifyDt, int articleHits,
			int articleLikes) {
		this.articleId = articleId;
		this.boardId = boardId;
		this.articleUserNumId = articleUserNumId;
		this.articleNo = articleNo;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
		this.articleComments = articleComments;
		this.articleRegDt = articleRegDt;
		this.articleModifyDt = articleModifyDt;
		this.articleHits = articleHits;
		this.articleLikes = articleLikes;
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
	public int getArticleNo() {
		return articleNo;
	}
	public void setArticleNo(int articleNo) {
		this.articleNo = articleNo;
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
	public Date getArticleRegDt() {
		return articleRegDt;
	}
	public void setArticleRegDt(Date articleRegDt) {
		this.articleRegDt = articleRegDt;
	}
	public Date getArticleModifyDt() {
		return articleModifyDt;
	}
	public void setArticleModifyDt(Date articleModifyDt) {
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
