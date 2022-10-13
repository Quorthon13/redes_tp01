package jokenpo.server;

import java.io.IOException;
import java.net.ServerSocket;

import jokenpo.client.Host;
import jokenpo.game.Match;
import jokenpo.game.Message;
import jokenpo.game.Message.MessageType;
import jokenpo.game.Player;
import jokenpo.util.ConstantsUtil;

public class Server {
	public Server() throws IOException, InterruptedException {
		ServerSocket serverSocket = new ServerSocket(ConstantsUtil.PORT);

		Host host_1 = new Host(serverSocket);
		sendNameRequest(host_1);
		Host host_2 = new Host(serverSocket);
		sendNameRequest(host_2);

		Player p_1 = new Player(host_1.readMessage().getContent(), host_1);
		Player p_2 = new Player(host_2.readMessage().getContent(), host_2);

		Match match = new Match(p_1, p_2);
		match.play();

		close(serverSocket, host_1, host_2);
	}

	private void sendNameRequest(Host host) {
		Message message = new Message(MessageType.REQUEST_NAME, "Jogador, insira seu nome: ");
		host.sendMessage(message);
	}

	private void close(ServerSocket serverSocket, Host host_1, Host host_2) throws IOException {
		host_1.close();
		host_2.close();
		serverSocket.close();
	}

}