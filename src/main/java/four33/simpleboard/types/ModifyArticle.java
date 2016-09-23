package four33.simpleboard.types;

import java.sql.Date;

/**
 * 수정해야함.
 * 
 * @author dongqlee
 *
 */
public class ModifyArticle {

	private int articleId;
	// private int boardId;
	private int articleUserNumId;

	private String articleTitle;
	private String articleContent;

	public ModifyArticle() {
	}

	public ModifyArticle(int articleId, int articleUserNumId, String articleTitle, String articleContent) {
		this.articleId = articleId;
		this.articleUserNumId = articleUserNumId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
	}

	public int getArticleId() {
		return articleId;
	}

	public void setArticleId(int articleId) {
		this.articleId = articleId;
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

}
