package ch.zhaw.multichannel.message;

import java.io.File;

public class EmailMessage extends Message {

	private String subject;
	private File attachement;
	
	public EmailMessage() {
		// TODO Auto-generated constructor stub
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getAttachementPath(){
		return attachement.getAbsolutePath();
	}

	public int getType() {
		return EMAIL;
	}
}
