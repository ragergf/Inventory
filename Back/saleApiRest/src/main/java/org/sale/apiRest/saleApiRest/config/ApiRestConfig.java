package org.sale.apiRest.saleApiRest.config;

import javax.sql.DataSource;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

public class ApiRestConfig {
	@Bean
	@Primary
	@ConfigurationProperties(prefix = "spring.dataSource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
}
