package jokenpo.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import jokenpo.util.ConstantsUtil;

public class Server {

	public Server() {
		try {
			ServerSocket serverSocket = new ServerSocket(ConstantsUtil.PORT);
			List<Thread> threads = new ArrayList<Thread>();
			while (true) {
				Socket clientSocket = null;

				try {
					clientSocket = serverSocket.accept();

					System.out.println("A new connection identified : " + clientSocket);

					// obtaining input and out streams
					DataInputStream in = new DataInputStream(clientSocket.getInputStream());
					DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
					Thread thread = new Thread(new ServerClientHandler(clientSocket, in, out));
					threads.add(thread);

					System.out.println("Thread assigned");
				} catch (Exception e) {
					clientSocket.close();
					serverSocket.close();
					e.printStackTrace();
					break;
				}

				if (threads.size() == 2) {
					System.out.println("2 jogadores conectados, iniciando jogo...");
					threads.stream().forEach(t -> t.start());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}