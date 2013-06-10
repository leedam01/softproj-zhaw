package ch.zhaw.multichannel.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFileChooser;
import javax.swing.JRadioButton;
import javax.swing.filechooser.FileNameExtensionFilter;

import ch.zhaw.multichannel.contact.Address;
import ch.zhaw.multichannel.contact.EmailAddress;
import ch.zhaw.multichannel.contact.MobileAddress;
import ch.zhaw.multichannel.message.EmailMessage;
import ch.zhaw.multichannel.message.Message;
import ch.zhaw.multichannel.message.MmsMessage;
import ch.zhaw.multichannel.message.SmsMessage;
import ch.zhaw.multichannel.messenger.Channel;
import ch.zhaw.multichannel.messenger.EmailChannel;
import ch.zhaw.multichannel.messenger.MmsChannel;
import ch.zhaw.multichannel.messenger.PrintChannel;
import ch.zhaw.multichannel.messenger.SmsChannel;
import ch.zhaw.multichannel.view.Gui;

public class Controller implements Observer {
	private Message message;
	private Gui gui;
	private Object initSource;
	private boolean isSendable;
	private Address empf;

	public Controller() {
		gui = new Gui();
		setListener();
	}

	public void setListener() {
		gui.setRadioListener(new RadioListener());
		gui.setSendListener(new SendListener());
		gui.setCheckboxListener(new CheckboxListener());
		gui.setAdressbookListener(new AdressbookListener());
		gui.setFileChooserListener(new FileChooserListener());
	}

	public void update(Observable o, Object obj) {
		// TODO Auto-generated method stub
		Message msg = (Message) obj;
		gui.createNotification(msg.getMsgId().toString() + " will be sent at "
				+ msg.getTimeToSend().toString() + " to "
				+ msg.getRecipients().toString());
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
				array.add("test");
				array.add("test2");
				gui.createDialog(array);
			}
			if (source == gui.getB7()) {
				// Contact c = new Contact();
				gui.getLimo().addElement(gui.getText9().getText());
				gui.getText9().setText("");
			}
			if (source == gui.getB8()) {
				String contact = gui.getAdressList().getSelectedValue();
				if (initSource == gui.getB1()) {
					gui.getText1().setText(contact);
				} else {
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
				message = new EmailMessage();
				String[] strArray = gui.getText1().getText().split(";|;\\s");
				gui.changeBackground(gui.getText1(), new Color(255, 255, 255));
				for (String str : strArray) {
					empf = new EmailAddress(str);
					if (!empf.validate()) {
						isSendable = false;
						gui.changeBackground(gui.getText1(), new Color(255,
								100, 100));
					} else {
						message.addRecipient(empf);
					}
				}

				String[] strArrayCc = gui.getText2().getText().split(";|;\\s");
				gui.changeBackground(gui.getText2(), new Color(255, 255, 255));
				for (String str : strArrayCc) {
					empf = new EmailAddress(str);
					if (!empf.validate()) {
						isSendable = false;
						gui.changeBackground(gui.getText2(), new Color(255,
								100, 100));
					} else {
						// message.addCcRecipient(empf);
					}
				}
				message.setText(gui.getArea1().getText());
				// message.setSbject();
				ch = EmailChannel.getInstance();
			}

			if (gui.getRadioSms().isSelected()) {
				message = new SmsMessage();

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
						message.addRecipient(empf);
					}
				}

				message.setText(gui.getArea1().getText());
				ch = SmsChannel.getInstance();

			}
			if (gui.getRadioMms().isSelected()) {

				message = new MmsMessage(gui.getFc().getSelectedFile());
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
						message.addRecipient(empf);
					}
				}
				ch = MmsChannel.getInstance();

			}

			if (gui.getRadioPrinter().isSelected()) {
				ch = PrintChannel.getInstance();
			}
			if (!isSendable) {
				gui.createWarning();
			} else {
				gui.removeWarning();
				if (gui.getCheckRemainder().isSelected()) {

				} else {
					ch.submit(message);
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