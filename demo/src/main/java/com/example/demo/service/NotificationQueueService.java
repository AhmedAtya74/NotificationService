package com.example.demo.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.database.Repository;
import com.example.demo.model.Template;

import NotificationQueueApplication.Mail;
import NotificationQueueApplication.SMS;
@Service
public class NotificationQueueService{

	private final Mail mail;
	private final SMS sms;
	@Autowired
	NotificationQueueService(Mail mail, SMS sms) {
		this.mail = mail;
		this.sms = sms;
	}
	
	public boolean sendByMail(int id, ArrayList<String> placeHolders) {
		return mail.Send(id, placeHolders);
	}
	
	public boolean sendBySMS(int id, ArrayList<String> placeHolders) {
		return sms.Send(id, placeHolders);
	}

}
