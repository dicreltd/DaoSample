public class Main {

	public static void main(String[] args) {
		HanbaiConnection con = new HanbaiConnection();
		ShouhinDAO dao = new ShouhinDAO(con.getConnection());

		Shouhin s = dao.findById(1);

		System.out.println(s.getSname());

		con.close();
	}

}
