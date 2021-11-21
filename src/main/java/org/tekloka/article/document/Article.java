package org.tekloka.article.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "articles")
public class Article extends AuditMetadata {

	@Id
	private String articleId;
	
	private String name;
	private String urlPath;
	private String content;
	private String authorId;
	private boolean active;
	private boolean approved;
	
}
