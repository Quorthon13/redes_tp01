package jokenpo.client;

import java.util.Scanner;

import jokenpo.Host;

public class Client extends Host {
	public Client() {
		super.start();

		Scanner scanner = new Scanner(System.in);

		System.out.println(read());
		String nome = scanner.nextLine();
		send(nome);

		// In the following loop, the client and client handle exchange data.
		while (true) {
			System.out.println(read());
			String tosend = scanner.nextLine();
			send(tosend);
			// Exiting from a while loo should be done when a client gives an exit message.
			if (tosend.toLowerCase().equals("sair")) {
				System.out.println("Connection closing... : " + socket);
				closeSocket();
				System.out.println("Closed");
				break;
			}

			// printing date or time as requested by client
			String result = read();
			System.out.println(result);
		}

		scanner.close();
		closeStream();
	}

}