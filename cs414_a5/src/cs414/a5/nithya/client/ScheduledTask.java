package cs414.a5.nithya.client;

	import java.rmi.RemoteException;
import java.util.TimerTask;
import java.util.Date;
	
	// Create a class extends with TimerTask
	public class ScheduledTask extends TimerTask {

		ParkingGarageStatusClient status; // to display current time

		public ScheduledTask(ParkingGarageStatusClient statusClient) {
			
			this.status=statusClient;
		}
		// Add your task here
		public void run() {
			
			try {
				status.getStatusLabel().setText(status.getGarage().getGarageStatus());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				ExceptionView ev= new ExceptionView(e.getMessage());
			}
		}
	}

