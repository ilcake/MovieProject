package step6.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class SESServerMain {
	static ArrayList<ObjectOutputStream> users = new ArrayList<>();
	private ServerSocket ss;
	private Socket sk;
	private boolean flag;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SESServerMain();

	}

	public SESServerMain() {
		flag = true;
		try {
			ss = new ServerSocket(21156);
			while (flag) {
				System.out.println("waiting....");
				sk = ss.accept();
		
				ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());

				ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());
				users.add(oos);
				
				System.out.println(sk.getInetAddress() + " is Connected!!");
				SESServerThread th = new SESServerThread(ois,oos, users);
				Thread tt = new Thread(th);
				tt.start();
			}

		} catch (Exception e) {
		}

	}

}
