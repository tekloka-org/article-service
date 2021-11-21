package org.tekloka.article.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tekloka.article.constants.PermissionConstants;
import org.tekloka.article.dto.ArticleDTO;
import org.tekloka.article.security.AccessPermissions;
import org.tekloka.article.service.ArticleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/article")
public class ArticleController {

	private final ArticleService articleService;
	
	public ArticleController(ArticleService articleService) {
		this.articleService = articleService;
	}
	
	@Operation(summary = "Add Article")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Article added successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping(path = "/save", produces = MediaType.APPLICATION_JSON_VALUE)
	//@AccessPermissions(value = { PermissionConstants.SAVE_ARTICLE })
	public ResponseEntity<Object> save(HttpServletRequest request, @RequestBody ArticleDTO articleDTO) {
		return articleService.save(request, articleDTO);
	}
	
	@Operation(summary = "Update Article")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Article updated successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping(path = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
	//@AccessPermissions(value = { PermissionConstants.UPDATE_ARTICLE })
	public ResponseEntity<Object> update(HttpServletRequest request, @RequestBody ArticleDTO articleDTO) {
		return articleService.update(request, articleDTO);
	}
	
	@Operation(summary = "Delete Article")
	@ApiResponses(value = {
	     @ApiResponse(responseCode = "200", description = "Article deleted successfully"),
	     @ApiResponse(responseCode = "500", description = "Internal Server Error")
	})
	@PostMapping(path = "/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(HttpServletRequest request, @RequestBody ArticleDTO articleDTO) {
		return articleService.delete(request, articleDTO);
	}
}
