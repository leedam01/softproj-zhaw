package ch.zhaw.multichannel.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.net.URL;
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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;


import ch.zhaw.multichannel.message.Message;

import com.toedter.calendar.JDateChooser;

public class Gui extends JFrame {
	private JPanel p2, p3, p4, p5, p6;
	private JRadioButton radioEmail, radioSms, radioMms, radioPrinter;
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12, l13, l14;
	private JTextField text1, text2, text3, text4, text5, text6, text7, text8, text9;
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
	
	public Gui(Message m) {

		this.createGuiV2(m);
	}

	public void createGuiV2(Message m) {

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
		p2.add(p6,BorderLayout.SOUTH);
		

		getContentPane().add(p2, BorderLayout.NORTH);

		createAllFields();
		setEmailForm();
		

		b8 = new JButton("Auswählen");
		b7 = new JButton("Hinzufügen");


		add(panel);
		setVisible(true);
		pack();
		//setSize(600, 400);
	}

	public void createAllFields() {
		l2 = new JLabel("Empfänger");
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
		text8 = new JTextField(10);

		b1 = new JButton("Adressbuch");
		b2 = new JButton("Adressbuch");
		b3 = new JButton("Datei");
		b4 = new JButton("Datei");
		b5 = new JButton("Senden");
		b6 = new JButton("Löschen");
		
		fc = new JFileChooser();

		p3 = new JPanel();
		p3.add(b5);
		p3.add(b6);

		area1 = new JTextArea(10, 10);

		date = new JDateChooser();

		checkRemainder = new JCheckBox("Senden zu einem späteren Zeitpunkt");

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
			l10.setVisible(true);
			text8.setVisible(true);

			l11.setVisible(true);
			date.setVisible(true);
		} else {
			l10.setVisible(false);
			text8.setVisible(false);

			l11.setVisible(false);
			date.setVisible(false);
		}
		p2.setVisible(true);
	}

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
			l10.setVisible(true);
			text8.setVisible(true);

			l11.setVisible(true);
			date.setVisible(true);
		} else {
			l10.setVisible(false);
			text8.setVisible(false);

			l11.setVisible(false);
			date.setVisible(false);
		}
		p2.setVisible(true);
	}

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
			l10.setVisible(true);
			text8.setVisible(true);

			l11.setVisible(true);
			date.setVisible(true);
		} else {
			l10.setVisible(false);
			text8.setVisible(false);

			l11.setVisible(false);
			date.setVisible(false);
		}
		p2.setVisible(true);
	}

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

		l10.setVisible(false);
		text8.setVisible(false);

		l11.setVisible(false);
		date.setVisible(false);

		p2.setVisible(true);
	}

	public void setRemainderFields(boolean b) {

		l10.setVisible(b);
		text8.setVisible(b);

		l11.setVisible(b);
		date.setVisible(b);
	}

	public void createDialog(ArrayList<String> li) {

		dialog = new JDialog();

		dialog.setTitle("Adressbuch");
		
		l12 = new JLabel("Neuer Kontakt hinzufügen:");
		text9 = new JTextField(20);
		
		limo = new DefaultListModel<String>();
		
		for (String field:li){
			limo.addElement(field);
		}
		
		adressList = new JList<String>(limo);
		l13 = new JLabel("Kontakt auswählen");
		
        adressList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        adressList.setVisibleRowCount(5);
        adressList.setPreferredSize(new Dimension(100,100));
        JScrollPane listScrollPane = new JScrollPane(adressList);
		
        dialog.setLayout(new BorderLayout());
		
		adressList.setPreferredSize(new Dimension(200,100));
		
		
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
		
		dialog.add(p4,BorderLayout.NORTH);
		dialog.add(p5,BorderLayout.CENTER);
		
		dialog.pack();
		dialog.setModal(true);
		dialog.setVisible(true);
		
	}
	
	public void changeBackground(JComponent c, Color color){
		if (c instanceof JTextField ){
			c.setBackground(color);
		}
	}
	
	public void createWarning(){		 
		l14 = new JLabel("Felder sind entweder leer oder fehlerhaft.", icon, SwingConstants.CENTER);
		l14.setForeground(Color.RED);
		try {
			p2.remove(l14);
			p2.add(l14, BorderLayout.NORTH);
		} catch (Exception e) {
			p2.add(l14, BorderLayout.NORTH);
		}
		revalidate();
	}
	
	public int openFileChooser(FileNameExtensionFilter filter){
		if (filter != null) {
			fc.setFileFilter(filter);
		}
		return fc.showOpenDialog(Gui.this);
	}

	public void setRadioListener(ActionListener a) {
		this.radioEmail.addActionListener(a);
		this.radioSms.addActionListener(a);
		this.radioMms.addActionListener(a);
		this.radioPrinter.addActionListener(a);
	}

	public void setSendListener(ActionListener a) {
		this.b5.addActionListener(a);
	}

	public void setCheckboxListener(ItemListener i) {
		this.checkRemainder.addItemListener(i);
	}

	public void setAdressbookListener(ActionListener a) {
		this.b1.addActionListener(a);
		this.b2.addActionListener(a);
		this.b7.addActionListener(a);
		this.b8.addActionListener(a);
	}
	
	public void setFileChooserListener(ActionListener a){
		this.b3.addActionListener(a);
		this.b4.addActionListener(a);
	}

	public JRadioButton getRadioEmail() {
		return radioEmail;
	}

	public JRadioButton getRadioSms() {
		return radioSms;
	}

	public JRadioButton getRadioMms() {
		return radioMms;
	}

	public JRadioButton getRadioPrinter() {
		return radioPrinter;
	}

	public JButton getB7() {
		return b7;
	}

	public JButton getB8() {
		return b8;
	}

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
	
	
}
