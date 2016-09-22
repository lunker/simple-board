package four33.simpleboard.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import four33.simpleboard.dao.IMembershipDao;
import four33.simpleboard.service.IArticleService;
import four33.simpleboard.service.IMembershipService;
import four33.simpleboard.types.LoginForm;
import four33.simpleboard.types.AppResponse;
import four33.simpleboard.types.SignupUser;
import four33.simpleboard.types.User;
import four33.simpleboard.types.WriteArticle;
import four33.simpleboard.utils.AES;
import four33.simpleboard.utils.Constants;

@Service
public class ArticleService implements IArticleService{

	@Override
	public AppResponse writeArticle(WriteArticle writeArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse modifyArticle(WriteArticle writeArticle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse deleteArticle(int articleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppResponse selectArticle() {
		// TODO Auto-generated method stub
		return null;
	}

}
