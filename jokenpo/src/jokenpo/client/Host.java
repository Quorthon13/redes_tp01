package jokenpo.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import com.google.gson.Gson;

import jokenpo.game.Message;
import jokenpo.util.ConstantsUtil;

public class Host {

	protected Socket socket;
	protected DataInputStream in;
	protected DataOutputStream out;

	public Host() {
	}

	public Host(ServerSocket serverSocket) {
		try {
			socket = serverSocket.accept();
			System.out.println("Nova conexão: " + socket);
			in = new DataInputStream(this.socket.getInputStream());
			out = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			socket = new Socket(InetAddress.getByName(ConstantsUtil.URL), ConstantsUtil.PORT);
			in = new DataInputStream(socket.getInputStream());
			out = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		closeStream();
		closeSocket();
	}

	public void closeSocket() {
		try {
			System.out.println("Closing client" + socket + "...");
			socket.close();
			System.out.println("Closed");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeStream() {
		try {
			in.close();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String read() {
		String s = "";

		try {
			s = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	private void send(String content) {
		try {
			out.writeUTF(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Message message) {
		Gson gson = new Gson();
		String json = gson.toJson(message);
		send(json);
	}

	public Message readMessage() {
		Gson gson = new Gson();
		return gson.fromJson(read(), Message.class);
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public DataInputStream getIn() {
		return in;
	}

	public void setIn(DataInputStream in) {
		this.in = in;
	}

	public DataOutputStream getOut() {
		return out;
	}

	public void setOut(DataOutputStream out) {
		this.out = out;
	}
}
