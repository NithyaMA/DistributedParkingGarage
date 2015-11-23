package cs414.a5.nithya.client;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cs414.a5.nithya.common.Garage;

public class HelpView {

	private Garage garage;
	private JFrame exitFrame;
	public HelpView(Garage g) {
		
		this.garage=g;
	}
	public void run() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		 exitFrame = new JFrame("Exit Garage");
		exitFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		
		JPanel mainPanel= new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	
	    
	    mainPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Exit Gate Status"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	   JLabel help= new JLabel();
		help.setText("Administrator can help you. Please contact the admin at the Admin Office");
		JButton  addButton = new JButton("OK");
		    addButton.addActionListener(new HelpActionListener(this));
		    mainPanel.add(help);
		    mainPanel.add(addButton);
		    exitFrame.setContentPane(mainPanel);
		    exitFrame.pack();
		    exitFrame.setVisible(true);
		    
		
	}
	public Garage getGarage() {
		return garage;
	}
	public JFrame getExitFrame() {
		return exitFrame;
	}

}
