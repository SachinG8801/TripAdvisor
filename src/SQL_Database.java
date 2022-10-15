import java.sql.Connection;
import java.util.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;

public class SQL_Database implements DataStorage{

	


	final String url = "jdbc:mysql://mis-sql.uhcl.edu/gopalakrishns88?useSSL=false";
	final String db_id = "gopalakrishns88";
	final String db_psw = "2008266";
	
	public void createUser(String user_id, String password, String tag1, String tag2)
	{
		Connection conn = null;
		Statement stm = null;
		//ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			conn.setAutoCommit(false);
			
		int r = stm.executeUpdate("Insert into user values ('" + user_id + "', '" +
					password + "', '" + tag1 + "', '" + tag2 + "' , '" + "regular_user" +
					"')");
			conn.commit();
			conn.setAutoCommit(true);
			
			System.out.println("******** Registration succesfull ********");
			System.out.println();
			
	}
		catch(SQLException e)
		{
			
			System.out.println("Account creation failed");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				//rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
}

	
	public void createAttraction(String Attraction, String city, double score, String description, String tag) {
				Connection conn = null;
				Statement stm = null;
				//ResultSet rs = null;
				
				try
				{
					//connect to db
					conn = DriverManager.getConnection(url, db_id, db_psw);
					//create an instance of sql statement
					stm = conn.createStatement();
					
					
					
					conn.setAutoCommit(false);
					
					int r = stm.executeUpdate("Insert into attraction values ('" + Attraction + "', '" +
								description + "', '" + tag + "', '" + city + "' , '" + "pending" +
								"', '" + score + "' )");
						conn.commit();
						conn.setAutoCommit(true);

					System.out.println("Attraction created succesfully");
						
					
			}
				catch(SQLException e)
				{
					
					System.out.println("Attraction creation failed");
					//e.printStackTrace();
					
				}
				
				finally
				{
					//close the db connection
					try
					{
						conn.close();
						stm.close();
						//rs.close();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
		
	}

	
	public String Login(String user_id, String password) {
		
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			
			
		   rs = stm.executeQuery("Select * from user where user_id ='" + user_id + "'");
		
		
			if(rs.next())
			{
				
				if(password.equals(rs.getString(2))) 
				{
					System.out.println();
					System.out.println("******** Login succesfull ********");
					System.out.println();
					
					return rs.getString(5);					
					
				}
				
				else 
				{
					System.out.println();
					System.out.println("incorrect Password");
					
					return "******** Password incorrect";
					//System.out.println();					
				}			
			}
			
			else
			{
				System.out.println();
				System.out.println("user id is wrong");
				return "******** User id incorrect";
				//System.out.println();
			}

			
				
			
	}
		catch(SQLException e)
		{
			
			return "Account creation failed";
			//e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	

	public void  displayattraction() 
	{
	
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			
		   rs = stm.executeQuery("Select title from attraction where status = 'pending'");
		
		   if (rs.next() == false) {
	        System.out.println("No pending attractions to review enter X to exit");
	     }
		   else 
		      {
			   System.out.println("the list of pending requests ");
			   System.out.println();
			        do 
			        {
			        	
			          System.out.println(rs.getString(1));
			        } while (rs.next());
			        System.out.println("Enter title to be approved or X to exit");
		      }
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


	public void approveOrReject(String title) {
		Connection conn = null;
		Statement stm = null;
		//ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
		   int result = stm.executeUpdate("UPDATE attraction SET status='Approved' WHERE title='"+title+"' ");
		
		System.out.println("Updated the status of "+title);
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
			//	rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

	public void searchAttraction(String attractionTagOrCity) {
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			
		   rs = stm.executeQuery("Select * from attraction where status = 'Approved' and( "
		   		+ "tag='"+attractionTagOrCity+"' OR city = '" +attractionTagOrCity+"')");
		  
			
			int row = 0;
			
			String title = null;
		
				   while(rs.next())
					{
					    System.out.println();
					    System.out.println("Result Attractions");
					    System.out.println();
					    
						System.out.println(" Attraction: " +rs.getString("title")+"  Description: "+rs.getString("description")
						+"  Tag: "+rs.getString("tag")+"  City: "+rs.getString("city")+"  Rating: "+rs.getString("rating"));
						System.out.println();
						
						row =1;
						
						title = rs.getString("title");
																
					}	
		
				   if (row == 0 )
					   {
						   System.out.println("No Attractions were found");
					   }
		
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void topAttractions(String title) 
	{
		Connection conn = null;
		Statement stm = null;
		ResultSet rr = null;
		ResultSet rc = null;
		
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			 DecimalFormat df=new DecimalFormat("#.##");
			  rr = stm.executeQuery("Select AVG(rating) FROM comment WHERE attraction = '"+title+"'");
  
			   if(rr.next())
			   {
				   double d = rr.getDouble(1);
				   int u = stm.executeUpdate("UPDATE attraction SET rating = '"+d+"' WHERE title = '"+title+"' ");
				  
				   System.out.println("The average rating is: " + df.format(d));
			   }
			   
			   rc = stm.executeQuery("Select content FROM comment WHERE attraction = '"+title+"'");
			   
			   System.out.println("The comments are: ");
			   while(rc.next())
			   {
				  System.out.print(rc.getString(1) +"*****");
				  System.out.println();
			   }
		}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rr.close();
				rc.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
			
	}


	public void writeReview(String title, String review, String user_id, double rating)
	{
		Connection conn = null;
		Statement stm = null;
		//ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			conn.setAutoCommit(false);
			
		   int result = stm.executeUpdate("Insert into comment values ('" + title + "', '" +
					review + "', '" + user_id + "', '" + rating + "' , '" + DateAndTime.DateTime() + "')");
		   
		   conn.commit();
			conn.setAutoCommit(true);
		
		
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
			//	rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		
		
	}


	public void addFavorites(String user_id, String attraction) 
	{
		
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			conn.setAutoCommit(false);
			
			rs = stm.executeQuery("Select * From favorite WHERE attraction = '" + attraction + "' and user_id = "
					+ "'"+user_id+"' ");
			
			if(!rs.next())
			{
				int result = stm.executeUpdate("Insert into favorite values ('" + user_id + "', '" + attraction + "')");
				System.out.println("Attraction added successfully to your favorites list");
			}
			
			else
			{
				System.out.println("This attraction is already on your favorite list");
			}
		   
		   conn.commit();
			conn.setAutoCommit(true);
			
			
		
		
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


	
	public void topThreeTrendingPlaces(String user_id)
	{
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			
		   rs = stm.executeQuery("SELECT title FROM `attraction` WHERE tag ="
		   		+ " ( SELECT tag_1 FROM `user` WHERE user_id = '"+user_id+"') OR tag = "
		   		+ "( SELECT tag_2 FROM `user` WHERE user_id = '"+user_id+"') ORDER BY rating DESC LIMIT 3;");
		   System.out.println("Your suggestions");
			while(rs.next())
			{
				
				System.out.println(rs.getString(1));														
			}				
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	
	}


	
	public void viewFaq(String attraction) 
	{
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			System.out.println();
		   rs = stm.executeQuery("SELECT * FROM faq WHERE att_title = '"+attraction+"';");
		   System.out.println();
		   int i = 1;
		   
			while(rs.next())
			{
				
				System.out.println("Question" + rs.getString("q_id") +": " +rs.getString("question"));
				System.out.println("Answer: " +rs.getString("answer"));
			}				
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


	
	public void postQuestion(String attraction, String user_id, String question) 
	{
		

		Connection conn = null;
		Statement stm = null;
		//ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			conn.setAutoCommit(false);
			
			Random random = new Random();
			int randomInteger = random.nextInt(100);
			
				int r = stm.executeUpdate("Insert into faq values('" + attraction + "','" + user_id + "',"
						+ "0, 'n', '" + question + "', 'ID"+randomInteger+"', ' ' )");		
			
				System.out.println("Question posted successfully");
			
		   conn.commit();
			conn.setAutoCommit(true);
			
			
		
		
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				//rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}


	
	public void postAnswer(String q_id, String answer)
	{
		
		Connection conn = null;
		Statement stm = null;
		//ResultSet rs = null;
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			conn.setAutoCommit(false);
					
				int r = stm.executeUpdate("Update faq SET answer = '"+answer+"', flag = 1, answered = 'y'"
						+ " WHERE q_id='"+q_id+"'");		
			
				System.out.println("Answer posted successfully");
			
		   conn.commit();
			conn.setAutoCommit(true);
				
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				//rs.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}


	
	public void faqNotification(String user_id)
	{
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		ResultSet rr = null;
		String selection = " ";
		
		try
		{
			//connect to db
			conn = DriverManager.getConnection(url, db_id, db_psw);
			//create an instance of sql statement
			stm = conn.createStatement();
			
			rs = stm.executeQuery("Select COUNT(att_title) FROM faq WHERE user_id = '"+user_id+"' AND flag = 1 ");
			if(rs.next())
			{
				System.out.println("There are " + rs.getString(1) +" new notifications");
				Scanner input = new Scanner(System.in);
				String res = rs.getString(1);
				if(!res.equals("0"))
				{
					System.out.println("Would you like to view notification? : Y/N ");
					
					selection = input.nextLine();
				}
					
				rr = stm.executeQuery("Select * FROM faq WHERE user_id = '"+user_id+"' AND flag = 1 ");
				if(selection.equalsIgnoreCase("y"))
				{
					while(rr.next())
					{
						System.out.println("Question: " +rr.getString("question"));
						System.out.println("Answer: " +rr.getString("answer"));
					}
					
					conn.setAutoCommit(false);
					
					int r = stm.executeUpdate("Update faq SET flag = 0  WHERE user_id='"+user_id+"'");	
				
			   conn.commit();
				conn.setAutoCommit(true);
				}
				
			}
						
			
	}
		catch(SQLException e)
		{
			
			System.out.println( "System error");
			e.printStackTrace();
			
		}
		
		finally
		{
			//close the db connection
			try
			{
				conn.close();
				stm.close();
				rs.close();
				rr.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}
