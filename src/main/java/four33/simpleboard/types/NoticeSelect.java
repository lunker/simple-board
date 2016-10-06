package four33.simpleboard.types;

public class NoticeSelect {
	
	private int noticeId;
	private int noticeArticleId;
	// article이 들어가야 하는데 . .  
	private int noticeRange;
	
	public NoticeSelect(){}

	public NoticeSelect(int noticeId, int noticeArticleId, int noticeRange) {
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
