package datas;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String pw;
	private String mail;
	private String phN;

	public User(String id, String pw, String mail, String phN) {
		super();
		this.id = id;
		this.pw = pw;
		this.mail = mail;
		this.phN = phN;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhN() {
		return phN;
	}

	public void setPhN(String phN) {
		this.phN = phN;
	}

	@Override
	public String toString() {
		return "����� ���� [ ���̵� : " + id + ", �̸��� : " + mail + ", ��ȭ��ȣ : " + phN + "]";
	}

}
