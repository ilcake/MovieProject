package guis;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JList;

public class ChatGUI extends JFrame implements ActionListener {

	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField textField;
	private JButton btnNewButton;
	private JScrollPane scrollPane_1;
	private JList list;
	
	public ChatGUI() {//

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Movie Lovers Chat");

		getContentPane().setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 10, 175, 451);
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
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(199, 10, 73, 451);
		getContentPane().add(scrollPane_1);
		
		list = new JList();
		scrollPane_1.setViewportView(list);
		btnNewButton.addActionListener(this);

		setSize(300, 540);
		setVisible(true);
	}

	//이벤트처리
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("GO")) {
			String ms = textField.getText();
			//ms쓰레드에 보내주기
			//보내고 텍스트필드 지워줌
			textField.setText("");
		}
	}
	
	public void talk(String message){	//스트링이 들어오면 textArea로 append
		textArea.append(message);
		scrollPane.getVerticalScrollBar().setValue(scrollPane.getVerticalScrollBar().getMaximum());
	}
}
