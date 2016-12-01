package step6.server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import step6.vo.Human;

public class SESServerThread extends Thread {
	private ArrayList<ObjectOutputStream> users;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private SESServerManager mg;

	public SESServerThread(ObjectInputStream ois, ObjectOutputStream oos, ArrayList<ObjectOutputStream> users) {
		this.users = users;
		this.oos = oos;
		this.ois = ois;
		mg = new SESServerManager();
	}

	public void run() {
		boolean flag = true;
		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				String pro = (String) obj[0];

				Object result = null;

				switch (pro) {
				case "INSERT":
					result = mg.insertHuman((Human) obj[1]);
					break;
				case "SEARCH":
					result = mg.findHuman((String) obj[1]);
					break;
				case "DELETE":
					result = mg.deleteHuman((String) obj[1]);
					break;
				case "UPDATE":
					mg.updateHuman((Human) obj[1]);
					break;
				case "GETALL":
					break;
				}

				for (ObjectOutputStream oos : users) {
					oos.writeObject(new Object[] { mg.getHumanList(), result });
				}

			} catch (Exception e) {
				users.remove(oos);
				flag = false;
			}

		}

	}

}
