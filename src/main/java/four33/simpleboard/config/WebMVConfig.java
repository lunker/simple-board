package four33.simpleboard.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import ftt.common.types.config.DataSourceConfig;



@Configuration
public class WebMVConfig extends WebMvcConfigurerAdapter{
	/*
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
		
	    registry.addInterceptor(httpRequestInterceptor).addPathPatterns("");
	    
	  }
	  */
	
	
}
