package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class ExitActionListener2 implements ActionListener {

	private ExitView exitView;
	
	
	public ExitActionListener2(ExitView ev) {
		this.exitView=ev;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		
		 if ("comboBoxChanged".equals(event.getActionCommand())) {
	           
	           if (exitView.getPaymentChoices().getSelectedItem().equals("Cash"))
	           {
	        	   exitView.getExitFrame().dispose();
	        	   exitView.payByCash();
	        	   
	        	   
	           }
	          
	           else if (exitView.getPaymentChoices().getSelectedItem().equals("Card"))
	           {
	        	   exitView.getExitFrame().dispose();
	        	   exitView.payByCard();
	        	   
	           }
	}

	}
	
	
}

