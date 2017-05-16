package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TTT extends Remote {
	
	public boolean setPlayerInfo(String name) throws RemoteException;

	public Player getPlayer(String name) throws RemoteException;
	
	public void searchFor(String name) throws RemoteException, InterruptedException;
	
	public int[] receiveMove(String name) throws RemoteException, InterruptedException;
	
	public void sendMove(String name, int move) throws RemoteException, InterruptedException;
}
