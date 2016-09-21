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
	
}
