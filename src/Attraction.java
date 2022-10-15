import java.util.Scanner;

public class Attraction {
	
	public String Attraction;
	public String title;
	public String city;
	public String description;
	public String tag;
	public double score;
	
	DataStorage data = new SQL_Database();
	
	public Attraction(String title, String city, String description, String tag, double score) {
		super();
		this.title = title;
		this.city = city;
		this.description = description;
		this.tag = tag;
		this.score = score;
	}

	public void createAttraction ()
	{
        String[] tags = new String[6];
		
		tags[0] = "History buff";
		tags[1] = "Shopping Fanatic";
		tags[2] = "Beach Goer";
		tags[3] = "Urban Explorer";
		tags[4] = "Nature Lover";
		tags[5] = "Family Vacationer";
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter the name of the Attraction");
		title = input.nextLine();
		System.out.println("Enter the name of the city");
		city = input.nextLine();
		System.out.println("Describe the attraction");
		description = input.nextLine();
		System.out.println("Enter tag from the following");
		System.out.println("1 - History Buff, 2 - Shopping Fanatic, 3 - Beach Goer,"
				+ " 4 - Urban Explorer, 5 - Nature Lover, 6 - Family Vacationer");
		int t1 = input.nextInt()-1;
		tag = tags[t1];
		
		score = 0;
		
		data.createAttraction(title,city,score,description,tag);
	}
	
	public boolean searchAttraction(String attractionTagOrCity) {
		if(!(attractionTagOrCity.equals("0") || attractionTagOrCity.equals(" ") ))
		{
			data.searchAttraction(attractionTagOrCity);
			return true;
		}
		return false;
		
	}

	public void viewAttraction(String user_id, String title) 
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Select from the menu below");
		System.out.println("1: to add this attraction to your favorites list");
		System.out.println("2: View the Q&A section for this attraction");
		System.out.println("3: Write a review for this attraction");
		System.out.println("0: to Exit");
		String selection = input.nextLine();
		
		while(!selection.equals("0"))
		{
			if(selection.equals("3"))
			{
				
			System.out.println("Please write your review");
			String review = input.nextLine();
			System.out.println("Please enter the rating");
			double rating = input.nextDouble();
			data.writeReview(title,review,user_id,rating);
			}
			else if(selection.equals("1"))
			{
				data.addFavorites(user_id,title);
			}
			
			else if(selection.equals("2"))
			{
				data.viewFaq(title);
				
				System.out.println(" 1: post a Question \n 2: Answer a FAQ");
				String reply = input.nextLine();
				if(reply.equals("1"))
				{
					System.out.println("Please enter your question for the attraction: " +title);
					String question = input.nextLine();
					
					data.postQuestion(title, user_id, question);
				}
				else if(reply.equals("2"))
				{
					System.out.println("Please enter question id for which you want to answer: ");
					String q_Id = input.nextLine();
					System.out.println("Please enter your answer");
					String answer = input.nextLine();
					
					data.postAnswer(q_Id, answer);
				}
				
			}
			
			System.out.println("Select from the menu below");
			System.out.println("1: to add this attraction to your favorites list");
			System.out.println("2: View the Q&A section for this attraction");
			System.out.println("3: Write a review for this attraction");
			System.out.println("0: to Exit");
			Scanner sc = new Scanner(System.in);
			selection = sc.nextLine();
			
		}
			
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Attraction (DataStorage d)
	{
		data = d;
	}
	
	public Attraction ()
	{
		
	}
	

}
