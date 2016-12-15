package gSearch;

import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JTextField;

import javax.swing.JButton;

public class StringGiver extends JFrame {

	private Search sk;
	private Scanner sc;
	private JTextField tf;
	private JButton bt;

	public static void main(String[] args) {
		new StringGiver();
	}

	public Search getSk() {
		return sk;
	}

	public void setSk(Search sk) {
		this.sk = sk;
	}

	public StringGiver() {
		sk = new Search();
		// String uurr = getURRR("라라랜드");
		// giveWEBVieW(uurr);
		// giveWEBVieW("라라랜드, 영화, 트레일러, 공식");
	}

	public void giveWEBVieW(String uurr) {
		uurr = sk.SearchUWant(uurr);
		uurr = getURRR(uurr);
		// new WEB(uurr);
		try {
			Desktop.getDesktop().browse(new URI(uurr));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (URISyntaxException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}

	}

	public String getURRR(String what) {
		String result = sk.SearchUWant(what);
		String ur = "https://www.youtube.com/v/" + result + "?version=3&enablejsapi=1&playerapiid=ytplayer";
		return ur;
	}
}