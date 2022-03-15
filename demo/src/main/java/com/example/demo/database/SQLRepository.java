package com.example.demo.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.example.demo.model.Template;
import com.example.demo.model.Template.Channel;
import com.example.demo.model.Template.Language;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@org.springframework.stereotype.Repository("SQL")

public class SQLRepository implements Repository, DefaultConnection {

	@Override
	public boolean createNotificationTemplate(Template template) throws SQLException {

		int ID = template.getId();
		String content = template.getContent();
		String subject = template.getSubject();
		Language language = template.getLanguage();
		Channel channel = template.getChannel();

		Connection defaultConnection = connectToDatabase();
		Statement statement = (Statement) defaultConnection.createStatement();

		String insertQuery = "INSERT INTO `notificationtemplate"
				+ "`(`notificationTemplateID`, `subject`, `content`, `type`, `language`)" + " VALUES ('" + ID + "'  , '"
				+ subject + "'  , '" + content + "', '" + channel + "','" + language + "' );";

		statement.executeUpdate(insertQuery);
		statement.close();
		return true;
	}

	@Override
	public Template readNotificationTemplate(int templateId) throws SQLException {

		Connection defaultConnection = connectToDatabase();
		Statement statement = (Statement) defaultConnection.createStatement();

		String selectQuery = "SELECT `notificationTemplateID`, `subject`, `content`, `type`, `language` FROM `notificationtemplate` WHERE notificationTemplateID="
				+ templateId + "; ";

		ResultSet result = statement.executeQuery(selectQuery);
		while (result.next()) {
			int id = result.getInt("notificationTemplateID");
			String subject = result.getString("subject");
			String content = result.getString("content");
			String channel = result.getString("type");
			String langauge = result.getString("language");

			Template template = new Template(id, subject, content, (Language) Language.valueOf(langauge),
					(Channel) Channel.valueOf(channel));

			return template;
		}

	}

	@Override
	public boolean updateNotificationTemplate(int templateId, Template newTemplate) throws SQLException {

		int ID = templateId;
		String content = newTemplate.getContent();
		String subject = newTemplate.getSubject();
		Language language = newTemplate.getLanguage();
		Channel channel = newTemplate.getChannel();

		Connection defaultConnection = connectToDatabase();
		Statement statement = (Statement) defaultConnection.createStatement();

		String updateQuery = "UPDATE `notificationtemplate` SET " + "`notificationTemplateID`='" + ID + "',"
				+ " `subject`='" + subject + "' ," + "`content`='" + content + "',`type`='" + channel + "' ,"
				+ "`language`='" + language + "' WHERE notificationTemplateID= " + templateId + ";";

		statement.executeUpdate(updateQuery);

		// System.out.println("notification updated");
		return true;
	}

	@Override
	public boolean deleteNotificationTemplate(int templateId) throws SQLException {

		Connection defaultConnection = connectToDatabase();
		Statement statement = (Statement) defaultConnection.createStatement();

		String deleteQuery = "DELETE FROM `notificationtemplate` WHERE notificationTemplateID= " + templateId + ";";
		statement.executeUpdate(deleteQuery);

		// System.out.println("Notification Deleted.");
		return true;
	}

	@Override
	public boolean sendNotification(Template notification) throws SQLException {

		Connection defaultConnection = connectToDatabase();
		Statement statement = (Statement) defaultConnection.createStatement();

		Channel type = notification.getChannel();
		if (type.equals(Channel.EMAIL)) {
			String insertQuery = "INSERT INTO `sendbyemail`(`NotificationID`, `subject`, `content`, `channel`, `language`)"
					+ " VALUES ('" + notification.getId() + "'  , '" + notification.getSubject() + "'  , '"
					+ notification.getContent() + "', '" + notification.getChannel() + "','"
					+ notification.getLanguage() + "' );";

			statement.executeUpdate(insertQuery);
			// System.out.println("Notification is sent by email");

			return true;

		} else if (type.equals(Channel.SMS)) {
			String insertQuery = "INSERT INTO `sendbysms`(`notificationID`, `subject`, `content`, `channel`, `language`) "
					+ "VALUES ('" + notification.getId() + "'  , '" + notification.getSubject() + "'  , '"
					+ notification.getContent() + "', '" + notification.getChannel() + "','"
					+ notification.getLanguage() + "' );";

			statement.executeUpdate(insertQuery);
			// System.out.println("Notification is sent by SMS");
			return true;
		} else {
			// System.out.println("Channel is not provided")
			return false;
		}

	}
}
