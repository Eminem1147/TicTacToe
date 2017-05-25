package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TTTServer {

	public static void main(String[] args) {

		try {
			TTT tttGame = new TTTImpl();

			LocateRegistry.createRegistry(8888);

			Naming.bind("rmi://localhost:8888/TTT", tttGame);// 可以修改

			System.out.println("TTT server ready");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Please start the client...");

	}

}
