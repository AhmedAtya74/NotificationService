package com.example.demo.api;

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

	@PostMapping
	public boolean send(@RequestBody Template notification) {
		return notificationQueueService.send(notification);
	}
}
