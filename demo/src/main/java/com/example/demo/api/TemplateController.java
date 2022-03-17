package com.example.demo.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Template;
import com.example.demo.service.TemplateService;

@RequestMapping("api/v1/template")
@RestController
public class TemplateController {

	TemplateService templateService;

	@Autowired
	TemplateController(TemplateService templateService) {
		this.templateService = templateService;
	}

	@PostMapping
	public boolean create(@NonNull @RequestBody Template template) {
		return templateService.createTemlate(template);
	}

	@GetMapping(path = "{id}")
	public Template readById(@PathVariable("id") int templateId) {
		return templateService.readTemplate(templateId);
	}

	@PutMapping(path = "{id}")
	public boolean update(@PathVariable("id") int templateId, @NonNull @RequestBody Template newTemplate) {
		return templateService.updateTemplate(templateId, newTemplate);
	}

	@DeleteMapping(path = "{id}")
	public boolean deleteById(@PathVariable("id") int templateId) {
		return templateService.deleteTemplate(templateId);
	}
}
