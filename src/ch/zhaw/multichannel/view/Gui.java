package ch.zhaw.multichannel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.toedter.calendar.JDateChooser;

/**
 * The GUI-class creates all the gui elements. It provides all the needed
 * options for the controller class
 * 
 * @author Olivier Favre
 * @version $LastChangedRevision: 159 $
 * @version $LastChangedDate: 2013-06-12 14:28:24 +0200 $
 */
public class Gui extends JFrame {
	private JPanel p2, p3, p4, p5, p6;
	private JRadioButton radioEmail, radioSms, radioMms, radioPrinter;
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14;
	private JTextField text1, text2, text3, text4, text5, text6, text7, text8,
			text9;
	private JTextArea area1;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8;
	private GroupLayout layout;
	private JCheckBox checkRemainder;
	private JList<String> adressList;
	private JDateChooser date;
	private DefaultListModel<String> limo;
	private JDialog dialog;
	private JFileChooser fc;
	private ImageIcon icon;

	/**
	 * Constructor for the gui. When called, it created the initial version of
	 * the gui.
	 */
	public Gui() {

		this.createGuiV2();
	}

	/**
	 * creates the initial version of the GUI.It is called V2 since it is the
	 * second version of the gui. The first version was made with GridBagLayout
	 * but is now deprecated
	 */
	private void createGuiV2() {

		getContentPane().setLayout(new BorderLayout());

		JComponent panel = new JPanel();

		layout = new GroupLayout(panel);
		layout.setHonorsVisibility(true);
		panel.setLayout(layout);

		// Turn on automatically adding gaps between components
		layout.setAutoCreateGaps(true);

		// Turn on automatically creating gaps between components that touch
		// the edge of the container and the container.
		layout.setAutoCreateContainerGaps(true);

		p2 = new JPanel();
		l1 = new JLabel("Messagetype:");

		// Create the radio buttons.
		radioEmail = new JRadioButton("Email");
		radioSms = new JRadioButton("SMS");
		radioMms = new JRadioButton("MMS");
		radioPrinter = new JRadioButton("Printer");

		// Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(radioEmail);
		group.add(radioSms);
		group.add(radioMms);
		group.add(radioPrinter);

		radioEmail.setSelected(true);

		p2.setLayout(new BorderLayout());
		p6 = new JPanel();
		p6.setLayout(new FlowLayout());
		p6.add(l1);
		p6.add(radioEmail);
		p6.add(radioSms);
		p6.add(radioMms);
		p6.add(radioPrinter);
		p2.add(p6, BorderLayout.SOUTH);

		getContentPane().add(p2, BorderLayout.NORTH);

		// create the form-fields
		createAllFields();
		setEmailForm();

		b8 = new JButton("Auswaehlen");
		b7 = new JButton("Hinzufuegen");

		add(panel);
		setVisible(true);
		// setSize(600, 400);
		pack();
	}

