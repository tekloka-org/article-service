package org.tekloka.article.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
	
	private String articleId;
	private String name;
	private String urlPath;
	private String content;
	private String authorId;
	
}
