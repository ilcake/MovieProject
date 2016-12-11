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
	private Socket sk, sk2;
	private ObjectOutputStream oos, oos2;
	private ObjectInputStream ois, ois2;
	private ClientGui gui;
	private ChatThread ct;
	private String userId;

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

	public int register(User us) {
		Object[] what = new Object[] { Data.REGISTER, us };
		Object result = whatTodo(what);
		if (result != null) {
			int type = (Integer) result;
			return type;
		}
		return -1;
	}

	public User login(String id, String pw) {
		Object[] what = new Object[] { Data.LOGIN, id, pw };
		Object result = whatTodo(what);
		if (result != null) {
			return (User) result;
		}
		return null;
	}

	public boolean logOut() {
		Object[] what = new Object[] { Data.LOGOUT };
		Object result = whatTodo(what);
		if (result != null) {
			return (Boolean) result;
		}
		return false;
	}

	public ArrayList<MovieBoxInfo> getMovieBoxInfo() {
		Object[] what = new Object[] { Data.GETMOVIEBOXINFO };
		return (ArrayList<MovieBoxInfo>) whatTodo(what);
	}

	public void connection() {
		try {
			sk = new Socket("localhost", 17771);
			oos = new ObjectOutputStream(sk.getOutputStream());
			ois = new ObjectInputStream(sk.getInputStream());

			sk2 = new Socket("localhost", 17771);
			oos2 = new ObjectOutputStream(sk2.getOutputStream());
			ois2 = new ObjectInputStream(sk2.getInputStream());

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Object whatTodo(Object[] what) {
		try {
			synchronized (this) {
				oos.writeObject(what);
				Object result = ois.readObject();
				return result;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void startChat() {
		ChatGUI gi = new ChatGUI(userId, gui, this, sk2, oos2, ois2);
		gui.setChatGUI(gi);
	}

}