	/**
	 * creates all the fields for the different forms. All components are
	 * created here in a GroupLayout. In the process, the components will be
	 * activated or deactivated depending on the selected form.
	 */
	private void createAllFields() {
		l2 = new JLabel("Empfaenger");
		l3 = new JLabel("CC");
		l4 = new JLabel("Telefonnummer");
		l5 = new JLabel("Betreff");
		l6 = new JLabel("Text");
		l7 = new JLabel("Attachement");
		l8 = new JLabel("Bild");
		l9 = new JLabel("Printer Name");
		l10 = new JLabel("Zeit");
		l11 = new JLabel("Datum");
		text1 = new JTextField();
		text2 = new JTextField();
		text3 = new JTextField();
		text4 = new JTextField();
		text5 = new JTextField();
		text6 = new JTextField();
		text7 = new JTextField();
		text8 = new JTextField("HH:MM");

		b1 = new JButton("Adressbuch");
		b2 = new JButton("Adressbuch");
		b3 = new JButton("Datei");
		b4 = new JButton("Datei");
		b5 = new JButton("Senden");
		b6 = new JButton("Loeschen");

		fc = new JFileChooser();

		p3 = new JPanel();
		p3.add(b5);
		p3.add(b6);

		area1 = new JTextArea(10, 10);
		area1.setLineWrap(true);
		area1.setWrapStyleWord(true);

		date = new JDateChooser();

		checkRemainder = new JCheckBox("Senden zu einem spaeteren Zeitpunkt");

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(l2).addComponent(l3)
								.addComponent(l4).addComponent(l5)
								.addComponent(l9).addComponent(l6)
								.addComponent(l7).addComponent(l8)
								.addComponent(l10).addComponent(l11))
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(text1).addComponent(text2)
								.addComponent(text3).addComponent(text4)
								.addComponent(text7).addComponent(area1)
								.addComponent(text5).addComponent(text6)
								.addComponent(checkRemainder)
								.addComponent(text8).addComponent(date)
								.addComponent(p3))
				.addGroup(
						layout.createParallelGroup(GroupLayout.Alignment.CENTER)
								.addComponent(b1).addComponent(b2)
								.addComponent(b3).addComponent(b4)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l2).addComponent(text1)
								.addComponent(b1))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l3).addComponent(text2)
								.addComponent(b2))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l4).addComponent(text3))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l5).addComponent(text4))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l9).addComponent(text7))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l6).addComponent(area1))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l7).addComponent(text5)
								.addComponent(b3))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l8).addComponent(text6)
								.addComponent(b4))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(
								checkRemainder))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l10).addComponent(text8))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(l11).addComponent(date))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(p3)));

	}

	/**
	 * sets the form to email, enables/disables all the necessary fields
	 */
	public void setEmailForm() {
		l2.setVisible(true);
		text1.setVisible(true);
		b1.setVisible(true);

		l3.setVisible(true);
		text2.setVisible(true);
		b2.setVisible(true);

		l4.setVisible(false);
		text3.setVisible(false);

		l5.setVisible(true);
		text4.setVisible(true);

		l9.setVisible(false);
		text7.setVisible(false);

		l6.setVisible(true);
		area1.setVisible(true);

		l7.setVisible(true);
		text5.setVisible(true);
		b3.setVisible(true);

		l8.setVisible(false);
		text6.setVisible(false);
		b4.setVisible(false);

		checkRemainder.setVisible(true);

		if (checkRemainder.isSelected()) {
			setRemainderFields(true);
		} else {
			setRemainderFields(false);
		}
		p2.setVisible(true);
		pack();
	}

	/**
	 * sets the form to SMS, all the necessary fields will be enabled/disabled
	 */
	public void setSmsForm() {
		l2.setVisible(false);
		text1.setVisible(false);
		b1.setVisible(false);

		l3.setVisible(false);
		text2.setVisible(false);
		b2.setVisible(false);

		l4.setVisible(true);
		text3.setVisible(true);

		l5.setVisible(false);
		text4.setVisible(false);

		l9.setVisible(false);
		text7.setVisible(false);

		l6.setVisible(true);
		area1.setVisible(true);

		l7.setVisible(false);
		text5.setVisible(false);
		b3.setVisible(false);

		l8.setVisible(false);
		text6.setVisible(false);
		b4.setVisible(false);

		checkRemainder.setVisible(true);

		if (checkRemainder.isSelected()) {
			setRemainderFields(true);
		} else {
			setRemainderFields(false);
		}
		p2.setVisible(true);
		pack();
	}

	/**
	 * sets the form to MMS, all the necessary fields will be enabled/disabled
	 */
	public void setMmsForm() {
		l2.setVisible(false);
		text1.setVisible(false);
		b1.setVisible(false);

		l3.setVisible(false);
		text2.setVisible(false);
		b2.setVisible(false);

		l4.setVisible(true);
		text3.setVisible(true);

		l5.setVisible(false);
		text4.setVisible(false);

		l9.setVisible(false);
		text7.setVisible(false);

		l6.setVisible(false);
		area1.setVisible(false);

		l7.setVisible(false);
		text5.setVisible(false);
		b3.setVisible(false);

		l8.setVisible(true);
		text6.setVisible(true);
		b4.setVisible(true);

		checkRemainder.setVisible(true);

		if (checkRemainder.isSelected()) {
			setRemainderFields(true);
		} else {
			setRemainderFields(false);
		}
		p2.setVisible(true);
		pack();
	}

	/**
	 * sets the form to printer, all the necessary fields will be
	 * enabled/disabled
	 */
	public void setPrinterForm() {
		l2.setVisible(false);
		text1.setVisible(false);
		b1.setVisible(false);

		l3.setVisible(false);
		text2.setVisible(false);
		b2.setVisible(false);

		l4.setVisible(false);
		text3.setVisible(false);

		l5.setVisible(false);
		text4.setVisible(false);

		l9.setVisible(true);
		text7.setVisible(true);

		l6.setVisible(true);
		area1.setVisible(true);

		l7.setVisible(false);
		text5.setVisible(false);
		b3.setVisible(false);

		l8.setVisible(false);
		text6.setVisible(false);
		b4.setVisible(false);

		checkRemainder.setVisible(false);

		setRemainderFields(false);

		p2.setVisible(true);
		pack();
	}

	/**
	 * enabled/disables the remainder fields
	 */
	public void setRemainderFields(boolean b) {
		l10.setVisible(b);
		text8.setVisible(b);

		l11.setVisible(b);
		date.setVisible(b);
	}

	/**
	 * creates the dialog for the addressbook
	 * 
	 * @param li
	 *            all the stored contacts
	 */
	public void createDialog(ArrayList<String> li) {

		dialog = new JDialog();

		dialog.setTitle("Adressbuch");

		l12 = new JLabel("Neuer Kontakt hinzufuegen:");
		text9 = new JTextField(20);

		limo = new DefaultListModel<String>();

		for (String field : li) {
			limo.addElement(field);
		}

		adressList = new JList<String>(limo);
		l13 = new JLabel("Kontakt auswählen");

		adressList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adressList.setVisibleRowCount(5);
		adressList.setPreferredSize(new Dimension(100, 100));
		JScrollPane listScrollPane = new JScrollPane(adressList);

		dialog.setLayout(new BorderLayout());

		adressList.setPreferredSize(new Dimension(200, 100));

		p4 = new JPanel();
		p5 = new JPanel();
		p4.setLayout(new FlowLayout());
		p5.setLayout(new FlowLayout());

		p4.add(l12);
		p4.add(text9);
		p4.add(b7);

		p5.add(l13);
		p5.add(listScrollPane);
		p5.add(b8);

		dialog.add(p4, BorderLayout.NORTH);
		dialog.add(p5, BorderLayout.CENTER);

		dialog.pack();
		dialog.setModal(true);
		dialog.setVisible(true);

	}

	/**
	 * changes the background of a component
	 * 
	 * @param c
	 *            the component to be colored
	 * @param color
	 *            the color to be painted
	 */
	public void changeBackground(JComponent c, Color color) {
		if (c instanceof JTextField) {
			c.setBackground(color);
		}
	}

	/**
	 * creates a warning on the top of the frame
	 */
	public void createWarning() {
		l14 = new JLabel("Felder sind entweder leer oder fehlerhaft.", icon,
				SwingConstants.CENTER);
		l14.setForeground(Color.RED);
		removeWarning();
		p2.add(l14, BorderLayout.NORTH);
		revalidate();
	}

	/**
	 * removes the warning on top of the screen, if it exists
	 */
	public void removeWarning() {
		try {
			p2.remove(l14);
		} catch (Exception e) {
			System.out.println("Warning nicht vorhanden");
		}
	}

	/**
	 * creates an information-dialog
	 * 
	 * @param str
	 *            text to be displayed in the dialog
	 */
	public void createNotification(String str) {
		// JOptionPane op = new JOptionPane();
		JOptionPane.showMessageDialog(this, str);
	}

	/**
	 * creates an error dialog
	 * 
	 * @param str
	 *            text to be displayed in the dialog
	 */
	public void createErrorNotification(String str) {
		JOptionPane.showMessageDialog(dialog, str, "Fehler",
				JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * resets all the textfields
	 */
	public void clearAllTextfields() {
		this.text1.setText("");
		this.text2.setText("");
		this.text3.setText("");
		this.text4.setText("");
		this.text5.setText("");
		this.text6.setText("");
		this.text7.setText("");
		this.text8.setText("");
		this.area1.setText("");
	}

	/**
	 * opens the file-chooser-dialog
	 * 
	 * @param filter
	 *            file extension filter for the file chooser
	 * @return the return state of the file chooser on popdown:
	 *         JFileChooser.CANCEL_OPTION , JFileChooser.APPROVE_OPTION
	 *         ,JFileChooser.ERROR_OPTION. If an error occurs or the dialog is
	 *         dismissed
	 */
	public int openFileChooser(FileNameExtensionFilter filter) {
		if (filter != null) {
			fc.setFileFilter(filter);
		}
		return fc.showOpenDialog(Gui.this);
	}

	/**
	 * sets the Listener for the clear-button
	 * 
	 * @param a
	 *            action-listener to be added to the button
	 */
	public void setClearListener(ActionListener a) {
		this.b6.addActionListener(a);
	}
	
	/**
	 * sets the Listener for the radio-buttons
	 * 
	 * @param a
	 *            action-listener to be added to the radio-buttons
	 */
	public void setRadioListener(ActionListener a) {
		this.radioEmail.addActionListener(a);
		this.radioSms.addActionListener(a);
		this.radioMms.addActionListener(a);
		this.radioPrinter.addActionListener(a);
	}

	/**
	 * sets the Listener for the send-button
	 * 
	 * @param a
	 *            action-listener to be added to the button
	 */
	public void setSendListener(ActionListener a) {
		this.b5.addActionListener(a);
	}

	/**
	 * sets the Listener for the remainder-checkbox
	 * 
	 * @param i
	 *            item-listener to be added to the remainder-checkbox
	 */
	public void setCheckboxListener(ItemListener i) {
		this.checkRemainder.addItemListener(i);
	}

	/**
	 * sets the Listener for the addressbook-button
	 * 
	 * @param a
	 *            action-listener to be added to the button
	 */
	public void setAdressbookListener(ActionListener a) {
		this.b1.addActionListener(a);
		this.b2.addActionListener(a);
		this.b7.addActionListener(a);
		this.b8.addActionListener(a);
	}

	/**
	 * sets the Listener for the filechooser-button
	 * 
	 * @param a
	 *            action-listener to be added to the button
	 */
	public void setFileChooserListener(ActionListener a) {
		this.b3.addActionListener(a);
		this.b4.addActionListener(a);
	}

	
	/**
	 * getter for the email-radiobutton
	 *
	 * @return the radiobutton for email
	 */
	public JRadioButton getRadioEmail() {
		return radioEmail;
	}

	/**
	 * getter for the sms-radiobutton
	 *
	 * @return the radiobutton for sms
	 */
	public JRadioButton getRadioSms() {
		return radioSms;
	}

	/**
	 * getter for the mms-radiobutton
	 *
	 * @return the radiobutton for mms
	 */
	public JRadioButton getRadioMms() {
		return radioMms;
	}

	/**
	 * getter for the printer-radiobutton
	 *
	 * @return the radiobutton for print
	 */
	public JRadioButton getRadioPrinter() {
		return radioPrinter;
	}

	/**
	 * getter for the choose button in the addressbook
	 *
	 * @return the choose-button in the addressbook
	 */
	public JButton getB7() {
		return b7;
	}

	/**
	 * getter for the add button in the addressbook
	 *
	 * @return the add-button in the addressbook
	 */
	public JButton getB8() {
		return b8;
	}
	/**
	 * getter for the list-model in the addressbook
	 *
	 * @return the list-model in the addressbook
	 */
	public DefaultListModel<String> getLimo() {
		return limo;
	}
	 
	public JTextField getText9() {
		return text9;
	}

	public JList<String> getAdressList() {
		return adressList;
	}

	public JButton getB1() {
		return b1;
	}

	public JButton getB2() {
		return b2;
	}

	public JDialog getDialog() {
		return dialog;
	}

	public JTextField getText1() {
		return text1;
	}

	public JTextField getText2() {
		return text2;
	}

	public JButton getB3() {
		return b3;
	}

	public JButton getB4() {
		return b4;
	}

	public JTextField getText5() {
		return text5;
	}

	public JTextField getText6() {
		return text6;
	}

	public JFileChooser getFc() {
		return fc;
	}

	public JTextField getText3() {
		return text3;
	}

	public JCheckBox getCheckRemainder() {
		return checkRemainder;
	}

	public JTextArea getArea1() {
		return area1;
	}

	public JTextField getText4() {
		return text4;
	}

	public JTextField getText7() {
		return text7;
	}

	public JTextField getText8() {
		return text8;
	}

	public JDateChooser getDate() {
		return date;
	}

}
