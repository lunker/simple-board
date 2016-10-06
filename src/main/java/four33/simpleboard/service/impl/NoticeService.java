package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.dao.IBoardDao;
import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.dao.INoticeDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.service.INoticeService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.NoticeWrite;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.AES;
import four33.simpleboard.utils.Constants;

@Service
public class NoticeService implements INoticeService{

	@Autowired
	private INoticeDao noticeDao;

	@Override
	public AppResponse selectPublicNotices() {
		
		AppResponse response = null;
		
		Object result = noticeDao.selectNotices();
		
		if(result!=null){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "전체 공지글 조회 성공", result);
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "전체 공지글 조회 실패", result);
		}
		
		return response;
	}

	@Override
	public AppResponse writeNotice(NoticeWrite noticeWrite) {
		AppResponse response = null;
		
		int result = 0;
		
		result = noticeDao.insertNotice(noticeWrite);
		
		if(result >0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "공지사항 작성 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "공지사항 작성 실패");
		}
		
		return response;
	}

	@Override
	public AppResponse deleteNotice(int noticeId) {
		// TODO Auto-generated method stub
		return null;
	}
}
