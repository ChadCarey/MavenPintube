
public class Video {
	int id;
	int userID;
	String title;
	//String [] category;
	String link;
	
	public Video (int userID1, String title1, String link1)
	{
		userID = userID1;
		title = title1;
		link = link1;
	}
	
	public int getID()
	{
		return id;
	}
	
	public int getUserID()
	{
		return userID;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getLink()
	{
		return link;
	}
}
