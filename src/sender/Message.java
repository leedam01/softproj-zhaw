package sender;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import addresses.Contact;

public class Message {

	private String text;
	private String subject;
	private List<Contact> recipient;
	private File mmsImage;
	private File attachement;
	
	public Message() {
		// TODO Auto-generated constructor stub
	}

	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getImagePath(){
		return mmsImage.getAbsolutePath();
	}
	
	public String getAttachementPath(){
		return attachement.getAbsolutePath();
	}
}
