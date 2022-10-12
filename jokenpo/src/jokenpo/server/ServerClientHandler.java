package jokenpo.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import jokenpo.Host;
import jokenpo.util.ConstantsUtil;

class ServerClientHandler extends Host implements Runnable {

	// Constructor
	public ServerClientHandler(Socket socket, DataInputStream in, DataOutputStream out) throws IOException {
		super(socket, in, out);
	}

	@Override
	public void run() {
		int matchNumber = 1;
		String receivedString;

		send("Nome do jogador: ");
		String nome = read();
		while (matchNumber <= ConstantsUtil.MAX_MATCHES) {
			send("Partida " + (matchNumber++)
			        + "\nDigite \"pedra\", \"papel\" ou \"tesoura\", ou \"sair\" para sair: ");

			receivedString = read();
			System.out.println(receivedString);

			if (receivedString.toLowerCase().equals("sair")) {
				closeSocket();
				break;
			}

			send("ok!");

		}

		closeStream();
	}

}