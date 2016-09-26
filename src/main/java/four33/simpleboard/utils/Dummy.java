package four33.simpleboard.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.types.WriteArticle;

public class Dummy {
	
	@Autowired
	private IArticleDao articleDao;
	
	public Dummy(){}
	
	
	public void start(){
		
		String title = "test";
		String content = "test";
		
		WriteArticle article = null;
		
		for(int i=0;i<10000;i++){
			article = new WriteArticle(1,12, title+i, content+i);
			articleDao.insertArticle(article);
		}
		System.out.println("더미 데이터 생성완료");
	}

}
