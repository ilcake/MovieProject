package client.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;

public class ChatThread implements Runnable {
	private Socket sk;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<String> userNicks;
	private boolean flag;
	private ChatManager cm;

	public ChatThread(Socket sk, ObjectInputStream ois, ObjectOutputStream oos, ChatManager cm) {
		this.sk = sk;
		this.ois = ois;
		this.oos = oos;
		this.cm = cm;
		userNicks = new ArrayList<String>();
		flag = true;
	}

	@Override
	public void run() {
		int protocols = -1;

		while (flag) {

			try {
				Object[] obj = (Object[]) ois.readObject();
				protocols = (int) obj[0];

				switch (protocols) {
				case Data.CHATLOGIN:
					break;
				case Data.CHATLOGOUT:
					break;
				case Data.CHATMESSAGE:
					break;
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				flag = false;
				e.printStackTrace();
			}

		}

	}

}
