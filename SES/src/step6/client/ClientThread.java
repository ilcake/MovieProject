package step6.client;

import java.io.ObjectInputStream;
import java.util.ArrayList;

import step6.vo.Human;

public class ClientThread extends Thread {
	private SESUI gui;
	private ObjectInputStream ois;
	private static Object[] obj;

	public ClientThread(SESUI gui, ObjectInputStream ois, Object[] obj) {
		this.gui = gui;
		this.ois = ois;

	}

	public static Object[] getObj() {
		return obj;
	}

	public static void setObj(Object[] obj) {
		ClientThread.obj = obj;
	}

	public void run() {
		while (true) {
			try {
				System.out.println("ct >" + "wait for server");
				obj = (Object[]) ois.readObject();
				synchronized (this) {
					System.out.println("ct >" + "i got orders");
					setObj(obj);
					refresh((ArrayList<Human>) obj[0]);
					System.out.println("ct >" + "OK start");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}

		}
	}

	public void refresh(ArrayList<Human> ll) {
		gui.li_humanList.setListData(ll.toArray());
	}

}
