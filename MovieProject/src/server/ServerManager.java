package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerManager {
	private ServerSocket ss;
	private boolean flag;
	public static ArrayList<ObjectOutputStream> usersList = new ArrayList();

	public ServerManager() {
		flag = true;
		serverOn();
		while (flag) {
			connection();
		}
	}

	public void serverOn() {
		try {
			ss = new ServerSocket(17771);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connection() {
		try {
			Socket sk = ss.accept();
			ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());
			usersList.add(oos);
			Thread th = new Thread(new ServerThread(sk, oos, ois, usersList));
			th.start();
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
	}

}
