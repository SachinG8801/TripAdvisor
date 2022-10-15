import java.util.Scanner;

public class Admin extends User
{
private DataStorage data;
private String user_id;
private String password;

	public Admin(DataStorage d) {
		super(d);
	}

	public Admin(String user_id, String password) 
	{
		super(user_id, password);
	}
	
	public void welcome(DataStorage data) {
		
		System.out.println("Admin welcome page");
		
		data.displayattraction();
		
		Scanner in = new Scanner(System.in);	
		
		String title = in.nextLine();
			
		while(!(title.equals(" ") || title.equals("x"))) 
		{
		data.approveOrReject(title);
		 title = in.nextLine();
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
	
	public Admin() {
		super();
	}
	
}
