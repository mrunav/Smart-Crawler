package smartCrawler.LoginRegister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dbconn {
	public static String ProjectName="Smart Crawler";
	static Connection conn;
	public Dbconn() throws SQLException {
		// initComponents();
		// Connection con;

	}

	public static Connection getConnection(){
	
		try{
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection(
				"jdbc:mysql://localhost/smartcrawler", "root", "");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return (conn);

	}
	
	public static void CloseConnection()
    {
        if(conn!=null)
        {
            try
            {
                conn.close();
                conn = null;
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }            
        }
        
    }

	public static ResultSet getResultFromSqlQuery(String SqlQueryString) {
		//System.out.println("in funcation");
		ResultSet rs = null;
	//	Connection conn = null;
		try {
			if (conn == null) {
				getConnection();
			}
			rs = conn.createStatement().executeQuery(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return rs;
	}
	
	public static void insertUpdateFromSqlQuery(String SqlQueryString) {
		System.out.println("in funcation insertUpdateFromSqlQuery "+SqlQueryString);
		
		try {
			if (conn == null) {
				getConnection();
			}
			conn.createStatement().executeUpdate(SqlQueryString);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
