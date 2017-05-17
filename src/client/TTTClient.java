package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import server.Player;
import server.TTT;

public class TTTClient {
	
	@SuppressWarnings("unused")
	private Player player;
	private TTT ttt;
	@SuppressWarnings("unused")
	private Scanner sc;
	
	public TTTClient() throws InterruptedException {
		// 远程返回一个对象
		try {
			ttt = (TTT)Naming.lookup("rmi://localhost:8888/TTT"); ////////////改
			new LoginWindow(ttt);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	// 入口函数
	public static void main(String[] args) throws InterruptedException {
		new TTTClient();
	}
}
