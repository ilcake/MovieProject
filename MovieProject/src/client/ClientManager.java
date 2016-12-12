package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.chat.ChatGUI;
import client.chat.ChatThread;
import datas.Data;
import datas.User;
import vos.MovieBoxInfo;

public class ClientManager {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ClientGui gui;
	private ChatGUI cgui;
	private ClientThread cht;
	private String userId;
	private User me;

	public ChatGUI getCgui() {
		return cgui;
	}

	public void setCgui(ChatGUI cgui) {
		this.cgui = cgui;
		cht.setCgui(cgui);
	}

	public User getMe() {
		return me;
	}

	public void setMe(User me) {
		this.me = me;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public ClientManager(ClientGui gui) {
		this.gui = gui;
		connection();

	}

	public void register(User us) {
		Object[] what = new Object[] { Data.REGISTER, us };
		whatTodo(what);
	}

	public void login(String id, String pw) {
		Object[] what = new Object[] { Data.LOGIN, id, pw };
		whatTodo(what);
	}

	public void logOut() {
		Object[] what = new Object[] { Data.LOGOUT };
		whatTodo(what);
	}

	// public void getMovieBoxInfo() {
	// Object[] what = new Object[] { Data.GETMOVIEBOXINFO };
	// whatTodo(what);
	// }

	public void connection() {
		try {
			sk = new Socket("localhost", 17771);
			oos = new ObjectOutputStream(sk.getOutputStream());
			ois = new ObjectInputStream(sk.getInputStream());

			cht = new ClientThread(ois, gui, this, cgui);
			Thread th = new Thread(cht);
			th.start();

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void whatTodo(Object[] what) {
		try {
			synchronized (this) {
				System.out.println("cm : " + what[0]);
				oos.writeObject(what);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void startChat() {
		// cgui = new ChatGUI(this, gui);
		Object[] what = new Object[] { Data.CHATLOGIN };
		whatTodo(what);
	}

	public void sendMessage(String message) {
		Object[] what = new Object[] { Data.CHATMESSAGE, message };
		whatTodo(what);

	}

	public void setIcon(String iconUrl) {
		Object[] what = new Object[] { Data.ICONCHANGE, me, iconUrl };
		whatTodo(what);

	}

	public void getComments(String movieCD) {
		Object[] what = new Object[] { Data.GETCOMMENT, movieCD };
		whatTodo(what);

	}

}
