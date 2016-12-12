package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.chat.ChatGUI;
import datas.Data;
import datas.User;
import vos.MovieBoxInfo;

public class ClientThread extends Thread {
	private ObjectInputStream ois;
	private ClientGui gui;
	private ClientManager cm;
	private ChatGUI cgui;
	private boolean flag;

	public ClientThread(ObjectInputStream ois, ClientGui gui, ClientManager cm, ChatGUI cgui) {
		this.ois = ois;
		this.gui = gui;
		this.cm = cm;
		flag = true;
	}

	public ChatGUI getCgui() {
		return cgui;
	}

	public void setCgui(ChatGUI cgui) {
		this.cgui = cgui;
	}

	public void run() {

		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				int proto = (int) obj[0];
				action(proto, obj);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	@SuppressWarnings("unchecked")
	private void action(int proto, Object[] obj) {
		switch (proto) {
		case Data.REGISTER:
			gui.registerReaction((int) obj[1]);
			break;
		case Data.LOGIN:
			User me = (User) obj[1];
			gui.loginReaction(me);
			gui.setMovieBoxInfo((ArrayList<MovieBoxInfo>) obj[2]);
			cm.setMe(me);
			gui.setMe(me);
			break;
		// case Data.GETMOVIEBOXINFO:
		// gui.setMovieBoxInfo((ArrayList<MovieBoxInfo>) obj[1]);
		// break;
		case Data.CHATLOGIN:
			ArrayList<String> usersdd = (ArrayList<String>) obj[1];
			// cgui.setUserList(usersdd);
			cgui.list.setListData(usersdd.toArray());
			break;
		case Data.CHATMESSAGE:
			cgui.talk((String) obj[1]);
			break;
		case Data.CHATLOGOUT:
			cgui.setUserList((ArrayList<String>) obj[1]);
			cgui.talk((String) obj[2]);
		}

	}

	public void getMovieBoxInfo() {

	}

	public void chatLogin() {

	}

	public void chatMessage() {

	}
}
