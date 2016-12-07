package guiss;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import datas.Data;
import datas.User;
import net.infonode.util.ImageUtils;
import vos.MovieBoxInfo;
import vos.MovieSearchInfo;

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.beans.AppletInitializer;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Component;

import javax.swing.ImageIcon;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import client.db.SearchBy;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class ClientGui extends JFrame {
	private static final int fwidth = 900;
	private static final int fheight = 540;
	private JPanel pnBOARD, pnLogin, pnMain, pnMovie;
	private JPanel lg2, lg1, lg2_1, lg2_2;
	private JPanel mm1_1, mm1_2, mm1_3, mm1_4, mv2_1, mv2_2, mv1, panel_6, mv2_panel, mn1;
	private JPanel pn_search, pn_info;
	private JTextField tf_id;
	private JPasswordField tf_pw;
	private JLabel lblNewLabel_3;
	private JButton bt_Login;
	private JLabel goRegi;
	private CardLayout mainCard, lg2Card;
	private JLabel lblNewLabel_2;
	private JTextField rg_id;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lb_mvIcon;
	private JLabel lb_mvTitle;
	private JLabel lb_miG;
	private JTextField rg_ph;
	private JTextField rg_ma;
	private JButton bt_rgCancel;
	private JButton bt_rgReg;
	private JPasswordField rg_pw;
	private ClientManager mg;
	private String whoAmI;
	private Container mainBOARD;
	private JTable mBoxTable;
	private ArrayList<MovieBoxInfo> dblist;
	private ArrayList<MovieSearchInfo> scList;
	private JScrollPane tableView;
	private JTextField mn_search;
	private JButton bt_search;
	private JComboBox cb;
	private JTable tb_search;
	private JScrollPane sp_search;
	private JTextArea ta_mvStory;
	private JScrollPane mv1_jsp;
	private JButton bt_mv2Return;
	private JButton bt_mv2Write;
	private JButton bt_mv2Like;
	private SearchBy sb;
	private CardLayout mn1Card;
	private JButton bt_mm1_2Return;
	private JLabel lb_miD;
	private JPanel pn_label;
	private JLabel lb_miT;
	private JLabel lb_miA;
	private JLabel lb_mvDirector;
	private JLabel lb_mvGen;
	private JLabel lb_mvShowT;
	private JLabel lb_mvActor;
	private JLabel lb_img2;
	private ImageIcon mainL, mainR, noImg;
	private JLabel lb_like;
	private JLabel lb_comm;

	public ClientGui() {//

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
			try { //
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel15");
				UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e1) {
				System.out.println("dd");
			}
		} //
		setIcons();

		mainBOARD = getContentPane();
		pnBOARD = new JPanel();
		setTitle("MovieLovers");
		setSize(fwidth, fheight);
		mainCard = new CardLayout(0, 0);
		mainBOARD.setLayout(mainCard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnLogin = new JPanel();
		pnLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBOARD.add(pnLogin, "pnLogin");
		pnLogin.setLayout(new GridLayout(1, 2, 0, 0));

		ImageIcon img = new ImageIcon("./img/mainL.png");
		lg1 = new JPanel();
		lg1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnLogin.add(lg1);
		lg1.setLayout(null);

		lg2 = new JPanel();
		lg2.setBorder(new LineBorder(new Color(0, 0, 0)));
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
		pnMain.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBOARD.add(pnMain, "pnMain");
		pnMain.setLayout(new GridLayout(1, 2, 0, 0));

		mn1 = new JPanel();
		mn1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnMain.add(mn1);
		mn1Card = new CardLayout(0, 0);
		mn1.setLayout(mn1Card);

		mm1_1 = new JPanel();
		mn1.add(mm1_1, "mm1_1");
		mm1_1.setLayout(null);

		tableView = new JScrollPane();
		tableView.setBounds(44, 130, 360, 214);
		mm1_1.add(tableView);

		mBoxTable = new JTable();
		tableView.setViewportView(mBoxTable);

		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.setBounds(44, 374, 360, 78);
		mm1_1.add(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel lb_img1 = new JLabel("");
		lb_img1.setIcon(new ImageIcon("./img/boLogo.png"));
		panel.add(lb_img1);
		lb_img1.setHorizontalAlignment(SwingConstants.CENTER);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_1.setBounds(44, 27, 360, 78);
		mm1_1.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		lb_img2 = new JLabel("");
		lb_img2.setIcon(new ImageIcon("./img/boLogo.png"));
		lb_img2.setHorizontalAlignment(SwingConstants.CENTER);
		panel_1.add(lb_img2);

		mm1_2 = new JPanel();
		mn1.add(mm1_2, "mm1_2");
		mm1_2.setLayout(null);

		pn_search = new JPanel();
		pn_search.setBackground(new Color(255, 255, 255));
		pn_search.setBounds(30, 60, 381, 228);
		mm1_2.add(pn_search);
		pn_search.setLayout(new GridLayout(1, 0, 0, 0));

		sp_search = new JScrollPane();
		sp_search.setBackground(new Color(255, 255, 255));
		pn_search.add(sp_search);

		tb_search = new JTable();
		sp_search.setViewportView(tb_search);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(30, 299, 381, 77);
		mm1_2.add(panel_5);

		bt_mm1_2Return = new JButton("뒤로가기");
		bt_mm1_2Return.setBounds(304, 427, 107, 27);
		mm1_2.add(bt_mm1_2Return);

		mm1_3 = new JPanel();
		mn1.add(mm1_3, "mm1_3");

		mm1_4 = new JPanel();
		mn1.add(mm1_4, "mm1_4");

		JPanel mn2 = new JPanel();
		mn2.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnMain.add(mn2);
		mn2.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_2.setBounds(72, 31, 312, 78);
		mn2.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 1, 0, 0));

		JLabel label_3 = new JLabel("");
		label_3.setIcon(new ImageIcon("./img/banner.png"));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		panel_2.add(label_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBackground(UIManager.getColor("Button.background"));
		panel_3.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_3.setBounds(72, 353, 312, 100);
		mn2.add(panel_3);
		panel_3.setLayout(null);

		lb_comm = new JLabel("");
		lb_comm.setBounds(47, 12, 75, 75);
		lb_comm.setIcon(new ImageIcon("./img/lg_comm.png"));

		lb_like = new JLabel("");
		lb_like.setBounds(195, 12, 75, 75);
		lb_like.setIcon(new ImageIcon("./img/lg_like.png"));

		panel_3.add(lb_like);
		panel_3.add(lb_comm);

		panel_6 = new JPanel();
		panel_6.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		panel_6.setBackground(new Color(192, 192, 192));
		panel_6.setBounds(72, 137, 312, 182);
		mn2.add(panel_6);
		panel_6.setLayout(null);

		cb = new JComboBox();
		cb.setBounds(14, 44, 99, 26);
		panel_6.add(cb);
		cb.addItem("=====");
		cb.addItem("제목검색");
		cb.addItem("감독검색");

		mn_search = new JTextField();
		mn_search.setBounds(127, 45, 171, 24);
		panel_6.add(mn_search);
		mn_search.setColumns(10);

		bt_search = new JButton("SEARCH");
		bt_search.setFont(new Font("굴림", Font.BOLD, 15));
		bt_search.setBounds(191, 122, 107, 27);
		panel_6.add(bt_search);

		pnMovie = new JPanel();
		pnMovie.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBOARD.add(pnMovie, "pnMovie");
		pnMovie.setLayout(new GridLayout(1, 2, 0, 0));

		mv1 = new JPanel();
		mv1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnMovie.add(mv1);
		mv1.setLayout(null);

		lb_mvIcon = new JLabel("", new ImageIcon("C:\\Users\\kita\\Downloads\\bmo.gif"), SwingConstants.CENTER);
		lb_mvIcon.setSize(new Dimension(170, 215));
		lb_mvIcon.setBounds(31, 49, 171, 222);
		mv1.add(lb_mvIcon);

		lb_mvTitle = new JLabel();
		lb_mvTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvTitle.setFont(new Font("Dialog", Font.PLAIN, 21));
		lb_mvTitle.setBounds(236, 49, 181, 80);
		mv1.add(lb_mvTitle);

		mv1_jsp = new JScrollPane();
		mv1_jsp.setBounds(31, 307, 386, 131);
		mv1.add(mv1_jsp);

		ta_mvStory = new JTextArea();
		mv1_jsp.setViewportView(ta_mvStory);
		ta_mvStory.setLineWrap(true);
		ta_mvStory.setEditable(false);

		pn_label = new JPanel();
		pn_label.setBounds(236, 151, 64, 120);
		mv1.add(pn_label);
		pn_label.setLayout(new GridLayout(0, 1, 0, 0));

		lb_miD = new JLabel("감독");
		pn_label.add(lb_miD);

		lb_miG = new JLabel("장르");
		pn_label.add(lb_miG);

		lb_miT = new JLabel("상영시간");
		pn_label.add(lb_miT);

		lb_miA = new JLabel("주연배우");
		pn_label.add(lb_miA);

		pn_info = new JPanel();
		pn_info.setBounds(304, 151, 113, 120);
		mv1.add(pn_info);
		pn_info.setLayout(new GridLayout(0, 1, 0, 0));

		lb_mvDirector = new JLabel("감독");
		lb_mvDirector.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvDirector);

		lb_mvGen = new JLabel("장르");
		lb_mvGen.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvGen);

		lb_mvShowT = new JLabel("상영시간");
		lb_mvShowT.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvShowT);

		lb_mvActor = new JLabel("주연배우");
		lb_mvActor.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvActor);

		JPanel mv2 = new JPanel();
		pnMovie.add(mv2);
		mv2.setBorder(new LineBorder(new Color(0, 0, 0)));
		mv2.setLayout(new CardLayout(0, 0));

		mv2_1 = new JPanel();
		mv2.add(mv2_1, "mv2_1");
		mv2_1.setLayout(null);

		mv2_panel = new JPanel();
		mv2_panel.setBackground(new Color(128, 128, 128));
		mv2_panel.setBounds(34, 43, 380, 362);
		mv2_1.add(mv2_panel);

		bt_mv2Return = new JButton("뒤로가기");
		bt_mv2Return.setBounds(323, 417, 91, 27);
		mv2_1.add(bt_mv2Return);

		bt_mv2Write = new JButton("글쓰기");
		bt_mv2Write.setBounds(34, 417, 91, 27);
		mv2_1.add(bt_mv2Write);

		bt_mv2Like = new JButton("평점주기");
		bt_mv2Like.setBounds(139, 417, 91, 27);
		mv2_1.add(bt_mv2Like);

		mv2_2 = new JPanel();
		mv2.add(mv2_2, "v2_2");

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int locWidth = (int) ((d.getWidth() - fwidth) / 2);
		int locHeight = (int) ((d.getHeight() - fheight) / 2);
		setLocation(locWidth, locHeight);

		setVisible(true);

		setIcons();
		drwaImages(lg1, mainL);
		drwaImages(lg2, mainR);

		addListeners();
		mg = new ClientManager(this);
		sb = new SearchBy();

	}

	public void addListeners() {
		MouseAdapter ma = new mcl();
		goRegi.addMouseListener(ma);
		bt_rgReg.addMouseListener(ma);
		bt_rgCancel.addMouseListener(ma);
		bt_Login.addMouseListener(ma);
		bt_search.addMouseListener(ma);
		bt_mv2Return.addMouseListener(ma);
		bt_mm1_2Return.addMouseListener(ma);
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
			} else if (e.getSource() == mBoxTable) {
				////////////////////////////////////////////
			} else if (e.getSource() == tb_search) {
				int who = tb_search.getSelectedRow();
				setMovieInfoPage(scList.get(who).getMvCode());
				mainCard.show(mainBOARD, "pnMovie");
			} else if (e.getSource() == bt_mv2Return) {
				mainCard.show(mainBOARD, "pnMain");
			} else if (e.getSource() == bt_search) {
				searchByAction();
			} else if (e.getSource() == bt_mm1_2Return) {
				mn1Card.show(mn1, "mm1_1");
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
			JOptionPane.showMessageDialog(null, "중복된 아이디가 이미 존재합니다.", "회원가입 에러", JOptionPane.ERROR_MESSAGE);
			rg_id.setText("");
			break;
		case Data.RG_SUCCESS:
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", "환영합니다", JOptionPane.INFORMATION_MESSAGE);
			lg2Card.show(lg2, "lg2_1");
			break;
		}
	}

	public void login() {
		String id = tf_id.getText();
		String pw = tf_pw.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해 주십시오", "로그인 에러", JOptionPane.ERROR_MESSAGE);
			tf_id.grabFocus();
			return;
		} else if (pw.equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주십시오", "로그인 에러", JOptionPane.ERROR_MESSAGE);
			tf_pw.grabFocus();
			return;
		}
		User result = mg.login(id, pw);
		if (result != null) {
			JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다!", "환영합니다", JOptionPane.INFORMATION_MESSAGE);
			mainCard.show(mainBOARD, "pnMain");
			setMovieBoxInfo();

		} else {
			JOptionPane.showMessageDialog(null, "아이디 혹은 패스워드가 일치하지않습니다.", "로그인 에러", JOptionPane.ERROR_MESSAGE);
			tf_id.grabFocus();
		}
	}

	public void setMovieBoxInfo() {
		dblist = mg.getMovieBoxInfo();
		Vector<String> column = new Vector();
		column.addElement("순위");
		column.addElement("제목");
		column.addElement("감독");
		column.addElement("개봉일");

		Vector<String> elements = null;
		Vector<Vector> rowData1 = new Vector();
		for (int i = 0; i < dblist.size(); i++) {
			MovieBoxInfo h = dblist.get(i);
			elements = new Vector();
			elements.addElement((i + 1) + "");
			elements.addElement(h.getMovieNm());
			elements.addElement(h.getDirector());
			elements.addElement(h.getOpenDt());
			rowData1.addElement(elements);
		}

		mBoxTable = new JTable(rowData1, column);
		mBoxTable.getTableHeader().setReorderingAllowed(false);
		// mBoxTable.setPreferredSize(new Dimension(tableView.getSize()));
		mBoxTable.getColumnModel().getColumn(0).setPreferredWidth(4);
		mBoxTable.getColumnModel().getColumn(0).setResizable(false);
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		mBoxTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);

		mBoxTable.setDragEnabled(false);
		tableView.setViewportView(mBoxTable);
		mBoxTable.addMouseListener(new mcl());
	}

	public void searchByAction() {
		int type = cb.getSelectedIndex();
		String what = mn_search.getText();
		if (what.equals("")) {
			JOptionPane.showMessageDialog(null, "검색값을 입력해 주십시오.", "검색 에러", JOptionPane.ERROR_MESSAGE);
			mn_search.grabFocus();
			return;
		}
		if (type == 0) {
			JOptionPane.showMessageDialog(null, "검색 타입을 선택하여 주십시오", "검색 에러", JOptionPane.ERROR_MESSAGE);
			cb.grabFocus();
			return;
		}

		mn1Card.show(mn1, "mm1_2");

		try {
			sb.Search(type, what);
			scList = sb.getSearchList();
			setSearchTable();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setSearchTable() {
		if (scList.size() == 0) {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "검색 실패", JOptionPane.ERROR_MESSAGE);
			mn_search.grabFocus();
			return;
		}

		Vector<String> column = new Vector();
		column.addElement("제목");
		column.addElement("장르");
		column.addElement("감독");
		column.addElement("주연배우");

		Vector<String> elements = null;
		Vector<Vector> rowData1 = new Vector();
		for (int i = 0; i < scList.size(); i++) {
			MovieSearchInfo h = scList.get(i);
			elements = new Vector();
			elements.addElement(h.getMvTitle());
			elements.addElement(h.getMvGenre());
			elements.addElement(h.getMvDirector());
			elements.addElement(h.getMvActor());
			rowData1.addElement(elements);
		}

		tb_search = new JTable(rowData1, column);

		tb_search.getTableHeader().setReorderingAllowed(false);
		tb_search.setPreferredSize(new Dimension(pn_search.getSize()));
		tb_search.setDragEnabled(false);
		sp_search.setViewportView(tb_search);
		tb_search.addMouseListener(new mcl());

	}

	public void setMovieInfoPage(String movieCode) {
		MovieSearchInfo m = null;
		for (int i = 0; i < scList.size(); i++) {
			if (scList.get(i).getMvCode().equals(movieCode)) {
				m = scList.get(i);
			}
		}
		String iconURL = m.getMvThumb();
		String title = m.getMvTitle();
		String genre = m.getMvGenre();
		String showTm = m.getMvTm();
		String actor = m.getMvActor();
		String director = m.getMvDirector();
		String story = m.getMvStory();

		try {
			ImageIcon imgicon = new ImageIcon(ImageIO.read(new URL(iconURL)));
			Image image = imgicon.getImage();
			Image reSized = image.getScaledInstance(171, 213, Image.SCALE_SMOOTH);
			imgicon = new ImageIcon(reSized);
			lb_mvIcon.setIcon(imgicon);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (iconURL.equals("")) {
			lb_mvIcon.setIcon(noImg);
		}
		//
		// lb_mvIcon.setSize(new Dimension(170, 215));
		// lb_mvIcon.setBounds(31, 49, 171, 222);
		lb_mvTitle.setText("");
		lb_mvTitle.setText(title);
		int fontSize = 30;
		int titleLength = title.length();
		System.out.println(titleLength);
		if (titleLength > 8) {
			fontSize -= 3;
		} else if (titleLength > 10) {
			fontSize -= 5;
		} else if (titleLength > 20) {
			fontSize -= 10;
		} else if (titleLength > 30) {
			fontSize -= 20;
		}
		lb_mvTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvTitle.setFont(new Font("Dialog", Font.PLAIN, fontSize));
		lb_mvTitle.setBounds(216, 49, 191, 80);

		lb_mvDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvDirector.setText(director);

		lb_mvGen.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvGen.setText(genre);

		lb_mvShowT.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvShowT.setText(showTm);

		lb_mvActor.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvActor.setText(actor);

		ta_mvStory.setText("");
		String[] sArr = story.split("\r\n");
		for (int i = 0; i < sArr.length; i++) {
			ta_mvStory.append(sArr[i] + "\n");
		}
		ta_mvStory.setCaretPosition(0);
		//////////////////
		mv1.repaint();
		mv1.revalidate();
	}

	public void setIcons() {
		mainL = new ImageIcon("./img/mainL.png");
		mainR = new ImageIcon("./img/mainR.jpg");
		noImg = new ImageIcon("./img/noImg.jpg");
	}

	public void drwaImages(JComponent j, ImageIcon img) {
		System.out.println(j.getWidth() + "dd" + j.getHeight());
		j.getGraphics().drawImage(img.getImage(), j.getWidth(), j.getHeight(), null);
	}

}
