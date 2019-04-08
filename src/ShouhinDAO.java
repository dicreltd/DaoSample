import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShouhinDAO {
	private Connection con;

	public ShouhinDAO(Connection con) {
		this.con = con;
	}

	public ArrayList<Shouhin> findAll(){
		ArrayList<Shouhin> list = new ArrayList<Shouhin>();
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from shouhin");
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Shouhin s = new Shouhin();
				s.setSid(rs.getInt("sid"));
				s.setSname(rs.getString("sname"));
				s.setTanka(rs.getInt("tanka"));
				list.add(s);
			}

			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return list;
	}

	public Shouhin findById(int sid){
		Shouhin shouhin=null;
		try{
			PreparedStatement stmt = con.prepareStatement("SELECT * from shouhin WHERE sid = ?");
			stmt.setInt(1,sid);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				shouhin= new Shouhin();
				shouhin.setSid(rs.getInt("sid"));
				shouhin.setSname(rs.getString("sname"));
				shouhin.setTanka(rs.getInt("tanka"));
			}

			rs.close();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.println("SELECTエラー：" + e.getMessage());
		}
		return shouhin;
	}

	public void insert(Shouhin shouhin) {
		try{
			String sql = "INSERT INTO SHOUHIN (sname,tanka) VALUES(?,?)";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,shouhin.getSname());
			stmt.setInt(2,shouhin.getTanka());

			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("INSERTエラー：" + e.getMessage());
		}
	}

	public void update(Shouhin shouhin) {
		try{
			String sql = "UPDATE SHOUHIN SET sname=?,tanka=? WHERE sid=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1,shouhin.getSname());
			stmt.setInt(2,shouhin.getTanka());
			stmt.setInt(3,shouhin.getSid());

			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("UPDATE エラー：" + e.getMessage());
		}
	}

	public void delete(int sid) {
		try{
			String sql = "DELETE FROM SHOUHIN WHERE sid=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,sid);

			stmt.executeUpdate();
		}
		catch (SQLException e) {
			System.out.println("DELETE エラー：" + e.getMessage());
		}
	}

}
