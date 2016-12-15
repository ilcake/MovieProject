package gSearch;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebView;

public class WEB extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8521698739351702730L;

	public static void main(String[] args) {
		new WEB("");
	}

	@SuppressWarnings("restriction")
	public WEB(String uurr) {
		setSize(800, 550);
		JFXPanel jfxPanel = new JFXPanel(); // Scrollable JCompenent
		Platform.runLater(() -> { // FX components need to be managed by JavaFX
			WebView webView = new WebView();
			webView.getEngine().loadContent("<html> Hello World!");
			webView.getEngine()
					.load("http://www.youtube.com/v/8c8sBrMvqWY?version=3&enablejsapi=1&playerapiid=ytplayer");
			jfxPanel.setScene(new Scene(webView));
			/// VzqDNIhslzQ
			// https://www.youtube.com/v/8c8sBrMvqWY?version=3&enablejsapi=1&playerapiid=ytplayer
		});

		getContentPane().add(jfxPanel);

		setVisible(true);

	}

}
