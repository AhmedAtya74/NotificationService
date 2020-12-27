package com.example.demo.database;

import com.example.demo.model.Template;

public interface Repository {

	public boolean createNotificationTemplate(Template template);

	public Template readNotificationTemplate(int templateId);

	public boolean updateNotificationTemplate(int templateId, Template newTemplate);

	public boolean deleteNotificationTemplate(int templateId);

	public boolean sendNotification(Template notification);
}
