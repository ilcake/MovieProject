package client;

import javax.swing.JFrame;
import javax.swing.UIManager;
import java.awt.CardLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;

public class ClientGui extends JFrame {
	private static final int fwidth = 900;
	private static final int fheight = 540;

	public ClientGui() {
		setTitle("MovieLovers");
		setSize(fwidth, fheight);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel pnLogin = new JPanel();
		getContentPane().add(pnLogin, "name_15049258855123");
		pnLogin.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel = new JPanel();
		pnLogin.add(panel);
		
		JPanel panel_1 = new JPanel();
		pnLogin.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] {5};
		gbl_panel_1.rowHeights = new int[] {5};
		gbl_panel_1.columnWeights = new double[]{Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JPanel pnMain = new JPanel();
		getContentPane().add(pnMain, "name_15181910601831");
		pnMain.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_3 = new JPanel();
		pnMain.add(panel_3);
		
		JPanel panel_4 = new JPanel();
		pnMain.add(panel_4);
		
		JPanel pnMovie = new JPanel();
		getContentPane().add(pnMovie, "name_15183354017821");
		pnMovie.setLayout(new GridLayout(1, 2, 0, 0));
		
		JPanel panel_6 = new JPanel();
		pnMovie.add(panel_6);
		
		JPanel panel_7 = new JPanel();
		pnMovie.add(panel_7);

		try {
			UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel");
		} catch (Exception e) {
			System.out.println("dd");
		}

		setVisible(true);
	}

}
