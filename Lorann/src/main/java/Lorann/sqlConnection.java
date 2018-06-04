package Lorann;

import java.sql.*;

public class sqlConnection {

	
	public sqlConnection() {
		
	}

	public static String saveInBase(int x, int y, int z) {
		
		String url = "jdbc:cj:mysql://localhost/phpmyadmin/db_structure.php?db=lorann?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String passwd = "";
		
		Connection cn = null;
		Statement st = null;
		String spriteType = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			cn = DriverManager.getConnection(url, user, passwd);
			
			st = cn.createStatement();
			
			String sql = "CALL call_map" + z + "(" + x + ", " + y + ")";
			
			ResultSet result = st.executeQuery(sql);
			
			spriteType = result.getString(1);
			
			return spriteType;
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return spriteType;

	}
}