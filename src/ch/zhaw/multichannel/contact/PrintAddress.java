package ch.zhaw.multichannel.contact;

public class PrintAddress implements Address {
	
	private String printAddress;

	public PrintAddress(String str) {
		this.printAddress = str;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getAddress(){
		return printAddress;
	}

}
