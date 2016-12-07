package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ClientChat implements Runnable {
	private Socket sk;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private ArrayList<String> userNicks;
	private boolean flag;

	public ClientChat(Socket sk, ObjectInputStream ois, ObjectOutputStream oos) {
		this.sk = sk;
		this.ois = ois;
		this.oos = oos;
		userNicks = new ArrayList<String>();
		flag = true;
	}

	@Override
	public void run() {

		while (flag) {

		}

	}

}
