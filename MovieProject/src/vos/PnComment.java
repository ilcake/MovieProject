package vos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PnComment extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6357216688207012046L;
	private UserComment c;

	public PnComment(UserComment c) {
		this.c = c;
		mkComment();
	}

	public UserComment getC() {
		return c;
	}

	public void setC(UserComment c) {
		this.c = c;
	}

	public void mkComment() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(355, 80));
		JLabel lb_icon = new JLabel("");
		ImageIcon icn = new ImageIcon(c.getUserPic());
		Image image = icn.getImage();
		Image reSized = image.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
		icn = new ImageIcon(reSized);

		lb_icon.setIcon(icn);
		add(lb_icon, BorderLayout.WEST);

		JTextArea ta_Te = new JTextArea();
		ta_Te.setLineWrap(true);
		ta_Te.setText(c.getUserText());
		add(ta_Te, BorderLayout.CENTER);
		ta_Te.setEditable(false);

		JLabel lb_nick = new JLabel(c.getUserID());
		add(lb_nick, BorderLayout.NORTH);

	}

}
