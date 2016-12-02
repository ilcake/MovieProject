package jdbcConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	public static final String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	public static final String id = "hr";
	public static final String pw = "hr";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ConnectionManager() {
	}

	public static Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}

	// 가비지 컬렉터가 볼 때 일을 안하지만 일을 하고있다고 착각하는 객체는 JDBC 가비지 컬렉터가 잘 안잡아준다.
	// 다른것들은 어플리케이션이 끝나면 잘 치워주는데 JDBC는.....XX
	public static void close(Connection con) { // --커넥션을 끊어주는 작업!
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
