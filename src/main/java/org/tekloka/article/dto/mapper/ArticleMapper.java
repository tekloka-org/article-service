package org.tekloka.article.dto.mapper;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import org.tekloka.article.document.Article;
import org.tekloka.article.dto.ArticleDTO;

@Component
public class ArticleMapper {
	
	public Article toArticle(Optional<Article> articleOptional, ArticleDTO aritcleDTO) {
		var article = new Article();
		if(articleOptional.isPresent()) {
			article = articleOptional.get();
		}
		article.setName(aritcleDTO.getName());
		article.setUrlPath(aritcleDTO.getUrlPath());
		article.setAuthorId(aritcleDTO.getAuthorId());
		article.setContent(aritcleDTO.getContent());
		return article;
	}
	
	public ArticleDTO toArticleDTO(Article article) {
		var articleDTO = new ArticleDTO();
		articleDTO.setArticleId(article.getArticleId());
		articleDTO.setName(article.getName());
		articleDTO.setUrlPath(article.getUrlPath());
		articleDTO.setAuthorId(article.getAuthorId());
		articleDTO.setContent(article.getContent());
		return articleDTO;
	}
	
	public Set<ArticleDTO> toArticleDTOSet(Set<Article> articles){
		return articles.stream().map(this::toArticleDTO).collect(Collectors.toSet());
	}

}
