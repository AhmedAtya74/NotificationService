package com.example.demo.database;

import java.sql.SQLException;

import com.example.demo.model.Template;

public interface Repository {

	public boolean createNotificationTemplate(Template template) throws SQLException;

	public Template readNotificationTemplate(int templateId) throws SQLException;

	public boolean updateNotificationTemplate(int templateId, Template newTemplate) throws SQLException;

	public boolean deleteNotificationTemplate(int templateId) throws SQLException;

}
