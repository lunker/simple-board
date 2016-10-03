package four33.simpleboard.types;

public class Notice {
	
	private int noticeId;
	private int noticeArticleId;
	private int noticeRange;
	
	public Notice(){}

	public Notice(int noticeId, int noticeArticleId, int noticeRange) {
		this.noticeId = noticeId;
		this.noticeArticleId = noticeArticleId;
		this.noticeRange = noticeRange;
	}

	public int getNoticeId() {
		return noticeId;
	}

	public void setNoticeId(int noticeId) {
		this.noticeId = noticeId;
	}

	public int getNoticeArticleId() {
		return noticeArticleId;
	}

	public void setNoticeArticleId(int noticeArticleId) {
		this.noticeArticleId = noticeArticleId;
	}

	public int getNoticeRange() {
		return noticeRange;
	}

	public void setNoticeRange(int noticeRange) {
		this.noticeRange = noticeRange;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
