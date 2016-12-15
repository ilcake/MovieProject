package vos;

import javax.swing.JTextArea;

public class MyText extends JTextArea {
	private UserComment comment;

	public MyText(UserComment comment) {
		super();
		this.comment = comment;
	}

	public UserComment getComment() {
		return comment;
	}

	public void setComment(UserComment comment) {
		this.comment = comment;
	}

}
