package four33.simpleboard.types;

import java.sql.Date;

public class ArticleView {

	private Article article;
	private String boardName;
//	private Comment comment;
//	private 
	
	
	public Article getArticle() {
		return article;
	}
	public ArticleView(Article article, String boardName) {
		super();
		this.article = article;
		this.boardName = boardName;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	
	
	
	
	
	
	
	
	
	
}
