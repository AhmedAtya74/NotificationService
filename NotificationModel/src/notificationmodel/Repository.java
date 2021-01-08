package notificationmodel;


public interface Repository 
{
	public boolean createNotificationTemplate(Template template);
	
	public Template readNotificationTemplate(int templateId);
	
	public boolean updateNotificationTemplate(int templateId);
	
	public boolean deleteNotificationTemplate(int templateId);
	
	public int getNotificationTemplateId(Template template);
	
}
