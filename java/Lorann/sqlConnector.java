package Lorann;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlConnector {
	
	public static String url = "jdbc:mysql://localhost/lorann?useSSL=false&serverTimezone=UTC";
	public static String user = "root";
	public static String password = "";
	public static String spriteType;
	
	public static Connection cn = null;
	public static Statement st = null;
	
	
	public sqlConnector() {
		
	}
	
	public static String doProcedure(int x, int y, int z) {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, user, password);
			st = cn.createStatement();
			
			String procedure = "CALL call_map" + z + "(" + x + "," + y +")";
			
			ResultSet result = st.executeQuery(procedure);
			
			spriteType = result.getString(1);
			
			return spriteType;
		}catch(SQLException e) {
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
		
		return null;
	}
	
}