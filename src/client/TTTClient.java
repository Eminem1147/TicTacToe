package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import server.TTT;

public class TTTClient {
	
	private TTT ttt;
	
	public TTTClient() throws InterruptedException {
		try {
			// 远程返回一个对象
			ttt = (TTT)Naming.lookup("rmi://localhost:8888/TTT"); // 可以修改
			new LoginWindow(ttt);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	// 客户端入口函数
	public static void main(String[] args) throws InterruptedException {
		new TTTClient();
	}
}
