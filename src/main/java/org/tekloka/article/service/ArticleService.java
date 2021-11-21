package org.tekloka.article.service;

import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.tekloka.article.document.Article;
import org.tekloka.article.dto.ArticleDTO;

public interface ArticleService {

	ResponseEntity<Object> save(HttpServletRequest request, ArticleDTO articleDTO);

	ResponseEntity<Object> update(HttpServletRequest request, ArticleDTO articleDTO);

	ResponseEntity<Object> delete(HttpServletRequest request, ArticleDTO articleDTO);

	Set<ArticleDTO> toArticleDTOSet(Set<Article> articles);

	ArticleDTO toArticleDTO(Article article);

	Article toArticle(Optional<Article> articleOptional, ArticleDTO aritcleDTO);

	ResponseEntity<Object> getAllActiveArticles(HttpServletRequest request);

	ResponseEntity<Object> getArticle(HttpServletRequest request, String userId, String articleUrlPath);

}
