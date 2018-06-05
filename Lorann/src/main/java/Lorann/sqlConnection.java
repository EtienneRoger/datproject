package Lorann;

import java.sql.*;

public class sqlConnection {

	
	public sqlConnection() {
		
	}

	public static String saveInBase(int x, int y, int z) {
		//declare and initialize the url's DB and the logs
		String url = "jdbc:cj:mysql://localhost/phpmyadmin/db_structure.php?db=lorann?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String passwd = "";
		
		Connection cn = null;
		Statement st = null;
		String spriteType = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			cn = DriverManager.getConnection(url, user, passwd);			//check for the connection
			
			st = cn.createStatement();										
				
			String sql = "CALL call_map" + z + "(" + x + ", " + y + ")";	//do the query of calling a specific procedure 
			
			ResultSet result = st.executeQuery(sql);						//look for the result
			
			spriteType = result.getString(1);								//add the result of the first column in a String variable
				
			return spriteType;												//return the String
		}catch(SQLException e){
			e.printStackTrace();
			
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			try {
				cn.close();													//close the connection and the statement if all go right
				st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return spriteType;

	}
}