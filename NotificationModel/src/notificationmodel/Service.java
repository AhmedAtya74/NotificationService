package notificationmodel;


public class Service {

	private Repository repo;
	private NotificationQueu queue;
	private Template temp;
        private dequeueing dequeue;

    public Service() {
     
    }
	
	Service(Repository repo)
	{
		this.repo = repo;
		temp = new Template();
                queue = new NotificationQueu();
                dequeue=new sender();
	}
	
	public Template createTemplate()
	{
		return temp.createTemplate();
	}
	
	public void perpareToSend(int templateId)
	{
		queue.perpareToSend(templateId);
	}
        
        public void send(int notificationid)
	{
		dequeue.send(notificationid);
	}
	
	public boolean createNotificationTemplate(Template temp)
	{
		if (repo.createNotificationTemplate(temp))
			return true;
		else
			return false;
	}
	
	public Template readTemplate(int templateId)
	{
		return repo.readNotificationTemplate(templateId);
	}
	
	public boolean updateTemplate(int templateId)
	{
		if (repo.updateNotificationTemplate(templateId))
			return true;
		else
			return false;
	}
	
	public boolean deleteTemplate(int templateId)
	{
		if (repo.deleteNotificationTemplate(templateId))
			return true;
		else
			return false;
		
	}
}
