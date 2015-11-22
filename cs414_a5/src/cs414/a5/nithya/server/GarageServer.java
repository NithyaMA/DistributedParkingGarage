package cs414.a5.nithya.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import cs414.a5.nithya.common.Garage;


public class GarageServer {

	public GarageServer(){
		try {
			LocateRegistry.createRegistry(1099);
			
		} catch (RemoteException e) {
			try {
				LocateRegistry.getRegistry(1099);
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		try {
			
			Garage garage= new GarageImpl();
			
			Naming.rebind("garageServer", garage);
			
		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}
	}

	// run the program using 
	// java CalculatorServer <host> <port>

	public static void main(String args[]) {
		
		new GarageServer();
	}
}
	

