package jdbcConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import movie.DailyBOList;
import vos.MovieBoxInfo;

public class GetBoxInfo {
	public DailyBOList dbl;
	public ArrayList<MovieBoxInfo> mmlist;
	public ConnectionManager cm;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GetBoxInfo();
	}

	public GetBoxInfo() {
		try {
			dbl = new DailyBOList();
			mmlist = dbl.getMlist();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setTable();
	}

	public void setTable() {
		Connection con = new ConnectionManager().getConnection();
		String sql = "";
		PreparedStatement ps;
		try {
			for (int i = 1; i < mmlist.size() + 1; i++) {
				String mc = mmlist.get(i).getMovieCd();
				String nm = mmlist.get(i).getMovieNm();
				String dr = mmlist.get(i).getDirector();
				String dt = mmlist.get(i).getOpenDt();
				int rank = i;
				sql = "insert into movieboxinfo values(?, ?, ?, ?, ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, mc);
				ps.setString(2, nm);
				ps.setString(3, dr);
				ps.setString(4, dt);
				ps.setInt(5, rank);
				ps.executeUpdate();
			}
		} catch (Exception e) {

		}
		cm.close(con);
	}

}
