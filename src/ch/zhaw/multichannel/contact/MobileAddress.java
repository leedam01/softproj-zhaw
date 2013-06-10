package ch.zhaw.multichannel.contact;

import java.util.regex.Pattern;

public class MobileAddress implements Address {
	
	private String mobileAddress;
	private Pattern p;

	public MobileAddress(String mobileAdress) {
		// TODO Auto-generated constructor stub
		this.mobileAddress = mobileAdress;
	}

	@Override
	public boolean validate() {
		// regex for only swiss phone numbers
		p = Pattern.compile("((\\+[0-9]{2})|0)[0-9]{2}\\s?[0-9]{3}\\s?[0-9]{2}\\s?[0-9]{2}");
		
		return p.matcher(mobileAddress).matches();
	}

}
