package four33.simpleboard.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import ftt.common.types.config.DataSourceConfig;

@Component
@ConfigurationProperties(locations = "${application.conf.path}/config.yml")
public class AppConfig {
	private Map<String, DataSourceConfig> datasource = new HashMap<String, DataSourceConfig>();

	public Map<String, DataSourceConfig> getDatasource() {
		return datasource;
	}
}
