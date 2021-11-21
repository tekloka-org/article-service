package org.tekloka.article.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.tekloka.article.util.AuditorAwareUtil;

@Configuration
public class AppConfig {

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareUtil();
	}
}