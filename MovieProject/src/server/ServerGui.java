package server;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import vos.MovieBoxInfo;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JTextArea;

public class ServerGui extends JFrame {
	private static final int fwidth = 400;
	private static final int fheight = 500;
	private JButton bt_r;
	private JLabel lb_c;
	private ServerReceiver sr;
	private JTextArea log;
	private ArrayList<MovieBoxInfo> mblist;
	private GetBoxInfo dd;

	public ServerGui() {
		setTitle("MovieServer");
		setSize(fwidth, fheight);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblNewLabel_2 = new JLabel("ServerStatus");
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new GridLayout(0, 2, 0, 0));

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel_1.add(panel_3);

		JLabel lblNewLabel = new JLabel("UserCount :");
		panel_3.add(lblNewLabel);

		lb_c = new JLabel("0");
		panel_3.add(lb_c);

		JPanel panel_4 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_4.getLayout();
		flowLayout_1.setAlignment(FlowLayout.RIGHT);
		panel_1.add(panel_4);

		bt_r = new JButton("Refresh");
		panel_4.add(bt_r);

		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(1, 2, 0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel_2.add(scrollPane);

		log = new JTextArea();
		scrollPane.setViewportView(log);

		setVisible(true);

		activate();
		sr = new ServerReceiver(this);
	}

	public void setMessage(String message) {
		log.append(message + "\n");
	}

	public void setUserCount(int count) {
		lb_c.setText(count + " 명");

	}

	public class ActionLis implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public void activate() {
		try {
			dd = new GetBoxInfo();
			mblist = dd.getMmlist();
			setMessage("박스오피스 갱신완료!");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<MovieBoxInfo> getMblist() {
		return mblist;
	}

	public void setMblist(ArrayList<MovieBoxInfo> mblist) {
		this.mblist = mblist;
	}

}
