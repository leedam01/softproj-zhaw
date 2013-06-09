package ch.zhaw.multichannel.messenger;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import ch.zhaw.multichannel.message.Message;
import ch.zhaw.multichannel.message.MessageType;

public class Messenger implements MessageType, Runnable {

	private List<Message> messages;
	private List<Message> messageArchive;
	
	public Messenger() {
		this.messages = new ArrayList<Message>();
		this.messageArchive = new ArrayList<Message>();
	}
	
	public List<Message> getMessages() {
		return messages;
	}
	
	public Message getMessageById(UUID msgId) {
		Message msg;
		Iterator <Message> msgIter = messages.iterator();
		while (msgIter.hasNext()) {
			msg = msgIter.next();
			if (msgId.equals(msg.getMsgId())) {
				return msg;
			}
		}
		return null;
	}
	
	public void addMessage(Message msg) {
		synchronized (messages) {
			this.messages.add(msg);
			messages.notify();
		}
	}
	
	public void removeMessage(Message msg) {
		synchronized (messages) {
			this.messages.remove(msg);
		}
	}
	
	public void sendMessage(Message msg) {
		switch (msg.getType()) {
		case EMAIL:
			EmailChannel.getInstance().submit(msg);
			break;
		case SMS:
			SmsChannel.getInstance().submit(msg);
			break;
		case MMS: 
			MmsChannel.getInstance().submit(msg);
			break;
		case PRINTER: 
			PrintChannel.getInstance().submit(msg);
			break;
		}
		synchronized (messages) {
			messages.remove(msg);
		}
		messageArchive.add(msg);
	}
	
	public void sendNotification(Message msg) {
		System.out.println(msg.getMsgId().toString() + " will be sent at " + msg.getTimeToSend().toString() + " to " + msg.getRecipients().toString());
		msg.setTimeNotified(Calendar.getInstance().getTime());
	}

	@Override
	public void run() {

		Message msg;
		Date currentTime = new Date();
		Iterator <Message> msgIter;
		
		while (true) {
			synchronized (messages) {
				msgIter = messages.iterator();
				while (msgIter.hasNext()) {
					msg = messages.listIterator().next();
					currentTime.setTime(System.currentTimeMillis());
					if (msg.getTimeToNotify() != null && msg.getTimeToNotify().compareTo(currentTime) >= 0) {
						this.sendNotification(msg);
					}
					if (msg.getTimeToSend() != null && msg.getTimeToSend().compareTo(currentTime) >= 0) {
						this.sendMessage(msg);
					}
				}
				try {
					wait(60000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
