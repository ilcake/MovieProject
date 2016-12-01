package step6.server;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import connect.ConnectionManager;
import step6.manager.SEManager;
import step6.vo.Human;
import step6.vo.Professor;
import step6.vo.Staff;
import step6.vo.Trainee;

public class SESServerManager implements SEManager {
	private ResultSet rs;
	private String sql;
	private ArrayList<Human> hlist;
	private ConnectionManager cm;

	public SESServerManager() {

	}

	@Override
	public boolean insertHuman(Human human) {

		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			sql = "insert into human values (?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, human.getName());
			ps.setInt(2, human.getAge());
			ps.setString(3, human.getJumin());
			String oth = "";
			if (human instanceof Professor) {
				oth = "Professor";
			} else if (human instanceof Staff) {
				oth = "Staff";
			} else if (human instanceof Trainee) {
				oth = "Trainee";
			}
			ps.setString(4, oth);
			ps.executeUpdate();
			ps.clearBatch();

			switch (oth) {
			case "Professor":
				sql = "insert into professor values (?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, human.getJumin());
				ps.setString(2, ((Professor) human).getMajor());
				break;
			case "Staff":
				sql = "insert into staff values (?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, human.getJumin());
				ps.setString(2, ((Staff) human).getField());
				break;
			case "Trainee":
				sql = "insert into trainee values (?,?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, human.getJumin());
				ps.setString(2, ((Trainee) human).getStdNo());
				break;
			}
			ps.executeUpdate();
			con.commit();
			con.setAutoCommit(true);

		} catch (Exception e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				System.out.println("NO!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
		cm.close(con);
		return true;
	}

	@Override
	public Human findHuman(String jumin) {
		Human h = null;
		Connection con = new ConnectionManager().getConnection();
		try {
			sql = "select type from human where jumin= ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, jumin);
			rs = ps.executeQuery();

			String type = "";
			PreparedStatement ps2;
			if (rs.next())
				type = rs.getString(1);
			switch (type) {
			case "Professor":
				sql = "select h.name, h.age, h.jumin, p.major from human h join professor p on h.jumin=p.jumin where h.jumin =?";
				ps2 = con.prepareStatement(sql);
				ps2.setString(1, jumin);
				rs = ps2.executeQuery();
				if (rs.next())
					h = new Professor(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				break;
			case "Staff":
				sql = "select h.name, h.age, h.jumin, p.field from human h join staff p on h.jumin=p.jumin where h.jumin =?";
				ps2 = con.prepareStatement(sql);
				ps2.setString(1, jumin);
				rs = ps2.executeQuery();
				if (rs.next())
					h = new Staff(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				break;
			case "Trainee":
				sql = "select h.name, h.age, h.jumin, p.stdno from human h join trainee p on h.jumin=p.jumin where h.jumin =?";
				ps2 = con.prepareStatement(sql);
				ps2.setString(1, jumin);
				rs = ps2.executeQuery();
				if (rs.next())
					h = new Trainee(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4));
				break;
			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("NO!");
		}
		cm.close(con);
		return h;
	}

	@Override
	public boolean deleteHuman(String jumin) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			sql = "select h.type from human h " + "left outer join professor p on h.jumin=p.jumin "
					+ "left outer join staff s on h.jumin=s.jumin " + "left outer join trainee t on h.jumin=t.jumin "
					+ "where h.jumin = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, jumin);
			rs = ps.executeQuery();
			String type = "";
			if (rs.next()) {
				type = rs.getString(1);
			}
			switch (type) {
			case "Professor":
				sql = "delete professor where jumin = ?";
				break;
			case "Staff":
				sql = "delete staff where jumin = ?";
				break;
			case "Trainee":
				sql = "delete trainee where jumin = ?";
				break;
			default:
				con.rollback();
				con.setAutoCommit(true);
				cm.close(con);
				return false;
			}
			ps = con.prepareStatement(sql);
			ps.setString(1, jumin);
			ps.executeUpdate();

			String sql2 = "delete human where jumin = ?";
			PreparedStatement ps23 = con.prepareStatement(sql2);
			ps23.setString(1, jumin);
			ps23.executeUpdate();

			con.commit();
			con.setAutoCommit(true);
			cm.close(con);
			return true;

		} catch (Exception e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				cm.close(con);
				System.out.println("NO!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public void updateHuman(Human newData) {
		Connection con = new ConnectionManager().getConnection();
		try {
			con.setAutoCommit(false);
			PreparedStatement ps = null;
			if (newData instanceof Professor) {
				sql = "update professor set major = ? where jumin = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ((Professor) newData).getMajor());
				ps.setString(2, newData.getJumin());
			} else if (newData instanceof Staff) {
				sql = "update staff set field = ? where jumin = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ((Staff) newData).getField());
				ps.setString(2, newData.getJumin());
			} else if (newData instanceof Trainee) {
				sql = "update trainee set stdno = ? where jumin = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, ((Trainee) newData).getStdNo());
				ps.setString(2, newData.getJumin());
			}
			ps.executeUpdate();

			sql = "update human set name = ?, age = ? where jumin = ?";
			PreparedStatement ps2 = con.prepareStatement(sql);
			ps2.setString(1, newData.getName());
			ps2.setInt(2, newData.getAge());
			ps2.setString(3, newData.getJumin());
			ps2.executeUpdate();

			con.commit();
			con.setAutoCommit(true);

		} catch (Exception e) {
			try {
				con.rollback();
				con.setAutoCommit(true);
				System.out.println("NO!");
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		cm.close(con);
	}

	@Override
	public ArrayList<Human> getHumanList() {
		ArrayList<Human> hlist = new ArrayList();
		Connection con = new ConnectionManager().getConnection();
		try {

			sql = "select h.name, h.age, h.jumin, p.major from human h join professor p on h.jumin=p.jumin";
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				hlist.add((Human) new Professor(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			sql = "select h.name, h.age, h.jumin, p.field from human h join staff p on h.jumin=p.jumin";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				hlist.add((Human) new Staff(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			sql = "select h.name, h.age, h.jumin, p.stdno from human h join trainee p on h.jumin=p.jumin";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				hlist.add((Human) new Trainee(rs.getString(1), rs.getInt(2), rs.getString(3), rs.getString(4)));
			}
			cm.close(con);

		} catch (Exception e) {
			cm.close(con);
			System.out.println("NO!");
			return null;
		}
		return hlist;
	}

}
