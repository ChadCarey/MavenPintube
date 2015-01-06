//package videos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.util.logging.Level;
//import java.util.logging.Logger;

public class Database {
    
        private final String DB_NAME = "java"; /*THIS MUST BE CHANGED TO THE NAME OF YOUR DATABASE*/
    
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
			//String appname = System.getenv("OPENSHIFT_APP_NAME");
			user = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
			pass = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
			host = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
			port = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
			database = "java";
			DB_URL = "jdbc:mysql://" + host + ":" + port + "/" + DB_NAME;
		}
		else
		{
			System.out.println("localhost");
			user = "java";
			pass = "mordor";	
			DB_URL = "jdbc:mysql://localhost/" + DB_NAME;
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
                    System.out.println("Failed to connect to the database");
			e.printStackTrace();
                        System.exit(-1);
		}
	}
	
        /**
         * Loads a video object and passes it on to addVideo(Video v);
         * @param title
         * @param videoID
         * @param username 
         * @param categoryID 
         */
        public void addVideo(String title, String videoID, String username, int categoryID) {
            try {
                System.out.println("Getting user ID");
                sql = "SELECT id FROM user WHERE username='" + username + "'";
                System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                System.out.println("Getting ID from response");
                System.out.println("Cointans next: " + rs.next());
                int userID = rs.getInt("id");
                System.out.println("ID: " + userID);
                addVideo(new Video(userID, title, videoID, categoryID));
            } catch (SQLException ex) {
                Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception e) {
                System.out.println("ERROR 0");
            }
        }
        
	public void addVideo(Video userVideo)
	{		
		System.out.println("Adding video to database..");
		try
		{	// check for and ignore duplicates	
			sql = "INSERT IGNORE INTO videos (user_id, video, title, category_id) VALUES ("
					+ userVideo.userID + ", " + "'" + userVideo.link + "'" + ", "
					+ "'" + userVideo.title + "'" + ", " + userVideo.category + ")";
                        System.out.println(sql);
			stmt.executeUpdate(sql);
			
			System.out.println("Video succesfully added!");
		}
		catch(SQLException se){
		      //Handle errors for JDBC
                    System.out.println("ERROR 1");
			se.printStackTrace();
		}catch(Exception e){
		      //Handle errors for Class.forName
                    System.out.println("ERROR 2");
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
	
        
        public List<Video> getUserVideos(String user, int category) {
            List<Video> videos = new ArrayList<Video>();
            
            try
		{		
			sql = "SELECT v.id, user_id, video, category_id, title FROM videos v";
                        sql += " JOIN user u ON u.id = v.user_id " + "WHERE username='" + user + "'";
                        sql += " and v.category_id=" + category;
                        System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Obtained results");
			
			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				int id  = rs.getInt("id");
				int userID  = rs.getInt("user_id");
				int categoryID = rs.getInt("category_id");
				String link = rs.getString("video");
				String title = rs.getString("title");
		
				//Display values
				System.out.print("ID: " + id);
				System.out.print(", User ID: " + userID);
				System.out.print(" , Category ID: " + categoryID);
				System.out.println(", Title: " + title);
				System.out.println("Video Link: " + link);
                                
                                videos.add(new Video(userID, title, link, categoryID));
			}
			rs.close();
                } catch (SQLException e) {
                    System.out.println("ERROR loading user videos e=1\n" + e.getMessage());
                    e.printStackTrace();
                    
                } catch (Exception e) {
                    System.out.println("ERROR loading user videos e=2\n" + e.getMessage());
                    e.printStackTrace();
                }
            
            return videos;
        }
         public List<Reel> getUserReels(String user) {
            List<Reel> reels = new ArrayList<Reel>();
            
            try
		{		
			sql = "SELECT DISTINCT r.id, category FROM category r";
                        sql += " JOIN videos v ON v.category_id = r.id ";
                        sql += " JOIN user u ON u.id = v.user_id WHERE username='" + user + "'";
                        System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			System.out.println("Obtained results");
			
			//STEP 5: Extract data from result set
			while(rs.next())
			{
				//Retrieve by column name
				int id  = rs.getInt("id");
				//int userID  = rs.getInt("user_id");
				//int categoryID = rs.getInt("category_id");
				//String link = rs.getString("video");
				String title = rs.getString("category");
		
				//Display values
				System.out.print("ID: " + id);
				//System.out.print(", User ID: " + userID);
				//System.out.print(" , Category ID: " + categoryID);
				//System.out.println(", Title: " + title);
				System.out.println("Category: " + title);
                                
                                reels.add(new Reel(id, title));
			}
			rs.close();
                } catch (SQLException e) {
                    System.out.println("ERROR loading user reels e=1\n" + e.getMessage());
                    e.printStackTrace();
                    
                } catch (Exception e) {
                    System.out.println("ERROR loading user reels e=2\n" + e.getMessage());
                    e.printStackTrace();
                }
            
            return reels;
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
        public boolean userExists(String user) {
        	try
            {		
            	sql = "SELECT username FROM user WHERE username= "
                                + "'" + user + "'";
			
				ResultSet rs = stmt.executeQuery(sql);
				while (rs.next()) {
					  String userN = rs.getString("username");
					  System.out.println("Users exists: " + userN + "\n");
					  return true;
					}
                System.out.println("free username\n");
                return false;
            } catch(Exception e) {
            	System.out.println("ERROR with try in database validation\n");
                return false;
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
				while (rs.next()) {
					  String userN = rs.getString("username");
					  System.out.println("Welcome: " + userN + "\n");
					  return true;
					}
                System.out.println("\tThis rs : " + rs);
                System.out.println("invalid login\n");
                return false;
            } catch(Exception e) {
            	System.out.println("ERROR with try in database validation\n");
                return false;
            }
        }
        
        public static void main(String[] args) {
           /* System.out.println("Starting db test");
            Database db = new Database();
            db.connect();
            System.out.println(db.checkUser("brady", "mordor"));
            db.getUserVideos("brady");
            db.disconnect();*/
        }
        
}

