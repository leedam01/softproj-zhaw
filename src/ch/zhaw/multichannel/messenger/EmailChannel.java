package ch.zhaw.multichannel.messenger;

import ch.zhaw.multichannel.message.Message;

public class EmailChannel implements Channel {

	private static final EmailChannel instance = new EmailChannel();
	
	private EmailChannel() {}
	
	public static Channel getInstance() {
		return instance;
	}

	@Override
	public void submit(Message msg) {
		System.out.println(msg.getText());
		System.out.println("Sent to: " + msg.getRecipients());
	}

	@Override
	public int getType() {
		return EMAIL;
	}

}
