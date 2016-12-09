package client.chat;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import client.ClientGui;
import client.ClientManager;
import datas.Data;

import java.awt.Toolkit;

public class ChatGUI extends JFrame implements ActionListener { //

	private JScrollPane scrollPane; // 채팅스크롤
	private JTextArea textArea; // 채팅
	private JTextField textField; // 채팅입력
	private JButton btnNewButton; // 채팅버튼
	private JScrollPane scrollPane_1; // 유저리스트스크롤
	private JList list; // 유저리스트
	private JPanel panel; // 배너
	private String id;
	private ClientGui mgui;
	private ClientManager mg;
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ChatGUI(String id, ClientGui mgui, ClientManager mg, Socket sk, ObjectOutputStream oos,
			ObjectInputStream ois) {
		this.id = id;
		this.mgui = mgui;
		this.mg = mg;
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;

		Thread thd = new Thread(new ChatThread(sk, oos, ois, this));
		thd.start();

		addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				super.windowClosing(e);
				windowClose();
			}

		});

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/chatIcon2.png"));

		setTitle("Movie Lovers Chat");

		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 175, 383);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setLineWrap(true);

		textField = new JTextField();
		textField.setBounds(12, 471, 197, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		textField.addActionListener(this);

		btnNewButton = new JButton("GO");
		btnNewButton.setBounds(221, 470, 51, 23);
		getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);

		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(199, 78, 73, 383);
		getContentPane().add(scrollPane_1);

		list = new JList();
		scrollPane_1.setViewportView(list);

		////////////////////////////////////////////////////////////////////////////////////////
		final ImageIcon icon1 = new ImageIcon("img/chatBG.png");
		JPanel chatPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon1.getImage(), 0, 0, null); // 이미지 원래사이즈로 넣기
				Dimension d = getSize();
				g.drawImage(icon1.getImage(), 0, 0, d.width, d.height, null); // 컴포넌트사이즈에맞게
				////////////////////////////////////////////////////////////////////////////////////////
			}
		};
		chatPanel.setBounds(new Rectangle(0, 0, 300, 540));
		this.add(chatPanel);

		setSize(300, 540);
		setResizable(false);
		setVisible(true);
		setThisLocation();

		login();
	}

	// 이벤트처리
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("GO") || e.getSource() == textField) { // 버튼눌렀을때,엔터
			String ms = textField.getText();
			// ms쓰레드에 보내주기
			sendMessage(ms);
			// 보내고 텍스트필드 지워줌
			textField.setText("");
		}
	}

	public void talk(String message) { // 스트링이 들어오면 textArea로 append
		textArea.append(message + "\n");
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}

	public void setUserList(ArrayList<String> userList) {
		list.setListData(userList.toArray());
	}

	public void windowOn() {
		this.setVisible(true);
	}

	public void windowClose() {
		this.setVisible(false);
	}

	public void setThisLocation() {
		Point d = mgui.getLocation();
		setLocation(d.x + 900, d.y);
	}

	public void sendMessage(String message) {
		try {
			oos.writeObject(new Object[] { Data.CHATMESSAGE, id + " : " + message });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void login() {
		try {
			oos.writeObject(new Object[] { Data.CHATLOGIN, id });
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}