package server.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;
import server.ServerThread;

public class ServerChat implements Runnable {

	public ArrayList<ObjectOutputStream> chatusersList;
	public ArrayList<String> userNicks;
	private Socket sk;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private boolean flag;
	private ServerThread sth;

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
					obj[1] = userNicks;
					for (ObjectOutputStream dd : chatusersList) {
						dd.writeObject(obj);
					}

					break;

				case Data.CHATLOGOUT:
					obj[0] = Data.CHATLOGIN;
					obj[1] = userNicks;
					for (ObjectOutputStream dd : chatusersList) {
						dd.writeObject(obj);
					}
					break;

				case Data.CHATMESSAGE:
					System.out.println("2");
					for (ObjectOutputStream dd : chatusersList) {
						dd.writeObject(obj);
					}
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
