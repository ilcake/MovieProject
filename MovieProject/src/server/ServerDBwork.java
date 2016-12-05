package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import datas.ConnectionManager;
import datas.Data;
import datas.User;

public class ServerDBwork {
	private ConnectionManager cm;
	private ServerGui gui;

	public ServerDBwork(ServerGui gui) {
		this.gui = gui;
	}

	public int register(User u) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "insert into usertable values (?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getId());
			pst.setString(2, u.getPw());
			pst.setString(3, u.getMail());
			pst.setString(4, u.getPhN());
			int result = pst.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			cm.close(con);

			gui.setMessage(u.getId() + "������ �����Ͽ����ϴ�.");
			return Data.RG_SUCCESS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				con.rollback();
				con.setAutoCommit(true);
				cm.close(con);
			} catch (SQLException e1) {
			}
			e.printStackTrace();
			gui.setMessage("������ ȸ�����Կ� �����Ͽ����ϴ�.");
			return Data.FAIL;
		}
	}

	public boolean login(String id, String pw) {
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select * from usertable where userid = ? and userpw = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			

		} catch (Exception e) {

		}

		return false;
	}

	public int searchUserCol(String colName) {

		return Data.FAIL;
	}

}
