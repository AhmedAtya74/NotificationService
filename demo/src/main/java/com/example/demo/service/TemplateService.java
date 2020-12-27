package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demo.database.Repository;
import com.example.demo.model.Template;

@Service
public class TemplateService {
	private final Repository repository;

	@Autowired
	TemplateService(@Qualifier("SQL") Repository repository) {
		this.repository = repository;
	}

	public boolean createTemlate(Template template) {
		return repository.createNotificationTemplate(template);
	}

	public Template readTemplate(int templateId) {
		return repository.readNotificationTemplate(templateId);
	}

	public boolean updateTemplate(int templateId, Template newTemplate) {
		return repository.updateNotificationTemplate(templateId, newTemplate);
	}

	public boolean deleteTemplate(int templateId) {
		return repository.deleteNotificationTemplate(templateId);
	}
}
