package ch.zhaw.multichannel.message;

import java.io.File;

public class MmsMessage extends Message {

	private File mmsImage;
	
	public MmsMessage() {
		// TODO Auto-generated constructor stub
	}

	public String getImagePath(){
		return mmsImage.getAbsolutePath();
	}

	public int getType() {
		return MMS;
	}
}
