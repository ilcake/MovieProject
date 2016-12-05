package client.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import datas.ConnectionManager;
import vos.MovieBoxInfo;

public class GetBoxInfo {
	public DailyBOList dbl;
	public ArrayList<MovieBoxInfo> mmlist;
	public ConnectionManager cm;

	public static void main(String[] args) {
		new GetBoxInfo();
	}

	public GetBoxInfo() {
		try {
			dbl = new DailyBOList();
			mmlist = dbl.getMlist();

		} catch (Exception e) {
			e.printStackTrace();
		}
		maketable();
		setTable();
	}

	public void maketable() {
		Connection con = new ConnectionManager().getConnection();
		String sql = "";
		Statement st;
		try {
			sql = "drop table movieboxinfo";
			st = con.createStatement();
			st.executeUpdate(sql);

			sql = "create table movieboxinfo(moviecd varchar2(30),movienm varchar2(50),directornm varchar2(50),opendt varchar2(30),rank number(5),constraint movieb_pk primary key (moviecd))";
			st = con.createStatement();
			st.executeUpdate(sql);

		} catch (Exception e) {

		}
		cm.close(con);

	}

	public void setTable() {
		Connection con = new ConnectionManager().getConnection();
		String sql = "";
		PreparedStatement ps;
		try {
			for (int i = 0; i < mmlist.size(); i++) {
				sql = "insert into movieboxinfo values(?, ?, ?, ?, ?)";
				ps = con.prepareStatement(sql);
				String mc = mmlist.get(i).getMovieCd();
				String nm = mmlist.get(i).getMovieNm();
				String dr = mmlist.get(i).getDirector();
				String dt = mmlist.get(i).getOpenDt();
				ps.setString(1, mc);
				ps.setString(2, nm);
				ps.setString(3, dr);
				ps.setString(4, dt);
				ps.setInt(5, i + 1);
				ps.executeUpdate();
			}
		} catch (Exception e) {

		}
		cm.close(con);
	}

}
