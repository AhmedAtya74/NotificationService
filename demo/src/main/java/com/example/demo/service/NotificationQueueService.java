package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.database.Repository;
import com.example.demo.model.Template;
@Service
public class NotificationQueueService {

	private final Repository repository;
	
	@Autowired
	NotificationQueueService(Repository repository) {
		this.repository = repository;
	}

	public boolean send(Template notification) {
		return repository.sendNotification(notification);
	}

}
