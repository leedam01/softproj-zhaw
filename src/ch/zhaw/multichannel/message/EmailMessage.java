package ch.zhaw.multichannel.message;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ch.zhaw.multichannel.contact.Address;

public class EmailMessage extends Message {

	private String subject;
	private File attachement;
	private List<Address> ccRecipients;
	
	public EmailMessage() {
		super();
		ccRecipients = new ArrayList<Address>();
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
	
	public void addCcRecipient(Address a){
		this.ccRecipients.add(a);
	}

	public void setAttachement(File attachement) {
		this.attachement = attachement;
	}
	
}
