package four33.simpleboard.types;

import java.sql.Date;

/**
 * 게시글 작성을 위한 DTO
 * @author dongqlee
 *
 */
public class WriteArticle {
	
	private int boardId;
	private int articleUserNumId;
	
	private String articleTitle;
	private String articleContent;
	
	public WriteArticle(){}
	
	public WriteArticle(int boardId, int articleUserNumId, String articleTitle, String articleContent) {
		this.boardId = boardId;
		this.articleUserNumId = articleUserNumId;
		this.articleTitle = articleTitle;
		this.articleContent = articleContent;
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

	
	
	
}
