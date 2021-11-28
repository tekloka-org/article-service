package org.tekloka.article.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tekloka.article.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/public")
public class PublicController {

	private final ArticleService artilceService;
	
	public PublicController(ArticleService artilceService) {
		this.artilceService = artilceService;
	}
	
	@GetMapping(path = "/check-service-status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> checkServiceStatus() {
		return artilceService.checkServiceStatus();
	}
	
	@Operation(summary = "Get all articles")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Articles found"),
	     @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping(path = "/get-all-articles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAllActiveCategories(HttpServletRequest request) {
		return artilceService.getAllActiveArticles(request);
	}
	
	@Operation(summary = "Get Article")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Article found"),
	     @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@GetMapping(path = "{userId}/{articleUrlPath}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getArticle(HttpServletRequest request, @PathVariable String userId, @PathVariable String articleUrlPath) {
		 return artilceService.getArticle(request, userId, articleUrlPath);
	}

	
}
