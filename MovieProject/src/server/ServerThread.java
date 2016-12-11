package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;
import datas.User;

public class ServerThread implements Runnable {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public ArrayList<ObjectOutputStream> usersList;
	public static ArrayList<String> userNicks;
	public static ArrayList<User> theUsers;
	private boolean flag = true;
	private ServerDBwork mg;
	private ServerGui gui;
	private String userID = "미접속";
	private ServerReceiver sr;
	private User me;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ServerThread(Socket sk, ObjectOutputStream oos, ObjectInputStream ois,
			ArrayList<ObjectOutputStream> usersList, ArrayList<String> userNicks, ArrayList<User> theUsers,
			ServerGui gui, ServerReceiver sr) {
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;
		this.usersList = usersList;
		this.userNicks = userNicks;
		this.theUsers = theUsers;
		this.gui = gui;
		this.sr = sr;
		mg = new ServerDBwork(gui, this);
	}

	public void run() {

		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				int protocol = (Integer) obj[0];
				process(protocol, obj);

			} catch (Exception e) {
				flag = false;
				logOut();
			}

		}

	}

	public void process(int protocol, Object[] obj) {
		try {
			switch (protocol) {
			case Data.REGISTER:
				int regRe = mg.register((User) obj[1]);
				Object[] regResult = new Object[] { Data.REGISTER, regRe };
				oos.writeUnshared(regResult);
				break;

			case Data.LOGIN:
				me = mg.login((String) obj[1], (String) obj[2]);
				if (me != null) {
					theUsers.add(me);
					gui.setUserList(userNicks);
				}
				Object[] loginResult = new Object[] { Data.LOGIN, me, gui.getMblist() };
				oos.writeUnshared(loginResult);
				break;

			case Data.LOGOUT:
				break;

			case Data.CHATLOGIN:
				Object[] CLogresult = new Object[] { Data.CHATLOGIN, userNicks };
				System.out.println("st : " + "유저 로그인 감지 --");
				for (ObjectOutputStream oio : usersList) {
					oio.writeUnshared(CLogresult);
				}

				break;

			case Data.CHATMESSAGE:
				String message = (String) obj[1];
				Object[] MeResult = new Object[] { Data.CHATMESSAGE, message };
				for (ObjectOutputStream oio : usersList) {
					oio.writeUnshared(MeResult);
				}
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void logOut() {
		System.out.println("로그아웃~");
		usersList.remove(oos);

		gui.setMessage(userID + " 회원이 프로그램을 종료하였습니다.");
		theUsers.remove(me);
		userNicks.remove(userID);
		gui.setUserCount(usersList.size());

		gui.setUserList(userNicks);

		Object[] logRes = new Object[] { Data.CHATLOGOUT, userNicks, (userID + "회원이 퇴장하였습니다.") };
		try {
			for (ObjectOutputStream ooos : usersList) {
				ooos.writeUnshared(logRes);
			}
		} catch (Exception e) {

		}

	}

}
