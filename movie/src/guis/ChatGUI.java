package guis;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.Toolkit;

public class ChatGUI extends JFrame implements ActionListener { //

	private JScrollPane scrollPane; // 채팅스크롤
	private JTextArea textArea; // 채팅
	private JTextField textField; // 채팅입력
	private JButton btnNewButton; // 채팅버튼
	private JScrollPane scrollPane_1; // 유저리스트스크롤
	private JList list; // 유저리스트
	private ImageIcon chatBack;

	public ChatGUI() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/chatIcon2.png"));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Movie Lovers Chat");

		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 78, 175, 383);
		getContentPane().add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
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
		chatBack = new ImageIcon("img/chatBG.png");
		JPanel chatPanel = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(chatBack.getImage(), 0, 0, null); // 이미지 원래사이즈로 넣기
				Dimension d = getSize();
				g.drawImage(chatBack.getImage(), 0, 0, d.width, d.height, null); // 컴포넌트사이즈에맞게
		////////////////////////////////////////////////////////////////////////////////////////
			}
		};
		chatPanel.setBounds(new Rectangle(0, 0, 300, 540));
		this.add(chatPanel);

		setSize(300, 540);
		setVisible(true);
	}

	// 이벤트처리
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("GO") || e.getSource() == textField) { // 버튼눌렀을때,엔터
			String ms = textField.getText();
			// ms쓰레드에 보내주기
			// 보내고 텍스트필드 지워줌
			textField.setText("");
		}
	}

	public void talk(String message) { // 스트링이 들어오면 textArea로 append
		textArea.append(message);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}

	public void setUserList(ArrayList<String> userList) {
		list.setListData(userList.toArray());
	}

/*	public static void main(String[] args) {
		new ChatGUI();
	}*/
}