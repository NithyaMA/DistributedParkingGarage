package cs414.a5.nithya.client;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ExceptionView {
	
	public ExceptionView(String s)
	{
		JFrame garageErrorFrame= null;
		JFrame.setDefaultLookAndFeelDecorated(true);
		garageErrorFrame = new JFrame("Error Frame");
		
		//garageErrorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  JPanel mainPanel, displayPanel;
		  mainPanel = new JPanel();
		  JLabel greetingLabel= null;
	      mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	      mainPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	      
	      displayPanel = new JPanel();
	      
	      displayPanel.setBorder(BorderFactory.createCompoundBorder(
	              BorderFactory.createTitledBorder("Error Occured"), 
	              BorderFactory.createEmptyBorder(5,5,5,5)));
	      mainPanel.add(displayPanel);
	      greetingLabel = new JLabel();
		  greetingLabel.setHorizontalAlignment(JLabel.CENTER);
		  greetingLabel.setVerticalAlignment(JLabel.CENTER);
		  greetingLabel.setVerticalTextPosition(JLabel.CENTER);
		  greetingLabel.setHorizontalTextPosition(JLabel.CENTER);
		  greetingLabel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createLoweredBevelBorder(),
	            BorderFactory.createEmptyBorder(5,5,5,5)));

		  greetingLabel.setBorder(BorderFactory.createCompoundBorder(
	            BorderFactory.createEmptyBorder(0,0,10,0),
	            greetingLabel.getBorder()));
		  greetingLabel.setText(s);
		  displayPanel.add(greetingLabel);
		  garageErrorFrame.setContentPane(mainPanel);
			garageErrorFrame.pack();
			garageErrorFrame.setVisible(true);
			
			
		  
	}

}
