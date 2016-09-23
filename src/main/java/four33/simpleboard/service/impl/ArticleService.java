package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.Constants;

@Service
public class ArticleService implements IArticleService{

	@Autowired
	private IArticleDao articleDao;
	
	@Override
	public AppResponse writeArticle(WriteArticle writeArticle) {
		AppResponse response = null;
		int result=0;
		
		result = articleDao.insertArticle(writeArticle);
		
		if(result > 0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 쓰기 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 쓰기 실패");
		}
		
		return response;
	}

	@Override
	public AppResponse modifyArticle(WriteArticle writeArticle) {
		// TODO Auto-generated method stub
		AppResponse response = null;
		int result=0;
		return null;
	}

	@Override
	public AppResponse deleteArticle(int articleId) {
		// TODO Auto-generated method stub
		AppResponse response = null;
		int result=0;
		return null;
	}

	@Override
	public AppResponse selectArticle() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse selectArticles(String condition, String order, int printNum, int pageNum) {
		
		AppResponse response = null;

		System.out.println("게시글 리스트 조회 : " );
		System.out.println("condition :"+condition);
		System.out.println("order " + order);
		System.out.println("printnum" + printNum);
		System.out.println("pageNum" + pageNum);
		
		Object data = articleDao.selectArticles(condition, order, printNum, (pageNum-1)*printNum);
		
		if(data == null){
			System.out.println("게시글 리스트 조회 실패");
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "게시글 리스트 조회 실패", null);
		}
		else{
			System.out.println( ((Article[])data).length );
			
			Article[] tmp = (Article[])data;
			
			for(int i=0; i<tmp.length;i++){
				System.out.println("article title : " + tmp[i].getArticleTitle());
			}
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 리스트 조회 성공", (Article[])data);
		}
		
		return response;
	}



}
