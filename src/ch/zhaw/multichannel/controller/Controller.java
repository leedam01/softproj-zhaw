package ch.zhaw.multichannel.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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

/**
 * The controller class handles all the events from the GUI
 * 
 * @author Olivier Favre
 * @version $LastChangedRevision: 159 $
 * @version $LastChangedDate: 2013-06-12 14:28:24 +0200 $
 */
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
	private Calendar c;

	public Controller() {
		gui = new Gui();
		setListener();
		mess = new Messenger();
		c = Calendar.getInstance();
	}

	/**
	 * sets all the Listener for the GUI-Buttons, -Checkboxes and -Radiobuttons.
	 */
	public void setListener() {
		gui.setRadioListener(new RadioListener());
		gui.setSendListener(new SendListener());
		gui.setCheckboxListener(new CheckboxListener());
		gui.setAdressbookListener(new AdressbookListener());
		gui.setFileChooserListener(new FileChooserListener());
		gui.setClearListener(new ClearListener());
	}

	/**
	 * As soon as a notification is sent from the messanger, this method get
	 * called. It creates a notification for the GUI with the notification text
	 */
	public void update(Observable o, Object obj) {
		Message msg = (Message) obj;
		gui.createNotification(msg.getMsgId().toString() + " will be sent at "
				+ msg.getTimeToSend().toString() + " to "
				+ msg.getRecipients().toString());
	}

	/**
	 * Eventhandler-Class which clears all fields from the GUI
	 */
	class ClearListener implements ActionListener {

		/**
		 * clears all the Textfields after click on button "Clear"
		 * 
		 * @param e
		 *            event triggered by clear-button
		 */
		public void actionPerformed(ActionEvent e) {
			gui.clearAllTextfields();

		}

	}

	/**
	 * Eventhandler-Class which handles the File-Chooser
	 */
	class FileChooserListener implements ActionListener {

		/**
		 * creates the Filechooser dialog after click on the file-button. if the
		 * event came from the mms-file-chooser, add a picture file filter to
		 * the FileChooser
		 * 
		 * @param e
		 *            event triggered by file-buttons
		 */
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

	/**
	 * handles the addressbook button-events
	 */
	class AdressbookListener implements ActionListener {

		/**
		 * calls the GUI to create a Dialog with the addressbook GUI
		 * 
		 * if an address is selected, and the submit-button is clicked, the
		 * address will be added to the addressee-field
		 * 
		 * @param e
		 *            event triggered by addressbook-button or by the buttons in
		 *            the addressbook-dialog
		 */
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

	/**
	 * handles the event after a radio-button is selected
	 */
	class RadioListener implements ActionListener {

		/**
		 * switches between the different forms depending on the selected
		 * radio-button
		 * 
		 * @param e
		 *            event triggered by radio-buttons
		 */
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

	/**
	 * handles the event after the send-button is clicked
	 */
	class SendListener implements ActionListener {
		/**
		 * validates all the necessary fields, adds a warning to the gui if
		 * validate fails, populates the message with all the input when the
		 * validate passes and sends it to the correct channel, clears all the
		 * fields after
		 * 
		 * @param e
		 *            event triggered by send-button
		 */
		public void actionPerformed(ActionEvent e) {
			isSendable = true;
			Channel ch = null;
			// checks the email-addresses for addressee and cc
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
				// handles the remainder-checkbox-event, adds the send-time to
				// the message
				gui.changeBackground(gui.getText8(), new Color(255, 255, 255));
				if (gui.getCheckRemainder().isSelected()) {
					Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
					if (p.matcher(gui.getText8().getText()).matches()) {
						String[] hm = gui.getText8().getText().split(":");
						try {
							c = gui.getDate().getCalendar();
							c.set(Calendar.HOUR, Integer.parseInt(hm[0]));
							c.set(Calendar.MINUTE, Integer.parseInt(hm[1]));
							Date d = c.getTime();
							c.set(Calendar.MINUTE, Integer.parseInt(hm[1]) - 15);
							Date d2 = c.getTime();
							email.setTimeToSend(d);
							email.setTimeToNotify(d2);
						} catch (Exception ex) {
							isSendable = false;
							gui.changeBackground(gui.getDate(), new Color(255,
									100, 100));
						}
					} else {
						gui.changeBackground(gui.getText8(), new Color(255,
								100, 100));
						isSendable = false;
					}
				}
				email.setText(gui.getArea1().getText());
				email.setSubject(gui.getText4().getText());
				email.setAttachement(gui.getFc().getSelectedFile());
				ch = EmailChannel.getInstance();
			}
			// validates the phonenumber fields, checks for remainder
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
				gui.changeBackground(gui.getDate(), new Color(255, 255, 255));
				if (gui.getCheckRemainder().isSelected()) {
					Pattern p = Pattern.compile("[0-9]{2}:[0-9]{2}");
					if (p.matcher(gui.getText8().getText()).matches()) {
						String[] hm = gui.getText8().getText().split(":");
						try {
							c = gui.getDate().getCalendar();
							c.set(Calendar.HOUR, Integer.parseInt(hm[0]));
							c.set(Calendar.MINUTE, Integer.parseInt(hm[1]));
							Date d = c.getTime();
							c.set(Calendar.MINUTE, Integer.parseInt(hm[1]) - 15);
							Date d2 = c.getTime();
							sms.setTimeToSend(d);
							sms.setTimeToNotify(d2);
						} catch (Exception ex) {
							isSendable = false;
							gui.changeBackground(gui.getDate(), new Color(255,
									100, 100));
						}
					} else {
						gui.changeBackground(gui.getText8(), new Color(255,
								100, 100));
						isSendable = false;
					}
				}
				sms.setText(gui.getArea1().getText());
				ch = SmsChannel.getInstance();

			}
			// validates the phonenumber field, checks for remainder
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
					if (p.matcher(gui.getText8().getText()).matches()) {
						String[] hm = gui.getText8().getText().split(":");
						try {
							c = gui.getDate().getCalendar();
							c.set(Calendar.HOUR, Integer.parseInt(hm[0]));
							c.set(Calendar.MINUTE, Integer.parseInt(hm[1]));
							Date d = c.getTime();
							c.set(Calendar.MINUTE, Integer.parseInt(hm[1]) - 15);
							Date d2 = c.getTime();
							mms.setTimeToSend(d);
							mms.setTimeToNotify(d2);
						} catch (Exception ex) {
							isSendable = false;
							gui.changeBackground(gui.getDate(), new Color(255,
									100, 100));
						}
					} else {
						gui.changeBackground(gui.getText8(), new Color(255,
								100, 100));
						isSendable = false;
					}
				}
				ch = MmsChannel.getInstance();

			}
			// handles a send-event to a printer
			// due to time reasons, this option is very slim. You have to know
			// either the name of the printer or the ip-address
			if (gui.getRadioPrinter().isSelected()) {
				print = new PrintMessage();
				print.setText(gui.getArea1().getText());
				PrintAddress printerAddress = new PrintAddress(gui.getText7()
						.getText());
				print.addRecipient(printerAddress);
				ch = PrintChannel.getInstance();
			}
			// if the message passed all field-validations it will be sent
			// otherwise a warning will be created at the top of the screen
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

	/**
	 * handles the event from the remainder-checkbox
	 */
	class CheckboxListener implements ItemListener {

		/**
		 * activates or deactivates the remainder timefields
		 * 
		 * @param e
		 *            event triggered by remainder-checkbox
		 */
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