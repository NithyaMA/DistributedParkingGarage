package cs414.a5.nithya.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import cs414.a5.nithya.common.CustomException;

public class ExitActionListener5 implements ActionListener {
	
	private ExitView exitView;
	
	 public ExitActionListener5(ExitView ev) {
		
		 this.exitView=ev;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cardNum= exitView.getCardNumField().getText();
		Long cardNumber= Long.parseLong(cardNum);
		String dateOfExpiry= exitView.getCardExpiryField().getText();
		DateFormat format= new SimpleDateFormat("MM/dd/yyyy");
		Date dateOfExpir=null;
		exitView.getExitFrame().dispose();
		try {
			dateOfExpir = format.parse(dateOfExpiry);
		} catch (ParseException e1) {
			
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		try {
			if(exitView.getGarage().payParkingFeeByCard(exitView.getTicketRefNum(), cardNumber, dateOfExpir))
				{System.out.println("entered loop");
				exitView.cardEndView();}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			ExceptionView ev= new ExceptionView(e1.getMessage());
		}
		
		catch (CustomException c)
		{
			ExceptionView ev= new ExceptionView(c.getMessage());
			 try {
					if(exitView.getGarage().getExitKiosk().getName().equals("ex1"))
						  ParkingGarageExit1.createAndShowGUI();
					else if (exitView.getGarage().getExitKiosk().getName().equals("ex2"))
						ParkingGarageExit2.createAndShowGUI();
				} catch (RemoteException er) {
					// TODO Auto-generated catch block
					ExceptionView ev1= new ExceptionView(er.getMessage());
				} catch (NotBoundException enb) {
					// TODO Auto-generated catch block
					ExceptionView ev2= new ExceptionView(enb.getMessage());
				}
		}
		
	}

}
