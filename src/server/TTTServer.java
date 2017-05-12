package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class TTTServer {

	public static void main(String[] args) {

		System.out.println("Main OK");

		try {
			TTT tttGame = new TTTImpl();

			System.out.println("After create");

			LocateRegistry.createRegistry(8888);

			Naming.bind("rmi://localhost:8888/TTT", tttGame);//////////////////////

			System.out.println("TTT server ready");

		} catch (Exception e) {
			System.out.println("TTT server main " + e.getMessage());
		}

		System.out.println("Please start the client...");

	}

}
