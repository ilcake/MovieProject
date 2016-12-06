package guis;

import javax.swing.JFrame;
import javax.swing.UIManager;

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Box;

public class ClientGui extends JFrame {
	public static void main(String[] args) {
		new ClientGui();
	}

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
	private String whoAmI;
	private Container mainBOARD;
	private JPanel mm1_1;
	private JPanel mm1_2;
	private JPanel mm1_3;
	private JPanel mm1_4;
	private JPanel mv2_1;
	private JPanel mv2_2;

	///////////// mv1을 위한..
	private BufferedImage bfImg = null; // 섬네일 이미지
	private JLabel imgJb; // 이미지를 담기위한 레이블
	private JLabel titleLb; // 제목레이블
	private JLabel setGenreLb; // 장르레이블
	private JLabel setShowTmLb; // 상영시간레이블
	private JLabel setActorLb; // 배우레이블
	private JScrollPane scrollPane; // 줄거리스크롤페인
	private JTextArea textArea; // 줄거리TA
	private String story; // 줄거리 담을 스트링

	private ImageIcon imgIcon;
	private Image img;
	private Image img2;
	private ImageIcon imgIcon2;
	private JLabel genreLb;
	private JLabel showTmLb;
	private JLabel actorLb;

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
		mv1.setLayout(null);

		/////////////////////////////////////////
		try {
			bfImg = ImageIO.read(new File("C:/Users/05-12/workspaceJHP/MovieDemo/img/harrypotter.jpg")); // 여기는섬네일
		} catch (IOException e) {
			e.printStackTrace();
		}

		imgIcon = new ImageIcon(bfImg);

		img = imgIcon.getImage(); // ImageIcon을 Image로 변환.
		img2 = img.getScaledInstance(140, 190, java.awt.Image.SCALE_SMOOTH);
		imgIcon2 = new ImageIcon(img2); // Image로 ImageIcon 생성

		imgJb = new JLabel("", imgIcon2, JLabel.CENTER);
		imgJb.setLocation(22, 28);
		imgJb.setSize(new Dimension(171, 213));

		mv1.add(imgJb);

		titleLb = new JLabel();
		titleLb.setHorizontalAlignment(SwingConstants.CENTER);
		titleLb.setFont(new Font("HY강M", Font.PLAIN, 25));
		titleLb.setBounds(214, 42, 191, 52);
		titleLb.setText("신비한 동물사전"); // 여기에 제목 연결
		mv1.add(titleLb);

		genreLb = new JLabel("장르");
		genreLb.setBounds(205, 115, 57, 15);
		mv1.add(genreLb);

		showTmLb = new JLabel("상영시간");
		showTmLb.setBounds(205, 155, 57, 15);
		mv1.add(showTmLb);

		actorLb = new JLabel("주연배우");
		actorLb.setBounds(205, 199, 57, 15);
		mv1.add(actorLb);

		setGenreLb = new JLabel();
		setGenreLb.setBounds(274, 115, 57, 15);
		setGenreLb.setText("판타지"); // 여기는 장르 연결
		mv1.add(setGenreLb);

		setShowTmLb = new JLabel();
		setShowTmLb.setBounds(274, 155, 57, 15);
		setShowTmLb.setText("132분"); // 여기에 상영시간 연결
		mv1.add(setShowTmLb);

		setActorLb = new JLabel();
		setActorLb.setBounds(274, 199, 108, 15);
		setActorLb.setText("에디 레드메인"); // 여기에주연배우 연결
		mv1.add(setActorLb);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 251, 351, 118);
		mv1.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
		textArea.setEditable(false);

		// 줄거리를 여기에
		story = "\u2018해리 포터\u2019 마법의 시작!\r\n신비한 동물들 탈출, 뉴욕 최대의 위기! 새로운 마법의 시대가 열린다!\r\n\r\n1926년 뉴욕, \u2018검은 존재\u2019가 거리를 쑥대밭으로 만들고 미국의 마법의회 MACUSA의 대통령과 어둠의 마법사를 체포하는 오러의 수장 그레이브스가 이를 추적하는 혼돈 속에 영국의 마법사 뉴트 스캐맨더가 이 곳을 찾는다. 그의 목적은 세계 곳곳에 숨어있는 신비한 동물들을 찾기 위한 것. 여행을 하면서 다양한 크기의 신비한 동물을 구조해 안에 마법의 공간이 있는 가방에 넣어 다니며 보살핀다. 하지만 은행을 지나던 중 금은보화를 좋아하는 신비한 동물인 니플러가 가방 안에서 탈출을 하고 이 일로 전직 오러였던 티나와 노마지 제이콥과 엮이게 된다. 이 사고로 뉴트와 제이콥의 가방이 바뀌면서 신비한 동물들이 대거 탈출을 하고 그들은 동물들을 찾기 위해 뉴욕 곳곳을 누빈다. 한편, \u2018검은 존재\u2019의 횡포는 더욱 거세져 결국 인간 사회와 마법 사회를 발칵 뒤집는 사건이 발생하고, 이 모든 것이 뉴트의 소행이라는 오해를 받게 되는데\u2026";

		String[] sArr = story.split("\r\n");
		for (int i = 0; i < sArr.length; i++) {
			textArea.append(sArr[i] + "\n");
		}
		////////////////////////////////////////

		JPanel mv2 = new JPanel();
		pnMovie.add(mv2);
		mv2.setLayout(new CardLayout(0, 0));

		mv2_1 = new JPanel();
		mv2.add(mv2_1, "name_29075740000102");

		mv2_2 = new JPanel();
		mv2.add(mv2_2, "name_29078634968268");

		setVisible(true);

	}
}
