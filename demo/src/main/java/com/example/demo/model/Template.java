package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// responsible for generate notification templates 
public class Template {
	public enum Language {
		ARABIC, ENGLISH;
	};

	public enum Channel {
		EMAIL, SMS
	};

	// @NotBlank
	private int templateId;
	// @NotBlank
	private String subject;
	// @NotBlank
	private String content;
	// @NotBlank
	private Language language;	
	// @NotBlank
	private Channel channel;
	// @NotBlank

	public Template(@JsonProperty("id") int templateId, @JsonProperty("subject") String subject,
			@JsonProperty("content") String content, @JsonProperty("language") Language language,
			@JsonProperty("channel") Channel channel) {
		this.templateId = templateId;
		this.subject = subject;
		this.content = content;
		this.language = language;
		this.channel = channel;
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

	public Channel getChannel() {
		return this.channel;
	}
}
