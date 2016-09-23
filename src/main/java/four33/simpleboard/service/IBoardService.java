package four33.simpleboard.service;

import four33.simpleboard.types.Board;

public interface IBoardService {
	
	/**
	 * 게시판 종류 조회 
	 * @return
	 */
	public Board[] selectBoard();
}
