package gui16;

/* Kevin Plotzker
 * CISS 241
 * 3/7/2016
 * 
 * ex164.java
 * This program will use a toolbar and menubar to set a jpeg
 * image in a JLabel. Hotkeys are provided, as is a Clear button
 * to clear the displayed image.
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class ex164 extends JApplet implements ActionListener {

	private static final long serialVersionUID = 1L; // Needed to avoid Eclipse warning
	
	JButton btnBrosius = new JButton("Brosius"); // Toolbar variables
	JButton btnMartinez = new JButton("Martinez");
	JButton btnJeter = new JButton("Jeter");
	JButton btnClear = new JButton("Clear Image");
	JToolBar toolBar = new JToolBar(SwingConstants.VERTICAL);
	JPanel toolbarPanel = new JPanel();  //Needed to make toolbar return to same location after 
										 //being moved off the applet

	JMenuItem menuBrosius = new JMenuItem("Brosius"); // Menubar variables
	JMenuItem menuMartinez = new JMenuItem("Martinez");
	JMenuItem menuJeter = new JMenuItem("Jeter");
	JMenuItem menuClear = new JMenuItem("Clear Image");
	JMenu homers = new JMenu("Home Runs");
	JMenuBar menuBar = new JMenuBar();

	ImageIcon brosiusImage = new ImageIcon("brosius1.jpg");  //Images and label
	ImageIcon martinezImage = new ImageIcon("martinez1.jpg");
	ImageIcon jeterImage = new ImageIcon("jeter1.jpg");
	JLabel imageLabel = new JLabel();

	Container c = getContentPane();

	// Init method to build applet components
	public void init() {

		setSize(250, 500); // Sets applet size

		c.setLayout(null); // Sets layout and background color
		c.setBackground(Color.CYAN);

		setJMenuBar(menuBar); // Adds menubar items to menubar
		homers.add(menuBrosius);
		homers.add(menuMartinez);
		homers.add(menuJeter);
		homers.add(menuClear);

		menuBrosius.addActionListener(this); // Wires menubar to listener
		menuMartinez.addActionListener(this);
		menuJeter.addActionListener(this);
		menuClear.addActionListener(this);

		btnBrosius.setMnemonic('b'); // Sets toolbar hotkeys
		btnMartinez.setMnemonic('m');
		btnJeter.setMnemonic('j');
		btnClear.setMnemonic('c');
		toolBar.add(btnBrosius); // Adds toolbar items to toolbar
		toolBar.add(btnMartinez);
		toolBar.add(btnJeter);
		toolBar.add(btnClear);

		btnBrosius.addActionListener(this); // Wires toolbar to listener
		btnMartinez.addActionListener(this);
		btnJeter.addActionListener(this);
		btnClear.addActionListener(this);

		menuBar.add(homers); // Adds components to applet 
		imageLabel.setBounds(25, 40, 300, 500);
		toolbarPanel.setBounds(80, 20, 90, 95);
		toolbarPanel.setBackground(Color.CYAN);
		toolbarPanel.add(toolBar);
		c.add(toolbarPanel);
		c.add(imageLabel);
	}
	
	//Action listener method to display and clear images based on user clicks
	public void actionPerformed(ActionEvent e) {
		
		//Displays Brosius image if Brosius selected on toolbar or menubar 
		if (e.getSource() == btnBrosius || e.getSource() == menuBrosius) {
			imageLabel.setVisible(true);
			imageLabel.setIcon(brosiusImage);
		} 
		//Displays Martinez image if Martinez selected on toolbar or menubar 
		else if (e.getSource() == btnMartinez || e.getSource() == menuMartinez) {
			imageLabel.setVisible(true);
			imageLabel.setIcon(martinezImage);
		} 
		//Displays Jeter image if Jeter selected on toolbar or menubar 
		else if (e.getSource() == btnJeter || e.getSource() == menuJeter) {
			imageLabel.setVisible(true);
			imageLabel.setIcon(jeterImage);
		} 
		//Clears image if clear is selected on toolbar or menubar
		else if (e.getSource() == btnClear || e.getSource() == menuClear)
			imageLabel.setVisible(false);
	}
}
