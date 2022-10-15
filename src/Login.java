import java.util.Scanner;

public class Login {
	
	private String user_id;
	private String password;
	private String tag1;
	private String tag2;
	private DataStorage data;
	

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Login(DataStorage d)
	{
		data = d;
	}
	
	public void Login()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("enter your username");
		user_id = input.nextLine();
		System.out.println("enter the password");
		password = input.nextLine();
		String userstatus = data.Login(user_id, password);
		
		if(userstatus.equals("admin"))
		{
			Admin admin = new Admin();
			admin.welcome(data);
		}
		
		else if(userstatus.equals("regular_user"))
		{
			RegularUser regular = new RegularUser(user_id,password);
			regular.welcome();		
					
		}
		
		else
			System.out.println("Please provide valid credentials");
		
	}
	
	public void registerAccount()
	{
		Scanner input = new Scanner(System.in);
		
		String[] tags = new String[6];
		
		tags[0] = "History buff";
		tags[1] = "Shopping Fanatic";
		tags[2] = "Beach Goer";
		tags[3] = "Urban Explorer";
		tags[4] = "Nature Lover";
		tags[5] = "Family Vacationer";
		
		System.out.println("Please enter your user id :");
		user_id = input.nextLine();
		System.out.println("Please enter your password :");
		password = input.nextLine();
		String numRegex   = ".*[0-9].*";
		String alphaRegex = ".*[a-z].*";

		if (user_id.matches(numRegex) && user_id.matches(alphaRegex) && !password.equals(user_id))
		{
			System.out.println("Please select any 2 tags from the following :");
			System.out.println("1 - History Buff, 2 - Shopping Fanatic, 3 - Beach Goer, 4 - Urban Explorer, 5 - Nature Lover, 6 - Family Vacationer");
			int t1 = input.nextInt()-1;
			tag1 = tags[t1];
			int t2 = input.nextInt()-1;
			tag2 = tags[t2];
		
			data.createUser(user_id,password,tag1,tag2);   
		}
		else
			System.out.println("The account ID is unique (between 3 and 10 characters),"
					+ " and must contain at least one letter and one digit. "
					+ "Also, the password cannot be the same as the login ID");
		
	}


	public String getUser_id() {
		return user_id;
	}
	
	public Login() {
			
		}

}
