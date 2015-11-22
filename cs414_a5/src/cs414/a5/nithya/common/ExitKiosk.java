package cs414.a5.nithya.common;

public interface ExitKiosk extends java.rmi.Remote {

	public boolean isExitGate() throws java.rmi.RemoteException;
	
	public void openExitGate() throws java.rmi.RemoteException;
	
	public void closeExitGate() throws java.rmi.RemoteException;
	
	public String getName() throws java.rmi.RemoteException;
}
