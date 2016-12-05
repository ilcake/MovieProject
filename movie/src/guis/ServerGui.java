package guis;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Component;
import javax.swing.JTextArea;

public class ServerGui extends JFrame {

	public static void main(String[] args) {
		new ServerGui();
	}

	private static final int fwidth = 400;
	private static final int fheight = 500;
	private JButton bt_r;
	private JLabel lb_c;
	private JTextArea log;

	public ServerGui() {

		setTitle("MovieServer");
		setSize(fwidth, fheight);

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

	}

	public void setMessage(String message) {
		log.append(message + "\n");
	}

	public void setUserCount(int count) {
		lb_c.setText(count + " ��");

	}

}
