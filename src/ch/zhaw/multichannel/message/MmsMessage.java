package ch.zhaw.multichannel.message;

import java.io.File;

public class MmsMessage extends Message {

	private File mmsImage;
	
	public MmsMessage(File f) {
		// TODO Auto-generated constructor stub
		this.mmsImage = f;
	}

	public String getImagePath(){
		return mmsImage.getAbsolutePath();
	}

	public int getType() {
		return MMS;
	}
}
