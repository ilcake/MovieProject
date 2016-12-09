package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import server.chat.ServerChat;

public class ServerReceiver {
	private ServerSocket ss;
	private boolean flag;
	public static ArrayList<ObjectOutputStream> usersList = new ArrayList();
	public static ArrayList<ObjectOutputStream> chatusersList = new ArrayList();
	public static ArrayList<String> userNicks = new ArrayList();
	private ServerGui gui;

	public ServerReceiver(ServerGui gui) {
		flag = true;
		this.gui = gui;

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

			Socket sk2 = ss.accept();
			ObjectOutputStream oos2 = new ObjectOutputStream(sk2.getOutputStream());
			chatusersList.add(oos2);
			ObjectInputStream ois2 = new ObjectInputStream(sk2.getInputStream());

			usersList.add(oos);
			gui.setUserCount(usersList.size());
			gui.setMessage(sk.getInetAddress() + "/ 접속하였습니다.");
			ServerThread sth = new ServerThread(sk, oos, ois, usersList, userNicks, gui, this);
			Thread th = new Thread(sth);
			th.start();

			Thread cth = new Thread(new ServerChat(chatusersList, userNicks, sk2, ois2, oos2));
			cth.start();

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
	}

}
