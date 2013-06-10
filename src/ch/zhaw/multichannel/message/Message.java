package ch.zhaw.multichannel.message;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import ch.zhaw.multichannel.contact.Address;

public abstract class Message implements Sendable<Address>, MessageType {

	private Set<Address> recipients;
	private String message;
	private UUID msgId;

	private Date timeToSend;
	private Date timeSent;
	private Date timeToNotify;
	private Date timeNotified;
	
	public abstract int getType();
	
	public Message() {
		this.msgId = UUID.randomUUID();
		this.recipients = new HashSet<Address>();
	}
	
	public Set<Address> getRecipients() {
		return recipients;
	}

	public void addRecipient(Address address) {
		this.recipients.add(address);
	}
	

	public void removeRecipient(Address address) {
		this.recipients.remove(address);
	}
	
	public String getText() {
		return message;
	}
	
	public void setText(String message) {
		this.message = message;
	}

	public Date getTimeToSend() {
		return timeToSend;
	}

	public void setTimeToSend(Date timeToSend) {
		this.timeToSend = timeToSend;
	}

	public Date getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(Date timeSent) {
		this.timeSent = timeSent;
	}

	public Date getTimeToNotify() {
		return timeToNotify;
	}

	public void setTimeToNotify(Date timeToNotify) {
		this.timeToNotify = timeToNotify;
	}

	public Date getTimeNotified() {
		return timeNotified;
	}

	public void setTimeNotified(Date timeNotified) {
		this.timeNotified = timeNotified;
	}

	public UUID getMsgId() {
		return this.msgId;
	}
}
