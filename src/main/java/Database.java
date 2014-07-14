//package videos;

import java.sql.*;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Database {
	Video userVideo;
	
	// Variables to connect to database
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	String DB_URL = "";
	String user = "";
	String pass = "";
	String host = "";
	String port = "";
	String database = "";
	String sql;
	
	Connection conn = null;
	Statement stmt = null;
	
	public void connect()
	{
		// OpenShift 
		if (System.getenv("OPENSHIFT_MYSQL_DB_USERNAME") != null)
		{
			System.out.println("openshift");
			System.out.println(System.getenv("OPENSHIFT_MYSQL_DB_USERNAME"));
			String appname = System.getenv("OPENSHIFT_APP_NAME");
			user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
			pass = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
			host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
			port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
			database = "java";
			DB_URL = "jdbc:mysql://" + host + ":" + port + "/" + appname;
		}
		
		//LocalHost
		else
		{

			System.out.println("localhost");
			user = "java";
			pass = "mordor";
			
			DB_URL = "jdbc:mysql://localhost/JAVA";
		}
		
		//STEP 2: Register JDBC driver
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			// Connect
			System.out.println("Creating statement...");
			conn = DriverManager.getConnection(DB_URL, user, pass);
			stmt = conn.createStatement();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public void addVideo(Video userVideo)
	{		
		System.out.println("Adding video to database..");
		try
		{		
			sql = "INSERT INTO videos (user_id, video, title) VALUES ("
					+ userVideo.userID + ", " + "'" + userVideo.link + "'" + ", "
					+ "'" + userVideo.title + "'" + ")";
			stmt.executeUpdate(sql);
			
			System.out.println("Video succesfully added!");
		}
		catch(SQLException se){
		      //Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
			e.printStackTrace();
		}

	}

	public void removeVideo(Video userVideo)
	{
		try
		{
		sql = "DELETE FROM videos WHERE title= " + "'" + userVideo.title + "'"
				+ "AND user_id = " + userVideo.userID;
		stmt.executeUpdate(sql);
		
		System.out.println("Video successfuly removed!");
		}
		catch(SQLException se){
		      //Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public void displayAllVideos()
	{
		try
		{		
			sql = "SELECT id, user_id, video, category_id, title FROM videos";
			ResultSet rs = stmt.executeQuery(sql);
			
			
			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				int id  = rs.getInt("id");
				int userID  = rs.getInt("user_id");
				int categoryID = rs.getInt("category_id");
				String video = rs.getString("video");
				String title = rs.getString("title");
				
		
				//Display values
				System.out.print("ID: " + id);
				System.out.print(", User ID: " + userID);
				System.out.print(" , Category ID: " + categoryID);
				System.out.println(", Title: " + title);
				System.out.println("Video Link: " + video);
			}
			rs.close();
		}
		catch(SQLException se){
		      //Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
			e.printStackTrace();
		}
		
	}
	
	public void addUser(String user, String pass)
	{
		System.out.println("Adding user to database..");
		try
		{		
			sql = "INSERT INTO user (username, password) VALUES ("
					+ "'" + user + "', " 
					+ "'" + pass + "'" + ")";
			stmt.executeUpdate(sql);
			
			System.out.println("User succesfully added!");
		}
		catch(SQLException se){
		      //Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
			e.printStackTrace();
		}
	}
	
	public void displayAllUsers()
	{
		//Display all users in database
			
		try
		{		
			sql = "SELECT id, username, password FROM user";
			ResultSet rs = stmt.executeQuery(sql);
			
			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				int id  = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
		
				//Display values
				System.out.print("ID: " + id);
				System.out.print(", Username: " + username);
				System.out.println(", Password: " + password);
			}
			rs.close();
		}
		catch(SQLException se){
		      //Handle errors for JDBC
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
			e.printStackTrace();
		}
		
		
	}
	
	public void displayOwner(Video userVideo)
	{
		try
		{
		sql = "SELECT DISTINCT username, password FROM user AS u JOIN videos AS v "
				+ "WHERE u.id = "
				+ userVideo.userID;
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			//Retrieve by column name
			String username = rs.getString("username");
			String password = rs.getString("password");
	
			//Display values
			System.out.println("Username: " + username);
			System.out.println("Password: " + password);
		}
		rs.close();
	}
	catch(SQLException se){
	      //Handle errors for JDBC
		se.printStackTrace();
	}catch(Exception e){
	      //Handle errors for Class.forName
		e.printStackTrace();
	}
		
		
	}
	
	public void disconnect()
	{
		try
		{
		conn.close();
		stmt.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
        
        public boolean checkUser(String user, String pass) {
            try
            {		
			sql = "SELECT id, username, password FROM user WHERE username= "
                                + "'" + user + "'" + 
                                        "AND password = "
                                        + "'" + pass + "'";
			ResultSet rs = stmt.executeQuery(sql);
                        System.out.println("\tThis rs: " + rs);
                try {
                    if(rs.getObject("username") == null) {
                        return false;
                    }
                    else {
                        
                    
                        }
                } catch (Exception e) {
                    
                }
			
                } catch(Exception e) {
                    return false;
                }
            return true;
        }
        
        public static void main(String[] args) {
            System.out.println("Starting db test");
            Database db = new Database();
            db.connect();
            System.out.println(db.checkUser("brady", "mordor"));
            db.disconnect();
        }
        
}

