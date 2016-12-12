package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datas.Comment;
import datas.ConnectionManager;
import datas.Data;
import datas.User;
import vos.MovieBoxInfo;

public class ServerDBwork {
	private ConnectionManager cm;
	private ServerGui gui;
	private ServerThread thth;

	public ServerDBwork(ServerGui gui, ServerThread thth) {
		this.gui = gui;
		this.thth = thth;
	}

	public int register(User u) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "insert into usertable values (?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, u.getId());
			pst.setString(2, u.getPw());
			pst.setString(3, u.getMail());
			pst.setString(4, u.getPhN());
			pst.setString(5, u.getPic());
			int result = pst.executeUpdate();
			con.commit();
			con.setAutoCommit(true);
			cm.close(con);

			gui.setMessage(u.getId() + "유저가 가입하였습니다.");
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
			gui.setMessage("유저가 회원가입에 실패하였습니다.");
			return Data.FAIL;
		}
	}

	public User login(String id, String pw) {
		Connection con = new ConnectionManager().getConnection();
		User u = null;
		try {
			String sql = "select * from usertable where userid = ? and userpw = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				u = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
				gui.setMessage(u.getId() + " 회원이 접속하였습니다.");
				thth.setUserID(u.getId());
			}

		} catch (Exception e) {

		}
		cm.close(con);
		return u;
	}

	public void changeIcon(User me, String iconUrl) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "update usertable set userpics=? where userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, iconUrl);
			ps.setString(2, me.getId());
			ps.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

		} catch (Exception e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		cm.close(con);
	}

	public ArrayList<Comment> getComment(String movieCD) {
		ArrayList<Comment> cmList = new ArrayList<>();
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select userid, usertext, grade, moviecd, userpic from usercomment";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Comment c = null;
			while (rs.next()) {
				c = new Comment(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
				cmList.add(c);
			}

		} catch (Exception e) {

		}
		cm.close(con);
		return cmList;
	}

	public boolean writeComment(Comment c) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			String sql = "insert into usercomment values(?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, c.getUserID());
			ps.setString(2, c.getMovieCD());
			ps.setString(3, c.getUserText());
			ps.setDouble(4, c.getGrade());
			ps.setString(5, c.getUserPic());
			ps.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			cm.close(con);
			return true;

		} catch (Exception e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		cm.close(con);
		return false;
	}
}
