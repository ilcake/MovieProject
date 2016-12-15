package gSearch;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import chrriis.dj.nativeswing.swtimpl.NativeInterface;
import chrriis.dj.nativeswing.swtimpl.components.JWebBrowser;

public class YoutubeViewer {
	StringGiver sg;

	// public static void main(String[] args) {
	//
	// new YoutubeViewer("라라랜드");
	// }

	private String urrr;

	public YoutubeViewer(String urrr) {

		this.urrr = urrr;
		sg = new StringGiver();
		String url = urrr = sg.getURRR(urrr);

		NativeInterface.open();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("YouTube Viewer");
				frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
				frame.getContentPane().add(getBrowserPanel(url), BorderLayout.CENTER);
				frame.setSize(800, 600);
				frame.setLocationByPlatform(true);
				frame.setVisible(true);
			}
		});
		NativeInterface.runEventPump();
		// don't forget to properly close native components
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				NativeInterface.close();
			}
		}));

	}

	public static JPanel getBrowserPanel(String where) {
		JPanel webBrowserPanel = new JPanel(new BorderLayout());
		JWebBrowser webBrowser = new JWebBrowser();
		webBrowserPanel.add(webBrowser, BorderLayout.CENTER);
		webBrowser.setBarsVisible(false);
		webBrowser.navigate(where);
		// webBrowser.navigate(urrr);
		// "https://www.youtube.com/v/8c8sBrMvqWY?version=3&enablejsapi=1&playerapiid=ytplayer"
		return webBrowserPanel;
	}
}