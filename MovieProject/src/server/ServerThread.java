package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;
import datas.User;

public class ServerThread implements Runnable {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public ArrayList<ObjectOutputStream> usersList;
	private boolean flag = true;
	private Object[] obj;
	private ServerDBwork mg;
	private ServerGui gui;
	private String userID;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ServerThread(Socket sk, ObjectOutputStream oos, ObjectInputStream ois,
			ArrayList<ObjectOutputStream> usersList, ServerGui gui) {
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;
		this.usersList = usersList;
		this.gui = gui;
		mg = new ServerDBwork(gui, this);
	}

	public void run() {

		while (flag) {
			try {
				obj = (Object[]) ois.readObject();
				int protocol = (Integer) obj[0];
				process(protocol);

			} catch (Exception e) {
				flag = false;
				usersList.remove(oos);
				gui.setMessage(userID + " 회원이 프로그램을 종료하였습니다.");
				gui.setUserCount(usersList.size());
			}

		}

	}

	public void process(int protocol) {
		Object result = null;
		switch (protocol) {
		case Data.REGISTER:
			result = mg.register((User) obj[1]);
			break;
		case Data.LOGIN:
			result = mg.login((String) obj[1], (String) obj[2]);
			break;

		case Data.LOGOUT:

			break;

		case Data.GETMOVIEBOXINFO:
			result = gui.getMblist();
			break;

		}

		try {
			oos.writeUnshared(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
