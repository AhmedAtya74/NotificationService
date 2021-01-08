package notificationmodel;

import java.util.Scanner;

enum Language {
	ARABIC, ENGLISH
};

enum Channel {
	EMAIL, SMS
};

public class Template {
	private int templateId;
	private String subject;
	private String content;
	private Language language;
	private Channel channel;

	public Template() {
		templateId = 0;
		subject = null;
		content = null;
	}

	public void setTemplateId() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the Template ID: ");
		this.templateId = sc.nextInt();
	}

	public void setSubject() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the subject of your template: ");
		this.subject = sc.nextLine();
	}

	public void setContent() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the conent of your template with (*) for the placeholders ");
		this.content = sc.nextLine();
	}

	public void setLanguage() {
           System.out.println("What is the language of the template? ENGLISH OR ARABIC");
		Scanner sc = new Scanner(System.in);
		Language choice = Language.valueOf(sc.next().toUpperCase());
		while (!choice.equals(Language.ARABIC) && !choice.equals(Language.ENGLISH)) {
			System.out.println("Please enter ARABIC OR ENGLISH");
			choice = Language.valueOf(sc.next().toUpperCase());
		}
		if (choice.equals(Language.ARABIC)) {
			this.language = Language.ARABIC;
		} else if (choice.equals(Language.ENGLISH)) {
			this.language = Language.ENGLISH;
		} else {
			System.out.println("Available languages are arabic and english");
		}
	}

	public void setChannel() {
		System.out.println("What is the type of the template? SMS OR EMAIL");
		Scanner sc = new Scanner(System.in);
		Channel choice = Channel.valueOf(sc.next().toUpperCase());
		while (!choice.equals(Channel.SMS) && !choice.equals(Channel.EMAIL)) {
			System.out.println("Please enter SMS OR EMAIL");
			choice = Channel.valueOf(sc.next().toUpperCase());
		}
		if (choice.equals(Channel.SMS)) {
			this.channel = Channel.SMS;
		} else if (choice.equals(Channel.EMAIL)) {
			this.channel = Channel.EMAIL;
		} else {
			System.out.println("Available channels are SMS and EMAIL");
		}
	}

	public int getTemplateId() {
		return this.templateId;
	}

	public String getSubject() {
		return this.subject;
	}

	public String getContent() {
		return this.content;
	}

	public Language getLanguage() {
		return this.language;
	}

	public Channel getChannel() {
		return this.channel;
	}

	public Template createTemplate() {
		Template temp = new Template();
		temp.setTemplateId();
		temp.setSubject();
		temp.setContent();
		temp.setLanguage();
		temp.setChannel();
		return temp;
	}
}
