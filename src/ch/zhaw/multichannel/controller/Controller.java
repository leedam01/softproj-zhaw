package ch.zhaw.multichannel.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import ch.zhaw.multichannel.contact.Address;
import ch.zhaw.multichannel.contact.EmailAddress;
import ch.zhaw.multichannel.contact.MobileAddress;
import ch.zhaw.multichannel.contact.PrintAddress;
import ch.zhaw.multichannel.message.EmailMessage;
import ch.zhaw.multichannel.message.Message;
import ch.zhaw.multichannel.message.MmsMessage;
import ch.zhaw.multichannel.message.PrintMessage;
import ch.zhaw.multichannel.message.SmsMessage;
import ch.zhaw.multichannel.messenger.Channel;
import ch.zhaw.multichannel.messenger.EmailChannel;
import ch.zhaw.multichannel.messenger.Messenger;
import ch.zhaw.multichannel.messenger.MmsChannel;
import ch.zhaw.multichannel.messenger.PrintChannel;
import ch.zhaw.multichannel.messenger.SmsChannel;
import ch.zhaw.multichannel.view.Gui;

public class Controller implements Observer {
	private Gui gui;
	private Object initSource;
	private boolean isSendable;
	private Address empf;
	private MmsMessage mms;
	private SmsMessage sms;
	private EmailMessage email;
	private PrintMessage print;
	private Messenger mess;

	public Controller() {
		gui = new Gui();
		setListener();
		mess = new Messenger();
	}

	public void setListener() {
		gui.setRadioListener(new RadioListener());
		gui.setSendListener(new SendListener());
		gui.setCheckboxListener(new CheckboxListener());
		gui.setAdressbookListener(new AdressbookListener());
		gui.setFileChooserListener(new FileChooserListener());
		gui.setClearListener(new ClearListener());
	}

	public void update(Observable o, Object obj) {
		Message msg = (Message) obj;
		gui.createNotification(msg.getMsgId().toString() + " will be sent at "
				+ msg.getTimeToSend().toString() + " to "
				+ msg.getRecipients().toString());
	}
	
	class ClearListener implements ActionListener{


		public void actionPerformed(ActionEvent arg0) {
			gui.clearAllTextfields();
			
		}
		
	}

	class FileChooserListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			FileNameExtensionFilter filter = null;

