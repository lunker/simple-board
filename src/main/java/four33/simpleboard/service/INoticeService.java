package four33.simpleboard.service;

import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.LikeWrite;
import four33.simpleboard.types.NoticeWrite;

public interface INoticeService {
	
	public AppResponse selectPublicNotices();
	
	public AppResponse writeNotice(NoticeWrite noticeWrite);
	
	public AppResponse deleteNotice(int noticeId);
	
}
