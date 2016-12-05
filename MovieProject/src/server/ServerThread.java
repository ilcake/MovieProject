package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;

public class ServerThread implements Runnable {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public ArrayList<ObjectOutputStream> usersList;
	private boolean flag = true;
	private Object[] obj;

	public ServerThread(Socket sk, ObjectOutputStream oos, ObjectInputStream ois,
			ArrayList<ObjectOutputStream> usersList) {
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;
		this.usersList = usersList;
	}

	@Override
	public void run() {

		while (flag) {
			try {
				obj = (Object[]) ois.readObject();
				int protocol = (int) obj[0];
				process(protocol);

			} catch (Exception e) {
				flag = false;
			}

		}

	}

	public void process(int protocol) {
		switch (protocol) {
		case Data.REGISTER:
			
			break;
		case Data.LOGIN:

			break;

		case Data.LOGOUT:

			break;

		}

	}

}
