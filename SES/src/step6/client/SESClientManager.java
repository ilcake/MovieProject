package step6.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import step6.manager.SEManager;
import step6.vo.Human;
import step6.vo.Staff;

public class SESClientManager extends Thread implements SEManager {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private SESUI gui;
	private ClientThread th;
	public static Object[] workResult = null;
	private Thread myth;

	public SESClientManager(SESUI gui) {
		this.gui = gui;
		myth = new Thread(this);
		connection();
	}

	@Override
	public boolean insertHuman(Human human) {
		Object[] obj = { "INSERT", human };
		boolean result = (boolean) sendRequest(obj);
		return result;
	}

	@Override
	public Human findHuman(String jumin) {
		Object[] obj = { "SEARCH", jumin };
		Human result = (Human) sendRequest(obj);
		System.out.println(result);
		return result;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		Object[] obj = { "DELETE", jumin };
		boolean result = (boolean) sendRequest(obj);
		return result;
	}

	@Override
	public void updateHuman(Human newData) {
		Object[] obj = { "UPDATE", newData };
		sendRequest(obj);
	}

	@Override
	public ArrayList<Human> getHumanList() {
		Object[] obj = { "GETALL" };
		ArrayList<Human> result = (ArrayList<Human>) sendRequest(obj);
		return result;
	}

	public Object sendRequest(Object[] obj) {
		Object result = null;
		try {
			System.out.println("cm:>" + "go!");
			oos.writeObject(obj);
			Thread.sleep(200);

			System.out.println("cm:>" + "im waiting....");
			result = workResult[1];
			System.out.println("cm:>" + "got it " + result);
		} catch (Exception e) {
		}
		return result;
	}

	public void connection() {
		try {
			sk = new Socket("localhost", 21156);// 10.10.5.129
			oos = new ObjectOutputStream(sk.getOutputStream());
			ois = new ObjectInputStream(sk.getInputStream());
			th = new ClientThread(gui, ois, workResult);
			th.start();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {

	}

}
