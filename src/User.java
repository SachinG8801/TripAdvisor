import java.sql.Array;
import java.util.Scanner;

public abstract class User {
	
	private DataStorage data;

	
	
	private String user_id;
	private String password;
	private String tag1;
	private String tag2;
	private String type;
	
	public User(String id,String pwd,String t1,String t2,String typ)
	{
		user_id = id;
		password = pwd;
		tag1 = t1;
		tag2 = t2;
		type = "regular_user";
		
	}
	
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public User(String id, String pwd)
	{
		user_id = id;
		password = pwd;
	}
	
	public String getUser_id() {
		return user_id;
	}
	
	public User(DataStorage d)
	{
		data = d;
	}
	
	public User() {
		
	}
	
	public DataStorage getData() {
		return data;
	}

	public void setData(DataStorage data) {
		this.data = data;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTag1() {
		return tag1;
	}

	public void setTag1(String tag1) {
		this.tag1 = tag1;
	}

	public String getTag2() {
		return tag2;
	}

	public void setTag2(String tag2) {
		this.tag2 = tag2;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

}
