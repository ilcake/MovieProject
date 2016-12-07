package client.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;

public class ChatManager {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private String id;
	private ArrayList<String> userList;
	private ChatThread cm;

	public ChatManager(Socket sk, ObjectOutputStream oos, ObjectInputStream ois, ArrayList<String> userList) {
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;
		ChatThread cm = new ChatThread(sk, ois, oos, this);
		Thread th = new Thread(cm);
		th.start();
	}

	private void setId(String userId) {
		this.id = userId;
	}

	private void setUserList(ArrayList<String> userList) {
		this.userList = userList;
	}

	private void login(String id) {
		Object[] obj = { Data.CHATLOGIN, id };
		request(obj);
	}

	private void sendMessage(String message) {
		Object[] obj = { Data.CHATMESSAGE, message };
		request(obj);
	}

	private void request(Object[] obj) {
		try {
			oos.writeUnshared(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
