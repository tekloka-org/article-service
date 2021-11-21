package org.tekloka.article;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableDiscoveryClient
@EnableMongoAuditing
public class ArticleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ArticleServiceApplication.class, args);
	}

}
