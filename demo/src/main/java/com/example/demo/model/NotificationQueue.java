package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

// responsible for push notification 

public class NotificationQueue {

	private int notificationId;

	NotificationQueue(@JsonProperty("id") int notificationId){
		this.notificationId = notificationId;
	}
	
	public int getNotificationId() {
		return this.notificationId;
	}
}
