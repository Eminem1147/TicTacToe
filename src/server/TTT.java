package server;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface TTT extends Remote {
	
	public boolean checkName(String name) throws RemoteException;
	
	public boolean setPlayerInfo(String name) throws RemoteException;

	public Player getPlayer(String name) throws RemoteException;
	
	public void searchFor(String name) throws RemoteException, InterruptedException;
	
	public int[] receiveMove(String name) throws RemoteException, InterruptedException;
	
	public void sendMove(String name, int move) throws RemoteException, InterruptedException;
	
	public void changeFirst(String name) throws RemoteException;
	
	public void sendNewGame(String name) throws RemoteException;
	
	public void unregister(String name) throws RemoteException;
	
	public boolean checkExist(String name) throws RemoteException;
}
