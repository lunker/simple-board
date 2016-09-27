package four33.simpleboard.dao.impl;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import four33.simpleboard.dao.IArticleDao;
import four33.simpleboard.dao.IBoardDao;
import four33.simpleboard.dao.ICommentDao;
import four33.simpleboard.dao.IMembershipDao;

@Configuration
public class DaoBeanFactory {

	@Autowired
	@Qualifier("localDataSource")
	private DataSource ds;

	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate membershipSessionTemplate() {
		try {
			Resource[] resources = new Resource[] { new ClassPathResource("sql/membership.xml"), };

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
			sqlSessionFactory.setDataSource(ds);
			sqlSessionFactory.setMapperLocations(resources);

			return new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {
			System.out.println("error in memberhship session template");
			System.out.println(e.getMessage());
		}

		return null;
	}
	

	/**
	 * Get dao implementation object from mybatis mapping
	 * @return
	 */
	@Bean
	public IMembershipDao membershipDao() {
		SqlSessionTemplate sessionTemplate = membershipSessionTemplate();
		return sessionTemplate.getMapper(IMembershipDao.class);
	}
	
	
	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate articleSessionTemplate() {
		try {
			Resource[] resources = new Resource[] { new ClassPathResource("sql/article.xml"), };

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
			sqlSessionFactory.setDataSource(ds);
			sqlSessionFactory.setMapperLocations(resources);

			return new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {
			System.out.println("error in article session template");
			System.out.println(e.getMessage());
		}

		return null;
	}

	/**
	 * Get dao implementation object from mybatis mapping
	 * @return
	 */
	@Bean
	public IArticleDao articleDao() {
		SqlSessionTemplate sessionTemplate = articleSessionTemplate();
		return sessionTemplate.getMapper(IArticleDao.class);
	}
	
	
	// ======== board dao 
	@Bean(destroyMethod = "clearCache")
	public SqlSessionTemplate boardSessionTemplate() {
		try {
			Resource[] resources = new Resource[] { new ClassPathResource("sql/board.xml"), };

			SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
			sqlSessionFactory.setDataSource(ds);
			sqlSessionFactory.setMapperLocations(resources);

			return new SqlSessionTemplate(sqlSessionFactory.getObject());
		} catch (Exception e) {
			System.out.println("error in board session template");
			System.out.println(e.getMessage());
		}

		return null;
	}
	
	@Bean
	public IBoardDao boardDao() {
		SqlSessionTemplate sessionTemplate = boardSessionTemplate();
		return sessionTemplate.getMapper(IBoardDao.class);
	}
	
	// ======== comment dao 
		@Bean(destroyMethod = "clearCache")
		public SqlSessionTemplate commmentSessionTemplate() {
			try {
				Resource[] resources = new Resource[] { new ClassPathResource("sql/comment.xml"), };

				SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
				sqlSessionFactory.setDataSource(ds);
				sqlSessionFactory.setMapperLocations(resources);

				return new SqlSessionTemplate(sqlSessionFactory.getObject());
			} catch (Exception e) {
				System.out.println("error in board session template");
				System.out.println(e.getMessage());
			}

			return null;
		}
		
		@Bean
		public ICommentDao commmentDao() {
			SqlSessionTemplate sessionTemplate = commmentSessionTemplate();
			return sessionTemplate.getMapper(ICommentDao.class);
		}
	
}
