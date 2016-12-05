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
import javax.swing.Box;
import net.miginfocom.swing.MigLayout;

public class ClientGui extends JFrame {
	private static final int fwidth = 900;
	private static final int fheight = 540;
	private JPanel pnLogin, pnMain, pnMovie;
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

	public ClientGui() {
		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
			System.out.println("dd");
		}
		setTitle("MovieLovers");
		setSize(fwidth, fheight);
		mainCard = new CardLayout(0, 0);
		getContentPane().setLayout(mainCard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnLogin = new JPanel();
		getContentPane().add(pnLogin, "name_15049258855123");
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
		getContentPane().add(pnMain, "name_15181910601831");
		pnMain.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel mn1 = new JPanel();
		pnMain.add(mn1);

		JPanel mn2 = new JPanel();
		pnMain.add(mn2);

		pnMovie = new JPanel();
		getContentPane().add(pnMovie, "name_15183354017821");
		pnMovie.setLayout(new GridLayout(1, 2, 0, 0));

		JPanel mv1 = new JPanel();
		pnMovie.add(mv1);

		JPanel mv2 = new JPanel();
		pnMovie.add(mv2);

		setVisible(true);

		addListeners();
		// mg = new ClientManager(this);

	}

	public void addListeners() {
		MouseAdapter ma = new mcl();
		goRegi.addMouseListener(ma);
		bt_rgReg.addMouseListener(ma);
		bt_rgCancel.addMouseListener(ma);
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
		case Data.RG_IDDUB:
			break;
		case Data.RG_MAILDUB:
			break;
		case Data.RG_PHDUB:
			break;
		case Data.RG_SUCCESS:
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
			lg2Card.show(lg2, "lg2_1");
			break;
		}
	}
}
