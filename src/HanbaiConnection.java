import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HanbaiConnection {
	private Connection con=null ;

	Connection getConnection(){
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/hanbai", "root", "");
		} catch (SQLException e) {
			System.out.println("DBコネクションエラー:" + e.getMessage());
		}
		return con;
	}

	void close() {
		try {
			if( con != null ) {
				con.close();
			}
		}catch (SQLException e) {
			System.out.println("DBクローズエラー：" + e.getMessage());
		}
	}
}
