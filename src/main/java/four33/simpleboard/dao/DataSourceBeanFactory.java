package four33.simpleboard.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import four33.simpleboard.config.AppConfig;
import ftt.common.types.config.DataSourceConfig;

@Configuration
public class DataSourceBeanFactory {
	
	@Autowired
	private AppConfig appConfig;

	@Bean
	public DataSource localDataSource() {
		BasicDataSource ds = new BasicDataSource();

		DataSourceConfig dsConfig = appConfig.getDatasource().get("localdb");

		String driver = dsConfig.getDriverClassName();
		String url = dsConfig.getUrl();
		String username = dsConfig.getUsername();
		String password = dsConfig.getPassword();

		ds.setDriverClassName(driver);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		ds.setTestOnBorrow(true);
		ds.setTestWhileIdle(true);
		ds.setValidationQuery("SELECT 1");

		return ds;
	}
}
