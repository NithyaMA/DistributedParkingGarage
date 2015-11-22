package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminActionListener3 implements ActionListener{


	private AdminOptionView aov;
	public AdminActionListener3(AdminOptionView aov) {
		this.aov= aov;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	    
		aov.getGarageFrame().dispose();
		aov.run();
	}

}
