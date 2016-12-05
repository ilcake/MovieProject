package client;

import javax.swing.JFrame;
import javax.swing.UIManager;

import datas.Data;
import datas.User;

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Container;

import javax.swing.Box;
import net.miginfocom.swing.MigLayout;

public class ClientGui extends JFrame {
	private static final int fwidth = 900;
	private static final int fheight = 540;
	private JPanel pnBOARD, pnLogin, pnMain, pnMovie;
	private JPanel lg2, lg1;
	private JPanel lg2_1, lg2_2;
	private JTextField tf_id;
	private JPasswordField tf_pw;
	private JLabel lblNewLabel_3;
	private JButton bt_Login;
	private JLabel goRegi;
	private CardLayout mainCard, lg2Card;
	private JLabel lblNewLabel_2;
	private JTextField rg_id;
	private JLabel label;
	private JTextField rg_ph;
	private JLabel label_1;
	private JLabel label_2;
	private JTextField rg_ma;
	private JButton bt_rgCancel;
	private JButton bt_rgReg;
	private JPasswordField rg_pw;
	private ClientManager mg;
	private String whoAmI;
	private Container mainBOARD;
	private JPanel mm1_1;
	private JPanel mm1_2;
	private JPanel mm1_3;
	private JPanel mm1_4;
	private JPanel mv2_1;
	private JPanel mv2_2;

	public ClientGui() {
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
			System.out.println("dd");
		}
		mainBOARD = getContentPane();
		pnBOARD = new JPanel();
		setTitle("MovieLovers");
		setSize(fwidth, fheight);
		mainCard = new CardLayout(0, 0);
		mainBOARD.setLayout(mainCard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnLogin = new JPanel();
		mainBOARD.add(pnLogin, "pnLogin");
		pnLogin.setLayout(new GridLayout(1, 2, 0, 0));

		lg1 = new JPanel();
		pnLogin.add(lg1);
		lg1.setLayout(null);

		lblNewLabel_3 = new JLabel("");
		ImageIcon bmo = new ImageIcon("C:\\Users\\kita\\Downloads\\bmo.gif");

		lblNewLabel_3.setIcon(bmo);
		lblNewLabel_3.setBounds(60, 79, 321, 319);
		lg1.add(lblNewLabel_3);

		lg2 = new JPanel();
		pnLogin.add(lg2);
		lg2Card = new CardLayout(0, 0);
		lg2.setLayout(lg2Card);

		lg2_1 = new JPanel();
		lg2.add(lg2_1, "lg2_1");
		lg2_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setBounds(132, 136, 62, 18);
		lg2_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pw");
		lblNewLabel_1.setBounds(132, 189, 62, 18);
		lg2_1.add(lblNewLabel_1);

		tf_id = new JTextField();
		tf_id.setBounds(221, 133, 116, 24);
		lg2_1.add(tf_id);
		tf_id.setColumns(10);

		tf_pw = new JPasswordField();
		tf_pw.setBounds(221, 186, 116, 24);
		tf_pw.setEchoChar('*');
		lg2_1.add(tf_pw);

		goRegi = new JLabel("\uD68C\uC6D0\uAC00\uC785");
		goRegi.setForeground(Color.BLUE);
		goRegi.setBounds(275, 223, 62, 18);
		lg2_1.add(goRegi);

		bt_Login = new JButton("LogIn");
		bt_Login.setBounds(175, 323, 107, 27);
		lg2_1.add(bt_Login);

		lg2_2 = new JPanel();
		lg2.add(lg2_2, "lg2_2");
		lg2_2.setLayout(null);

		lblNewLabel_2 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_2.setBounds(122, 118, 62, 18);
		lg2_2.add(lblNewLabel_2);

		rg_id = new JTextField();
		rg_id.setBounds(224, 115, 116, 24);
		lg2_2.add(rg_id);
		rg_id.setColumns(10);

		rg_pw = new JPasswordField();
		rg_pw.setBounds(224, 161, 116, 24);
		rg_pw.setEchoChar('*');
		lg2_2.add(rg_pw);

		rg_ma = new JTextField();
		rg_ma.setColumns(10);
		rg_ma.setBounds(224, 211, 116, 24);
		lg2_2.add(rg_ma);

		rg_ph = new JTextField();
		rg_ph.setColumns(10);
		rg_ph.setBounds(224, 257, 116, 24);
		lg2_2.add(rg_ph);

		label = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label.setBounds(122, 164, 62, 18);
		lg2_2.add(label);

		label_1 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_1.setBounds(122, 260, 62, 18);
		lg2_2.add(label_1);

		label_2 = new JLabel("\uC774\uBA54\uC77C");
		label_2.setBounds(122, 214, 62, 18);
		lg2_2.add(label_2);

		bt_rgCancel = new JButton("CANCEL");
		bt_rgCancel.setBounds(110, 331, 107, 27);
		lg2_2.add(bt_rgCancel);

		bt_rgReg = new JButton("REGISTER");
		bt_rgReg.setBounds(243, 331, 107, 27);
		lg2_2.add(bt_rgReg);

		pnMain = new JPanel();
		mainBOARD.add(pnMain, "pnMain");
		pnMain.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel mn1 = new JPanel();
		pnMain.add(mn1);
		mn1.setLayout(new CardLayout(0, 0));
		
		mm1_1 = new JPanel();
		mn1.add(mm1_1, "name_28853304489764");
		
		mm1_2 = new JPanel();
		mn1.add(mm1_2, "name_28858938190096");
		
		mm1_3 = new JPanel();
		mn1.add(mm1_3, "name_28882569109417");
		
		mm1_4 = new JPanel();
		mn1.add(mm1_4, "name_28884992067110");

		JPanel mn2 = new JPanel();
		pnMain.add(mn2);

		pnMovie = new JPanel();
		mainBOARD.add(pnMovie, "pnMovie");
		pnMovie.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel mv1 = new JPanel();
		pnMovie.add(mv1);

		JPanel mv2 = new JPanel();
		pnMovie.add(mv2);
		mv2.setLayout(new CardLayout(0, 0));
		
		mv2_1 = new JPanel();
		mv2.add(mv2_1, "name_29075740000102");
		
		mv2_2 = new JPanel();
		mv2.add(mv2_2, "name_29078634968268");

		setVisible(true);

		addListeners();
		mg = new ClientManager(this);

	}

