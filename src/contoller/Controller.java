package contoller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JRadioButton;

import sender.Message;
import view.GUI;

public class Controller {
	private Message message;
	private GUI gui;
	private Object initSource;
	

	public Controller(){
		message = new Message();
		gui = new GUI(message);
		setListener();
	}
	
	public void setListener(){
		gui.setRadioListener(new RadioListener());
		gui.setSendListener(new SendListener());
		gui.setCheckboxListener(new CheckboxListener());
		gui.setAdressbookListener(new AdressbookListener());
	}
	
	class AdressbookListener implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			if (source == gui.getB1() || source == gui.getB2()) {
				
				initSource = source;
				
				ArrayList<String> array = new ArrayList<String>();
				array.add("test");
				array.add("test2");
				gui.createDialog(array);
			}
			if (source == gui.getB7()){
				//Contact c = new Contact();
				gui.getLimo().addElement(gui.getText9().getText());
				gui.getText9().setText("");
			}
			if (source == gui.getB8()){
				String contact = gui.getAdressList().getSelectedValue();
				if (initSource == gui.getB1()){
					gui.getText1().setText(contact);
				} else {
					gui.getText2().setText(contact);
				}
				gui.getDialog().dispose();
			}
			
		}
		
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
