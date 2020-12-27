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

public class SQLRepository implements Repository {

	@Override
	public boolean createNotificationTemplate(Template template) {

		int ID = template.getId();
		String content = template.getContent();
		String subject = template.getSubject();
		Language language = template.getLanguage();
		Channel channel = template.getChannel();

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/notificationservice?" + "autoReconnect=true&useSSL=false", "root",
					"Eagle01113986309");
			stmt = (Statement) conn.createStatement();

			String sql = "INSERT INTO `notificationtemplate`(`notificationTemplateID`, `subject`, `content`, `type`, `language`) VALUES ('"
					+ ID + "'  , '" + subject + "'  , '" + content + "', '" + channel + "','" + language + "' );";
			((java.sql.Statement) stmt).executeUpdate(sql);
			return true;

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return false;
	}

	@Override
	public Template readNotificationTemplate(int templateId) {
		try {

			Class.forName("com.mysql.jdbc.Driver");

		} catch (ClassNotFoundException ex) {
			System.out.println("mysql not found");
		}
		Connection conn = null;
		Template template = null;
		try {
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/notificationservice?" + "autoReconnect=true&useSSL=false", "root",
					"Eagle01113986309");
			String query = "SELECT `notificationTemplateID`, `subject`, `content`, `type`, `language` FROM `notificationtemplate` WHERE notificationTemplateID="
					+ templateId + "; ";
			Statement st = (Statement) conn.createStatement();
			ResultSet rs = ((java.sql.Statement) st).executeQuery(query);

			while (rs.next()) {
				int id = rs.getInt("notificationTemplateID");
				String subject = rs.getString("subject");
				String content = rs.getString("content");
				String channel = rs.getString("type");
				String langauge = rs.getString("language");
				template = new Template(id, subject, content, (Language) Language.valueOf(langauge),
						(Channel) Channel.valueOf(channel));
				return template;
			}

			((Connection) st).close();
		} catch (SQLException ex) {
			System.out.println("not connected");
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return template;
	}

	@Override
	public boolean updateNotificationTemplate(int templateId, Template newTemplate) {

		int ID = templateId;
		String content = newTemplate.getContent();
		String subject = newTemplate.getSubject();
		Language language = newTemplate.getLanguage();
		Channel channel = newTemplate.getChannel();
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/notificationservice?" + "autoReconnect=true&useSSL=false", "root",
					"Eagle01113986309");
			stmt = (Statement) conn.createStatement();

			String sql = "UPDATE `notificationtemplate` SET `notificationTemplateID`='" + ID + "', `subject`='"
					+ subject + "' ,`content`='" + content + "',`type`='" + channel + "' ,`language`='" + language
					+ "' WHERE notificationTemplateID= " + templateId + ";";
			((java.sql.Statement) stmt).executeUpdate(sql);

			System.out.println("notification updated");
			return true;
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean deleteNotificationTemplate(int templateId) {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/notificationservice?" + "autoReconnect=true&useSSL=false", "root",
					"Eagle01113986309");
			stmt = (Statement) conn.createStatement();

			String sql = "DELETE FROM `notificationtemplate` WHERE notificationTemplateID= " + templateId + ";";
			((java.sql.Statement) stmt).executeUpdate(sql);

			System.out.println("notification deleted");
			return true;

		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean sendNotification(Template notification) {

		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/notificationservice?" + "autoReconnect=true&useSSL=false", "root",
					"Eagle01113986309");
			stmt = (Statement) conn.createStatement();
			Channel type = notification.getChannel();
			if (type.equals(Channel.EMAIL)) {
				String sql = "INSERT INTO `sendbyemail`(`NotificationID`, `subject`, `content`, `channel`, `language`) VALUES ('"
						+ notification.getId() + "'  , '" + notification.getSubject() + "'  , '"
						+ notification.getContent() + "', '" + notification.getChannel() + "','"
						+ notification.getLanguage() + "' );";
				((java.sql.Statement) stmt).executeUpdate(sql);

				System.out.println("notification is send by email");
				return true;
			} else if (type.equals(Channel.SMS)) {
				String sql = "INSERT INTO `sendbysms`(`notificationID`, `subject`, `content`, `channel`, `language`) VALUES ('"
						+ notification.getId() + "'  , '" + notification.getSubject() + "'  , '"
						+ notification.getContent() + "', '" + notification.getChannel() + "','"
						+ notification.getLanguage() + "' );";
				((java.sql.Statement) stmt).executeUpdate(sql);
				System.out.println("notification is send by sms");
				return true;
			} else
				System.out.println("notification is not send");
		} catch (SQLException se) {

			se.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		} finally {

			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		return false;

	}
}
