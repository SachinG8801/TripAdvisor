import java.util.ArrayList;
import java.util.Scanner;

public class TripAdvisor {
	
	//public static ArrayList<User> allUsers = new ArrayList<User>();
	
	public static void main(String[] args) {
		
		DataStorage data = new SQL_Database();
		
		Scanner input = new Scanner(System.in);
		String userstatus = "";
		
		while(!userstatus.equals("x")) 
		{
			System.out.println();
			System.out.println("Please login or register");
			System.out.println("1: login");
			System.out.println("2: register");
			System.out.println("x: exit");
			
			userstatus = input.nextLine();
			System.out.println();
			
			if(userstatus.equals("1")) 
			{
				new Login(data).Login();
			}
			else if(userstatus.equals("2"))
			{
				new Login(data).registerAccount();
			}
			
			
		
		}
		
		
	}

	

}
