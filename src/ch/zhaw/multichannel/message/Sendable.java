package ch.zhaw.multichannel.message;

import java.util.Set;

import ch.zhaw.multichannel.contact.Address;

public interface Sendable <A extends Address> {
	Set<A> getRecipients();
	void addRecipient(A address);
	void removeRecipient(A address);
	String getText();
}
