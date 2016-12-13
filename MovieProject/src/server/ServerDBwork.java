package server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import datas.ConnectionManager;
import datas.Data;
import datas.User;
import vos.UserComment;
import vos.MovieBoxInfo;
import vos.MovieSearchInfo;

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

	public ArrayList<UserComment> getComment(String movieCD) {
		ArrayList<UserComment> cmList = new ArrayList<>();
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select userid, usertext, grade, moviecd, userpic from usercomment";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			UserComment c = null;
			while (rs.next()) {
				c = new UserComment(rs.getString(1), rs.getString(2), rs.getDouble(3), rs.getString(4), rs.getString(5));
				cmList.add(c);
			}

		} catch (Exception e) {

		}
		cm.close(con);
		return cmList;
	}

	public boolean writeComment(UserComment c) {
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

	public void saveUserLike(String id, MovieSearchInfo msi) {
		int isIt = getUserLikebyCD(id, msi.getMvCode());
		Connection con = new ConnectionManager().getConnection();

		if (isIt == Data.USERLIKETHIS) {
			try {
				con.setAutoCommit(false);
				String sql = "delete userlike where userid=? and moviecd=?";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, msi.getMvCode());
				ps.executeUpdate();

				con.commit();
				con.setAutoCommit(true);

			} catch (SQLException e) {

				try {
					con.rollback();
					con.setAutoCommit(true);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
			}

		} else {

			try {
				con.setAutoCommit(false);
				String sql = "insert into userlike values(?,?,?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, id);
				ps.setString(2, msi.getMvCode());
				ps.setString(3, msi.getMvTitle());
				ps.setString(4, msi.getMvGenre());
				ps.setString(5, msi.getMvDirector());
				ps.setString(6, msi.getMvActor());
				ps.setString(7, msi.getMvThumb());
				ps.setString(8, msi.getMvStory());
				ps.setString(9, msi.getMvTm());
				ps.executeUpdate();
				con.commit();

				con.setAutoCommit(true);
				cm.close(con);

			} catch (Exception e) {
				try {
					con.rollback();
					con.setAutoCommit(true);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}
		cm.close(con);
	}

	public int getUserLikebyCD(String id, String mvCd) {
		int result = -1;
		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select userid, moviecd from userlike where userid=? and moviecd=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, mvCd);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
			}
			cm.close(con);

		} catch (Exception e) {
			result = Data.FAIL;
		}
		cm.close(con);
		return result;
	}

	public ArrayList<MovieSearchInfo> getUserLikebyID(String id) {
		ArrayList<MovieSearchInfo> sList = new ArrayList<>();

		Connection con = new ConnectionManager().getConnection();
		try {
			String sql = "select moviecd, title, genre, director, actor, thumb, story, mytm from userlike where userid=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			MovieSearchInfo m = null;
			while (rs.next()) {
				m = new MovieSearchInfo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8));
				sList.add(m);
			}
			cm.close(con);

		} catch (Exception e) {

		}
		cm.close(con);

		return sList;
	}
}
