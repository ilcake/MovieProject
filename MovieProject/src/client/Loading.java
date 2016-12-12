package client;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;

public class Loading extends JDialog implements Runnable {
	private JLabel time;
	private String tex;
	private ImageIcon loadingIcon;
	private JLabel loading;

	public Loading() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(122, 173, 300, 250);
		getLabel();
		int xlo = (int) ((d.getWidth() - 300) / 2);
		int ylo = (int) ((d.getHeight() - 200) / 2);

		setLocation(xlo, ylo);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void getLabel() {
		tex = " ";
		loadingIcon = new ImageIcon("./img/loading2.gif");
		////////////// 로딩을 붙여보자
		loading = new JLabel();
		loading.setHorizontalAlignment(SwingConstants.CENTER);
		loading.setIcon(loadingIcon);
		getContentPane().add(loading);
		// loading.setBounds(122, 173, 200, 165);

		time = new JLabel(" ");
		time.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(time, BorderLayout.SOUTH);
		//
		// Thread tg = new Thread(this);
		// tg.start();

	}

	public static void main(String[] args) {
		new Loading();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			time.setText(tex);
			tex += ".";
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.revalidate();
		}

	}
}
