package org.tekloka.article.repository;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import org.tekloka.article.document.Article;

@Repository
public interface ArticleRepository extends MongoRepository<Article, String>{
	
	Set<Article> findByActive(boolean active);

	Optional<Article> findByCreatedByAndUrlPathAndActive(String userId, String articleUrlPath, boolean active);
	
}

	
	