import java.util.Scanner;

public class RegularUser extends User {
	
	//private DataStorage data;
	private String user_id;
	private String password;
	
	DataStorage data = new SQL_Database();
		
		public RegularUser(String user_id, String password) 
		{
			this.user_id = user_id;
		}
		
		public void welcome() {
			String title = null;
			System.out.println("Regular User welcome page");
			
			System.out.println();
			System.out.println("*************************************************");
			data.topThreeTrendingPlaces(user_id);
			System.out.println("*************************************************");
			System.out.println();
			data.faqNotification(user_id);
			System.out.println("Please select from the menu below");
			System.out.println();
			Scanner input = new Scanner(System.in);
			System.out.println("1: create attraction");
			System.out.println("2: Search attraction");
			System.out.println("0: exit");
			System.out.println();
			String menu = input.nextLine();
			Attraction attraction = new Attraction();
			while(!menu.equals("0"))
			{
				if(menu.equals("1"))
				{
					Attraction newatt = new Attraction();
					newatt.createAttraction();
				}
				else if(menu.equals("2"))
				{
					
					System.out.println("Please search by entering city or tag OR enter 0 to view other options");
					Scanner inp = new Scanner(System.in);
					String attractionTagOrCity = input.nextLine(); 
					
					if(!attractionTagOrCity.equals("0"))
					{
						
						boolean attractionFound= attraction.searchAttraction(attractionTagOrCity);
						
							if(attractionFound == true)
							{	
								System.out.println("Please enter the title to view the Attraction OR enter 0 to exit");
								Scanner in = new Scanner(System.in);
								
								 title = in.nextLine();
								 
								 if(!title.equals("0"))
									 data.topAttractions(title);
									 attraction.viewAttraction(user_id,title);
							}
							
						else
							break;
					}
				
				}

				System.out.println("1: create attraction");
				System.out.println("2: Search attraction");
				System.out.println("0: Exit");
				menu = input.nextLine();
			}
			
		}

		public String getUser_id() {
			return user_id;
		}




		public void setUser_id(String user_id) {
			this.user_id = user_id;
		}




		public String getPassword() {
			return password;
		}




		public void setPassword(String password) {
			this.password = password;
		}


}
