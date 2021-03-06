package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.dao.IBoardDao;
import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IBoardService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Board;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.AES;
import four33.simpleboard.utils.Constants;

@Service
public class BoardService implements IBoardService{

	
	@Autowired
	private IBoardDao boardDao;

	@Override
	public AppResponse selectBoards() {
		
		AppResponse response = null;
		
		Board[] data = boardDao.selectBoards();
		
		if(data!=null){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "메뉴 조회 성공", data);
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "메뉴 조회 실패", data);
		}
		
		return response;
	}

	@Override
	public AppResponse selectBoard(int boardId) {
		
		AppResponse response = null;
		
		Board data = boardDao.selectBoard(boardId);
		
		if(data!=null){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "메뉴 조회 성공", data);
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "메뉴 조회 실패", data);
		}
		
		return response;
	}
}
