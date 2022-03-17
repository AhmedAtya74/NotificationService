package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Template {
	
	public enum Language {
		ARABIC, ENGLISH;
	};

	// @NotBlank
	private int templateId;
	// @NotBlank
	private String subject;
	// @NotBlank
	private String content;
	// @NotBlank
	private Language language;	

	public Template(@JsonProperty("id") int templateId, @JsonProperty("subject") String subject,
			@JsonProperty("content") String content, @JsonProperty("language") Language language) {
		this.templateId = templateId;
		this.subject = subject;
		this.content = content;
		this.language = language;
	}

	public int getId() {
		return this.templateId;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getContent() {
		return this.content;
	}

	public Language getLanguage() {
		return this.language;
	}
}