			if (source == gui.getB3()) {
				int returnValue = gui.openFileChooser(filter);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					gui.getText5().setText(
							gui.getFc().getSelectedFile().getName());
				}
			}
			if (source == gui.getB4()) {
				filter = new FileNameExtensionFilter("Bilder", "jpg", "png",
						"gif");
				int returnValue = gui.openFileChooser(filter);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					gui.getText6().setText(
							gui.getFc().getSelectedFile().getName());
				}
			}
		}

	}

	class AdressbookListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			Object source = e.getSource();
			if (source == gui.getB1() || source == gui.getB2()) {

				initSource = source;

				ArrayList<String> array = new ArrayList<String>();
				gui.createDialog(array);
			}
			if (source == gui.getB7()) {
				Address addr = new EmailAddress(gui.getText9().getText());
				if (addr.validate()) {
					gui.getLimo().addElement(addr.getAddress());
					gui.getText9().setText("");
				} else {
					gui.createErrorNotification("Email nicht gueltig");
				}
			}
			if (source == gui.getB8()) {
				String contact = gui.getAdressList().getSelectedValue();
				if (initSource == gui.getB1()) {
					contact += ";" + gui.getText1().getText();
					gui.getText1().setText(contact);
				} else {
					contact += ";" + gui.getText2().getText();
					gui.getText2().setText(contact);
				}
				gui.getDialog().dispose();
			}

		}

	}

	class RadioListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			JRadioButton rb = (JRadioButton) e.getSource();
			if (rb.getText() == "Email") {
				gui.setEmailForm();
			} else if (rb.getText() == "SMS") {
				gui.setSmsForm();
			} else if (rb.getText() == "MMS") {
				gui.setMmsForm();
			} else if (rb.getText() == "Printer") {
				gui.setPrinterForm();
			}
		}

	}

	class SendListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			isSendable = true;
			Channel ch = null;
			if (gui.getRadioEmail().isSelected()) {
				email = new EmailMessage();
				String[] strArray = gui.getText1().getText().split(";|;\\s");
				gui.changeBackground(gui.getText1(), new Color(255, 255, 255));
				for (String str : strArray) {
					empf = new EmailAddress(str);
					if (!empf.validate()) {
						isSendable = false;
						gui.changeBackground(gui.getText1(), new Color(255,
								100, 100));
					} else {
						email.addRecipient(empf);
					}
				}

				String[] strArrayCc = gui.getText2().getText().split(";|;\\s");
				gui.changeBackground(gui.getText2(), new Color(255, 255, 255));
				if (!gui.getText2().getText().isEmpty()) {
					for (String str : strArrayCc) {
						empf = new EmailAddress(str);
						if (!empf.validate()) {
							isSendable = false;
							gui.changeBackground(gui.getText2(), new Color(255,
									100, 100));
						} else {
							email.addCcRecipient(empf);
						}
					}
				}

				gui.changeBackground(gui.getText8(), new Color(255, 255, 255));
				if (gui.getCheckRemainder().isSelected()) {
					Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
					if (p.matcher(gui.getText8().getText()).matches()){
						String[] hm = gui.getText8().getText().split(":");
						Calendar c = gui.getDate().getCalendar();
						c.set(Calendar.HOUR, Integer.parseInt(hm[0]));
						c.set(Calendar.MINUTE, Integer.parseInt(hm[1]));
						Date d = c.getTime();
						c.set(Calendar.MINUTE, Integer.parseInt(hm[1]) - 15);
						Date d2 = c.getTime();
						email.setTimeToSend(d);
						email.setTimeToNotify(d2);
					} else{
						gui.changeBackground(gui.getText8(), new Color(255,
								100, 100));
						isSendable = false;
					}
				}
				email.setText(gui.getArea1().getText());
				email.setSubject(gui.getText4().getText());
				email.setAttachement(gui.getFc().getSelectedFile());
				// message.setSbject();
				ch = EmailChannel.getInstance();
			}

			if (gui.getRadioSms().isSelected()) {
				sms = new SmsMessage();

				gui.changeBackground(gui.getText3(), new Color(255, 255, 255));
				String[] strArrayPhone = gui.getText3().getText()
						.split(";|;\\s");
				for (String str : strArrayPhone) {
					empf = new MobileAddress(str);
					if (!empf.validate()) {
						isSendable = false;
						gui.changeBackground(gui.getText3(), new Color(255,
								100, 100));
					} else {
						sms.addRecipient(empf);
					}
				}
				gui.changeBackground(gui.getText8(), new Color(255, 255, 255));
				if (gui.getCheckRemainder().isSelected()) {
					Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
					if (p.matcher(gui.getText8().getText()).matches()){
						String[] hm = gui.getText8().getText().split(":");
						Calendar c = gui.getDate().getCalendar();
						c.set(Calendar.HOUR, Integer.parseInt(hm[0]));
						c.set(Calendar.MINUTE, Integer.parseInt(hm[1]));
						Date d = c.getTime();
						c.set(Calendar.MINUTE, Integer.parseInt(hm[1]) - 15);
						Date d2 = c.getTime();
						email.setTimeToSend(d);
						email.setTimeToNotify(d2);
					} else{
						gui.changeBackground(gui.getText8(), new Color(255,
								100, 100));
						isSendable = false;
					}
				}
				sms.setText(gui.getArea1().getText());
				ch = SmsChannel.getInstance();

			}
			if (gui.getRadioMms().isSelected()) {

				mms = new MmsMessage(gui.getFc().getSelectedFile());
				gui.changeBackground(gui.getText3(), new Color(255, 255, 255));
				String[] strArrayPhone = gui.getText3().getText()
						.split(";|;\\s");
				for (String str : strArrayPhone) {
					empf = new MobileAddress(str);
					if (!empf.validate()) {
						isSendable = false;
						gui.changeBackground(gui.getText3(), new Color(255,
								100, 100));
					} else {
						mms.addRecipient(empf);
					}
				}
				gui.changeBackground(gui.getText8(), new Color(255, 255, 255));
				if (gui.getCheckRemainder().isSelected()) {
					Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
					if (p.matcher(gui.getText8().getText()).matches()){
						String[] hm = gui.getText8().getText().split(":");
						Calendar c = gui.getDate().getCalendar();
						c.set(Calendar.HOUR, Integer.parseInt(hm[0]));
						c.set(Calendar.MINUTE, Integer.parseInt(hm[1]));
						Date d = c.getTime();
						c.set(Calendar.MINUTE, Integer.parseInt(hm[1]) - 15);
						Date d2 = c.getTime();
						email.setTimeToSend(d);
						email.setTimeToNotify(d2);
					} else{
						gui.changeBackground(gui.getText8(), new Color(255,
								100, 100));
						isSendable = false;
					}
				}				
				ch = MmsChannel.getInstance();

			}

			if (gui.getRadioPrinter().isSelected()) {
				print = new PrintMessage();
				print.setText(gui.getArea1().getText());
				PrintAddress printerAddress = new PrintAddress(gui.getText7()
						.getText());
				print.addRecipient(printerAddress);
				ch = PrintChannel.getInstance();
			}
			
			if (!isSendable) {
				gui.createWarning();
			} else {
				gui.removeWarning();
				if (gui.getCheckRemainder().isSelected()) {
					if (gui.getRadioEmail().isSelected()) {
						mess.addMessage(email);
					} else if (gui.getRadioSms().isSelected()) {
						mess.addMessage(sms);
					} else if (gui.getRadioMms().isSelected()) {
						mess.addMessage(mms);
					}
					
					gui.createNotification("Ihre Nachricht wird zur angegebenen Zeit versendet. Sie werden 15 Minuten vorher eine Notifikation erhalten");
					gui.clearAllTextfields();
				} else {
					if (gui.getRadioEmail().isSelected()) {
						ch.submit(email);
					} else if (gui.getRadioSms().isSelected()) {
						ch.submit(sms);
					} else if (gui.getRadioMms().isSelected()) {
						ch.submit(mms);
					} else if (gui.getRadioPrinter().isSelected()) {
						ch.submit(print);
					}
					gui.createNotification("Ihre Nachricht wurde versendet!");
					gui.clearAllTextfields();
				}
			}
		}

	}

	class CheckboxListener implements ItemListener {

		public void itemStateChanged(ItemEvent e) {

			if (e.getStateChange() == ItemEvent.SELECTED) {
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