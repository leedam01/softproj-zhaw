package ch.zhaw.multichannel.contact;

public class EmailAddress implements Address {

	private String emailAddress;
	
	public EmailAddress() {}
	public EmailAddress(String addr) {
		this.emailAddress = addr;
	}

	public void validate() {
		return;
	}
}
