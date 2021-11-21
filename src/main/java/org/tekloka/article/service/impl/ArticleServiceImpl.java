package org.tekloka.article.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.tekloka.article.constants.AppConstants;
import org.tekloka.article.constants.DataConstants;
import org.tekloka.article.constants.ResponseConstants;
import org.tekloka.article.document.Article;
import org.tekloka.article.dto.ArticleDTO;
import org.tekloka.article.dto.mapper.ArticleMapper;
import org.tekloka.article.repository.ArticleRepository;
import org.tekloka.article.service.ArticleService;
import org.tekloka.article.util.ResponseUtil;

@Service
public class ArticleServiceImpl implements ArticleService {

	private final Logger logger = LoggerFactory.getLogger(ArticleServiceImpl.class);
	private final ArticleRepository articleRepository;
	private final ArticleMapper articleMapper;
	private final ResponseUtil responseUtil;
	
	public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper,
			ResponseUtil responseUtil) {
		this.articleRepository = articleRepository;
		this.articleMapper = articleMapper;
		this.responseUtil = responseUtil;
	}
	
	@Override
	public Article toArticle(Optional<Article> articleOptional, ArticleDTO aritcleDTO) {
		return articleMapper.toArticle(articleOptional, aritcleDTO);
	}
	
	@Override
	public ArticleDTO toArticleDTO(Article article) {
		return articleMapper.toArticleDTO(article);
	}
	
	@Override
	public Set<ArticleDTO> toArticleDTOSet(Set<Article> articles){
		return articleMapper.toArticleDTOSet(articles);
	}
	
	public Article save(Article article) {
		return articleRepository.save(article);
	}
	
	private Optional<Article> findByArticleId(String articleId) {
		return articleRepository.findById(articleId);
	}
	
	private Set<Article> findByActive(boolean active) {
		return articleRepository.findByActive(active);
	}
	
	public Optional<Article> findByCreatedByAndUrlPathAndActive(String userId, String articleUrlPath, boolean active){
		return articleRepository.findByCreatedByAndUrlPathAndActive(userId, articleUrlPath, active);
	}

	@Override
	public ResponseEntity<Object> save(HttpServletRequest request, ArticleDTO articleDTO) {
		Map<String, Object> dataMap = new HashMap<>();
		try {
			Optional<Article> articleOptional = Optional.empty();
			var article = toArticle(articleOptional, articleDTO);
			article.setActive(true);
			article = save(article);
			dataMap.put(DataConstants.ARTICLE, toArticleDTO(article));
			return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_SAVED);
		}catch (Exception e) {
			logger.error(AppConstants.LOG_FORMAT, ResponseConstants.ARTICLE_NOT_SAVED, e);
			return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_NOT_SAVED);
		}
	}

	@Override
	public ResponseEntity<Object> update(HttpServletRequest request, ArticleDTO articleDTO) {
		Map<String, Object> dataMap = new HashMap<>();
		Optional<Article> artcileOptional = findByArticleId(articleDTO.getArticleId());		
		if(artcileOptional.isPresent()) {
			try {
				var article = toArticle(artcileOptional, articleDTO);
				article = save(article);
				dataMap.put(DataConstants.ARTICLE, toArticleDTO(article));
				return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_UPDATED);
			}catch (Exception e) {
				logger.error(AppConstants.LOG_FORMAT, ResponseConstants.ARTICLE_NOT_UPDATED, e);
				return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_NOT_UPDATED);
			}
		}else {
			return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_NOT_FOUND);
		}
	}

	@Override
	public ResponseEntity<Object> delete(HttpServletRequest request, ArticleDTO articleDTO) {
		Map<String, Object> dataMap = new HashMap<>();
		Optional<Article> artcileOptional = findByArticleId(articleDTO.getArticleId());		
		if(artcileOptional.isPresent()) {
			try {
				var article = toArticle(artcileOptional, articleDTO);
				article.setActive(false);
				save(article);
				return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_DELETED);
			}catch (Exception e) {
				logger.error(AppConstants.LOG_FORMAT, ResponseConstants.ARTICLE_NOT_DELETED, e);
				return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_NOT_DELETED);
			}
		}else {
			return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_NOT_FOUND);
		}
	}
	
	@Override
	public ResponseEntity<Object> getAllActiveArticles(HttpServletRequest request) {
		Map<String, Object> dataMap = new HashMap<>();
		Set<Article> articles = findByActive(true);
		dataMap.put(DataConstants.ARTICLE_LIST, toArticleDTOSet(articles));
		return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_LIST_FOUND);
	}

	@Override
	public ResponseEntity<Object> getArticle(HttpServletRequest request, String userId, String articleUrlPath) {
		Map<String, Object> dataMap = new HashMap<>();
		Optional<Article> articleOptional = findByCreatedByAndUrlPathAndActive(userId, articleUrlPath, true);
		if(articleOptional.isPresent()) {
			dataMap.put(DataConstants.ARTICLE, toArticleDTO(articleOptional.get()));
			return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_FOUND);
		}else {
			return responseUtil.generateResponse(dataMap, ResponseConstants.ARTICLE_NOT_FOUND);
		}
	}

}
