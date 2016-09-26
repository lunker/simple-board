package four33.simpleboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import four33.simpleboard.utils.Dummy;


@Configuration
@ComponentScan(basePackages = {"four33.simpleboard"})
@EnableAutoConfiguration // (exclude={DataSourceAutoConfiguration.class})
public class SimpleBoardApplication extends SpringBootServletInitializer{
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SimpleBoardApplication.class);
	}

	public void run(String[] args) throws Exception {
		SpringApplication.run(SimpleBoardApplication.class, args);
	}

	public static void main(String[] args) throws Exception {
		

		
		new SimpleBoardApplication().run(args);
	}
}
