package contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

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
		gui.setCheckboxListener(new CheckboxListener());
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
	
	class CheckboxListener implements ItemListener{

		public void itemStateChanged(ItemEvent e) {
			
			if (e.getStateChange() == ItemEvent.SELECTED){
				gui.setRemainderFields(true);
			} else {
				gui.setRemainderFields(false);
			}
			
		}
		
	}
	
    public static void main(String[] args) {
        Controller c = new Controller();
    }
}
