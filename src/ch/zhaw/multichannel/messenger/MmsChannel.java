package ch.zhaw.multichannel.messenger;

import ch.zhaw.multichannel.message.Message;

public class MmsChannel implements Channel {

	private static final MmsChannel instance = new MmsChannel();
	
	private MmsChannel() {}
	
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
		return MMS;
	}

}
