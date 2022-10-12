package jokenpo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import jokenpo.util.ConstantsUtil;

public class Host {

	protected Socket socket;
	protected DataInputStream in;
	protected DataOutputStream out;

	public Host() {
	}

	public Host(Socket socket, DataInputStream in, DataOutputStream out) {
		this.socket = socket;
		this.in = in;
		this.out = out;
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

	public void closeSocket() {
		try {
			System.out.println("Client " + socket + " sends exit...");
			System.out.println("Connection closing...");
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

	public String read() {
		String s = "";

		try {
			s = in.readUTF();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return s;
	}

	public void send(String content) {
		try {
			out.writeUTF(content);
		} catch (IOException e) {
			e.printStackTrace();
		}
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
