package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import sender.Message;

public class GUI extends JFrame{
	private JMenuBar menubar;
	private JMenu menu;
	private JMenuItem save, load, close;
	private JPanel p1;
	private JCheckBox checkEmail, checkSms, checkMms, checkPrinter;
	private JLabel l1, l2, l3, l4, l5, l6;
	private JTextField text1, text2, text3, text4;
	private JTextArea area1;
	private JButton b1, b2, b3, b4;
	
	public GUI(Message m){
		
		this.createGui(m);
	}
	public void createGui(Message m) {
		
		
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
		
		c.insets = new Insets(5,5,5,5);
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
		checkEmail.setSelected(true);
		checkEmail.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkEmail,c);
		
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkSms = new JCheckBox("SMS");
		checkSms.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkSms,c);
		
		c.gridx = 3;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkMms = new JCheckBox("MMS");
		checkMms.setSelected(true);
		checkMms.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkMms,c);
		
		c.gridx = 4;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 1;
		checkPrinter = new JCheckBox("Printer");
		checkPrinter.setVerticalTextPosition(SwingConstants.TOP);
		p1.add(checkPrinter,c);


		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		c.gridheight = 1;
		l2 = new JLabel("Adresse");
		p1.add(l2,c);
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridwidth = 5;
		c.gridheight = 1;
		text1 = new JTextField(20);
		//text1.setText(m.getAdress);
		p1.add(text1,c);
		
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
			p1.add(text2,c);
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
		
		if (checkMms.isSelected()){
			c.gridx = 0;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 1;
			c.gridheight = 1;
			l5 = new JLabel("Bild");
			p1.add(l5,c);
			
			c.gridx = 1;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 3;
			c.gridheight = 1;
			text3 = new JTextField(15);
			p1.add(text3,c);
			
			c.gridx = 4;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 2;
			c.gridheight = 1;
			b1 = new JButton("Bild wählen");
			p1.add(b1,c);
		}
		
		if (checkEmail.isSelected()){
			c.gridx = 0;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 1;
			c.gridheight = 1;
			l5 = new JLabel("Anhang");
			p1.add(l5,c);
			
			c.gridx = 1;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 3;
			c.gridheight = 1;
			text4 = new JTextField(15);
			p1.add(text4,c);
			
			c.gridx = 4;
			c.gridy = GridBagConstraints.RELATIVE;
			c.gridwidth = 2;
			c.gridheight = 1;
			b4 = new JButton("File wählen");
			p1.add(b4,c);
		}	
		
		c.gridx = 1;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridheight = 1;
		b2 = new JButton("Senden");
		p1.add(b2,c);
		
		c.gridx = 3;
		c.gridy = GridBagConstraints.RELATIVE;
		c.gridwidth = 2;
		c.gridheight = 1;
		b3 = new JButton("Löschen");
		p1.add(b3,c);
		
		add(p1);
		
		setSize(500,800);
		setVisible(true);
	}
	
    public static void main(String[] args) {
        GUI t = new GUI(new Message());
    }
}