	public void addListeners() {
		MouseAdapter ma = new mcl();
		goRegi.addMouseListener(ma);
		bt_rgReg.addMouseListener(ma);
		bt_rgCancel.addMouseListener(ma);
		bt_Login.addMouseListener(ma);
	}

	public class acl implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

		}

	}

	public class mcl extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == goRegi) {
				lg2Card.show(lg2, "lg2_2");
			} else if (e.getSource() == bt_rgReg) {
				register();

			} else if (e.getSource() == bt_rgCancel) {
				lg2Card.show(lg2, "lg2_1");
			} else if (e.getSource() == bt_Login) {
				login();
			}
		}
	}

	public void register() {
		String id = rg_id.getText();
		String pw = rg_pw.getText();
		String mail = rg_ma.getText();
		String phN = rg_ph.getText();

		User nUser = new User(id, pw, mail, phN);
		int reaction = mg.register(nUser);
		switch (reaction) {
		case Data.FAIL:
			JOptionPane.showMessageDialog(null, "�ߺ��� ���̵� �̹� �����մϴ�.", "ȸ������ ����", JOptionPane.ERROR_MESSAGE);
			rg_id.setText("");
			break;
		case Data.RG_SUCCESS:
			JOptionPane.showMessageDialog(null, "ȸ�������� �Ϸ�Ǿ����ϴ�!", "ȯ���մϴ�", JOptionPane.INFORMATION_MESSAGE);
			lg2Card.show(lg2, "lg2_1");
			break;
		}
	}

	public void login() {
		String id = tf_id.getText();
		String pw = tf_pw.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "���̵� �Է��� �ֽʽÿ�", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			tf_id.grabFocus();
			return;
		} else if (pw.equals("")) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��� �ֽʽÿ�", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			tf_pw.grabFocus();
			return;
		}
		User result = mg.login(id, pw);
		if (result != null) {
			JOptionPane.showMessageDialog(null, "�α����� �Ϸ�Ǿ����ϴ�!", "ȯ���մϴ�", JOptionPane.INFORMATION_MESSAGE);
			mainCard.show(mainBOARD, "pnMain");

		} else {
			JOptionPane.showMessageDialog(null, "���̵� Ȥ�� �н����尡 ��ġ�����ʽ��ϴ�.", "�α��� ����", JOptionPane.ERROR_MESSAGE);
			tf_id.grabFocus();
		}

	}
}
