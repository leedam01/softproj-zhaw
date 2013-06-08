package contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JRadioButton;

import sender.Message;
import view.GUI;

public class Controller {
	private Message message;
	private GUI gui;
	

	public Controller(){
		message = new Message();
		gui = new GUI(message);
		setListener();
	}
	
	public void setListener(){
		gui.setRadioListener(new RadioListener());
		gui.setSendListener(new SendListener());
	}
	
	class RadioListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			JRadioButton rb = (JRadioButton) e.getSource();			
			if (rb.getText() == "Email"){
				gui.setEmailForm();
			} else if (rb.getText() == "SMS"){
				gui.setSmsForm();
			} else if (rb.getText() == "MMS"){
				gui.setMmsForm();
			} else if (rb.getText() == "Printer"){
				gui.setPrinterForm();
			}
		}
		
	}
	class SendListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
		}
		
	}
	
	
    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
