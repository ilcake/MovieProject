package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import client.chat.ChatManager;
import datas.Data;
import datas.User;
import vos.MovieBoxInfo;

public class ClientManager {
	private Socket sk, sk2;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private ObjectOutputStream oos2;
	private ObjectInputStream ois2;
	private ClientGui gui;
	private Object[] obj;
	private ChatManager cm;

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
			oos.writeObject(what);
			Object result = ois.readObject();
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
