package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerManager {
	private ServerSocket ss;
	private Socket sk;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	public ServerManager() {

	}

	public void serverOn() {
		try {
			ss = new ServerSocket(17771);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
