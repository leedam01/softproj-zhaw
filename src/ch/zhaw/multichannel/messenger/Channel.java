package ch.zhaw.multichannel.messenger;

import ch.zhaw.multichannel.message.Message;
import ch.zhaw.multichannel.message.MessageType;

public interface Channel extends MessageType {
	void submit(Message msg);
	int getType();
}
