package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sender.Message;

public class GUI extends JFrame {
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem save, load, close;
	private JPanel p1, p2, p3;
	private JRadioButton radioEmail, radioSms, radioMms, radioPrinter;
	private JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
	private JTextField text1, text2, text3, text4, text5, text6, text7;
	private JTextArea area1;
	private JButton b1, b2, b3, b4, b5, b6;
	private GroupLayout.SequentialGroup hGroup, vGroup;
	private GroupLayout layout;

	public GUI(Message m) {

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

		/*
		 * // Create a sequential group for the horizontal axis. text1 = new
		 * JTextField(20); text2 = new JTextField(20); l1 = new
		 * JLabel("Betreff"); l2 = new JLabel("Absender");
		 */

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

		p2.setLayout(new FlowLayout());
		p2.add(l1);
		p2.add(radioEmail);
		p2.add(radioSms);
		p2.add(radioMms);
		p2.add(radioPrinter);
		

		getContentPane().add(p2,BorderLayout.NORTH);

		createAllFields();
		setEmailForm();

		// layout.setHorizontalGroup(hGroup);
		// layout.setVerticalGroup(vGroup);

		add(panel);
		setVisible(true);
		setSize(600, 400);
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
		text1 = new JTextField();
		text2 = new JTextField();
		text3 = new JTextField();
		text4 = new JTextField();
		text5 = new JTextField();
		text6 = new JTextField();
		text7 = new JTextField();
		
		b1 = new JButton("Adressbuch");
		b2 = new JButton("Adressbuch");
		b3 = new JButton("Datei");
		b4 = new JButton("Datei");
		b5 = new JButton("Senden");
		b6 = new JButton("Löschen");
		
		p3 = new JPanel();
		p3.add(b5);
		p3.add(b6);

		area1 = new JTextArea(10,10);

		layout.setHorizontalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.CENTER).addComponent(l2)
								.addComponent(l3).addComponent(l4)
								.addComponent(l5).addComponent(l9).addComponent(l6).addComponent(l7).addComponent(l8))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.CENTER)
								.addComponent(text1).addComponent(text2).addComponent(text3)
								.addComponent(text4).addComponent(text7).addComponent(area1)
								.addComponent(text5).addComponent(text6).addComponent(p3))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.CENTER).addComponent(b1)
								.addComponent(b2).addComponent(b3).addComponent(b4)));

		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l2)
								.addComponent(text1).addComponent(b1))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l3)
								.addComponent(text2).addComponent(b2))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l4)
								.addComponent(text3))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l5)
								.addComponent(text4))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l9)
								.addComponent(text7))				
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l6)
								.addComponent(area1))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l7)
								.addComponent(text5).addComponent(b3))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(l8)
								.addComponent(text6).addComponent(b4))
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE).addComponent(p3)));

			}
	
	public void setEmailForm(){
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
		
		p2.setVisible(true);
	}
	
	public void setSmsForm(){
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
		
		p2.setVisible(true);
	}
	
	public void setMmsForm(){
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
		
		p2.setVisible(true);
	}
	
	public void setPrinterForm(){
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
		
		p2.setVisible(true);
	}
	
	public void setRadioListener(ActionListener a) {
		this.radioEmail.addActionListener(a);
		this.radioSms.addActionListener(a);
		this.radioMms.addActionListener(a);
		this.radioPrinter.addActionListener(a);
	}

	public void setSendListener(ActionListener a) {
		this.b2.addActionListener(a);
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

	
	/*public void createGui(Message m) {

		save = new JMenuItem("Nachricht speichern");
		load = new JMenuItem("Nachricht laden");
		close = new JMenuItem("Schliessen");

		menu = new JMenu("Datei");

		menu.add(save);
		menu.add(load);
		menu.add(close);

		menubar = new JMenuBar();
		menubar.add(menu);

		setJMenuBar(menubar);

		p1 = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.insets = new Insets(5, 5, 5, 5);
		c.anchor = GridBagConstraints.WEST;

		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		l1 = new JLabel("Messagetyp:");
		p1.add(l1, c);

		c.gridx = 1;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkEmail = new JCheckBox("Email");
		checkEmail.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkEmail, c);

		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkSms = new JCheckBox("SMS");
		checkSms.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkSms, c);

		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkMms = new JCheckBox("MMS");
		checkMms.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkMms, c);

		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkPrinter = new JCheckBox("Printer");
		checkPrinter.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkPrinter, c);

		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		l2 = new JLabel("Adresse");
		p1.add(l2, c);

		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 1;
		text1 = new JTextField(20);
		// text1.setText(m.getAdress);
		p1.add(text1, c);

		if (checkEmail.isSelected() || checkMms.isSelected()
				|| checkSms.isSelected()) {

			c.gridx = 0;
			c.gridy = 2;
			c.gridwidth = 1;
			c.gridheight = 1;
			l3 = new JLabel("Betreff");
			p1.add(l3, c);

			c.gridx = 1;
			c.gridy = 2;
			c.gridwidth = 5;
			c.gridheight = 1;
			text2 = new JTextField(20);
			p1.add(text2, c);
		}

		if (checkEmail.isSelected() || checkPrinter.isSelected()
				|| checkSms.isSelected()) {
			c.gridx = 0;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 1;
			c.gridheight = 3;
			l4 = new JLabel("Messagetext");
			p1.add(l4, c);

			c.gridx = 1;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 5;
			c.gridheight = 3;
			area1 = new JTextArea(4, 20);
			area1.setLineWrap(true);
			area1.setAutoscrolls(true);
			p1.add(area1, c);
		}

		if (checkMms.isSelected()) {
			c.gridx = 0;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 1;
			c.gridheight = 1;
			l5 = new JLabel("Bild");
			p1.add(l5, c);

			c.gridx = 1;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 3;
			c.gridheight = 1;
			text3 = new JTextField(15);
			p1.add(text3, c);

			c.gridx = 4;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 2;
			c.gridheight = 1;
			b1 = new JButton("Bild wählen");
			p1.add(b1, c);
		}

		if (checkEmail.isSelected()) {
			c.gridx = 0;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 1;
			c.gridheight = 1;
			l5 = new JLabel("Anhang");
			p1.add(l5, c);

			c.gridx = 1;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 3;
			c.gridheight = 1;
			text4 = new JTextField(15);
			p1.add(text4, c);

			c.gridx = 4;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 2;
			c.gridheight = 1;
			b4 = new JButton("File wählen");
			p1.add(b4, c);
		}

		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridheight = 1;
		b2 = new JButton("Senden");
		p1.add(b2, c);

		c.gridx = 3;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridheight = 1;
		b3 = new JButton("Löschen");
		p1.add(b3, c);

		add(p1);

		setSize(500, 800);
		setVisible(true);
	}*/
}
