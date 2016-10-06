package four33.simpleboard.types;

public class NoticeWrite {

	private int noticeArticleId;
	private int noticeRange;

	public NoticeWrite() {
	}

	public NoticeWrite(int noticeArticleId, int noticeRange) {
		this.noticeArticleId = noticeArticleId;
		this.noticeRange = noticeRange;
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
