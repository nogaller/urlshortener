package urlshortener.model;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class DataSourceBean {

	@Bean
	@Primary
	DataSourceProperties getDatasourceProperties() {
		return new DataSourceProperties();
	}

	@Bean
	DataSource dataSource() {
		return getDatasourceProperties()//
				.initializeDataSourceBuilder()//
				.build();
	}
}