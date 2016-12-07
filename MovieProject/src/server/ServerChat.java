package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;

public class ServerChat implements Runnable {

	public ArrayList<ObjectOutputStream> chatusersList;
	public ArrayList<String> userNicks;
	private Socket sk;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean flag;

	public ServerChat(ArrayList<ObjectOutputStream> chatusersList, ArrayList<String> userNicks, Socket sk,
			ObjectInputStream ois, ObjectOutputStream oos) {
		this.chatusersList = chatusersList;
		this.userNicks = userNicks;
		this.sk = sk;
		this.ois = ois;
		this.oos = oos;
		flag = true;
	}

	@Override
	public void run() {
		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();

				int pro = (int) obj[0];
				switch (pro) {
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
				// TODO Auto-generated catch block
				flag = false;
			}

		}

	}

}
