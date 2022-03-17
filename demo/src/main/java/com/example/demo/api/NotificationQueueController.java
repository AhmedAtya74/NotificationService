package com.example.demo.api;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Template;
import com.example.demo.service.NotificationQueueService;

@RequestMapping("api/v1/notificationQueue")
@RestController
public class NotificationQueueController {

	NotificationQueueService notificationQueueService;

	@Autowired
	NotificationQueueController(NotificationQueueService notificationQueueService) {
		this.notificationQueueService = notificationQueueService;
	}

	@PostMapping("/sendBySMS/{id}")
	public boolean sendBySMS(@PathVariable(value = "id") int id, @RequestBody ArrayList<String> placeHolders) {
		return notificationQueueService.sendBySMS(id, placeHolders);
	}

	@PostMapping("/sendByMail/{id}")
	public boolean sendByMail(@PathVariable(value = "id") int id, @RequestBody ArrayList<String> placeHolders) {
		return notificationQueueService.sendByMail(id, placeHolders);
	}

}
