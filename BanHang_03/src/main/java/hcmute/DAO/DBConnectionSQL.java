package hcmute.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnectionSQL {

	private final String serverName = "EBISU-SAN\\DAOQUYCUONG";
	private final String dbName = "BanHang";
	private final String portNumber = "1433";
	private final String instance = "";
	private final String userID = "sa";
	private final String password = "daoquycuong123";

	public Connection getConnection() throws Exception {
		
		String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName;
		
		if (instance == null || instance.trim().isEmpty())
			
		url = "jdbc:sqlserver://"+serverName+":"+portNumber +";databaseName="+dbName;
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		
		return DriverManager.getConnection(url, userID, password);
	}
	public static void main(String[] args) {
		try {
			System.out.println(new DBConnectionSQL().getConnection());
		} catch (Exception e) {
			e.printStackTrace();
		}
		}

}
