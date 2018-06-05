package main;

import java.sql.CallableStatement;
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
	
	public static String doProcedure(int x, int y, int z) throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
		cn = DriverManager.getConnection(url,user,password); 
		st = cn.createStatement(); 

		String sql = "{CALL call_map"+ z +"(?,?)}"; 
		CallableStatement call = cn.prepareCall(sql); 
		call.setInt(1, x); 
		call.setInt(2, y);
		
		if(call.execute()){ 
		    ResultSet result = call.getResultSet();
		    result.next();

		    spriteType = result.getString(1);
		    result.close(); 
		    cn.close();
		    st.close();
		    return spriteType;

		}
		return null;
		
	}
}