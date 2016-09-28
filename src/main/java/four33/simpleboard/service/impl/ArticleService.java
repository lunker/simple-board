package four33.simpleboard.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.databind.util.JSONWrappedObject;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.Article;
import four33.simpleboard.types.ArticleView;
import four33.simpleboard.types.ModifyArticle;
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
	public AppResponse modifyArticle(ModifyArticle modifyArticle) {
		AppResponse response = null;
		int result=0;

		result = articleDao.updateArticle(modifyArticle);

		if(result > 0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 수정 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 수정 실패");
		}
		return response;
	}

	@Override
	public AppResponse deleteArticle(int articleId) {
		AppResponse response = null;
		int result=0;

		result = articleDao.deleteArticle(articleId);

		if(result > 0){
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 삭제 성공");
		}
		else{
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 삭제 실패");
		}
		return response;
	}

	@Override
	public AppResponse selectArticle(int articleId, int articleUserNumId) {

		AppResponse response = null;
		
		/*
		Article article = articleDao.selectArticle(articleId);
		
		if(article == null){
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "게시글 조회 실패");
		}
		else{
			System.out.println(article.toString());
			int result = 0;
			result = articleDao.updateArticleHits(articleId, articleUserNumId);
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 조회 성공", article);	
		}
		*/
		int result = 0;
		result = articleDao.updateArticleHits(articleId, articleUserNumId);
		
		Article article = articleDao.selectArticle(articleId);
		response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 조회 성공", article);
		
		return response;
	}

	@Override
	public AppResponse selectArticles(String condition, String order, int printNum, int pageNum) {
		
		/*
		 * {
		 * 	response:
		 * 		data : {
		 * 		
		 * 			articles: {},
		 * 			count : ,
		 * 	}
		 * }
		 */
		AppResponse response = null;

		System.out.println("게시글 리스트 조회 : " );
		System.out.println("condition:"+condition);
		System.out.println("order: " + order);
		System.out.println("printnum: " + printNum);
		System.out.println("pageNum: " + pageNum);
		
		Object articles = articleDao.selectArticles(condition, order, printNum, (pageNum-1)*printNum);
		int count = articleDao.selectArticleCount();
		
		Map<String, Object> data = new HashMap<String, Object>();
		
		data.put("articles",articles);
		data.put("count", count);
		
		if(articles==null){
			System.out.println("게시글 리스트 조회 실패");
			response = new AppResponse(Constants.STR_STATUS_CODE_FAIL, "게시글 리스트 조회 실패", null);
		}
		else{
			System.out.println("게시글 리스트 조회 성공");
			//System.out.println( ((Article[])articles).length );
			
			Article[] tmp = (Article[])articles;
			
			for(int i=0; i<tmp.length;i++){
				System.out.println("article title : " + tmp[i].getArticleTitle());
			}
			
			response = new AppResponse(Constants.STR_STATUS_CODE_SUCCESS, "게시글 리스트 조회 성공", data);
		}
		
		return response;
	}

	@Override
	public Object selectRawArticle(int articleId) {
		Object result = null;
		
		result = articleDao.selectArticle(articleId);
		
		return result;
	}
}
