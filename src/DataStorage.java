import java.util.ArrayList;

public interface DataStorage {
	
	void createUser(String user_id, String password, String tag1, String tag2);
	String Login(String user_id, String password);
	void createAttraction(String title, String city, double score, String description, String tag);
	void displayattraction();
	void approveOrReject(String title);
	void searchAttraction(String attractionTagOrCity);
	void writeReview(String title, String review, String user_id, double rating);
	void addFavorites(String user_id, String title);
	void topThreeTrendingPlaces(String user_id);
	void viewFaq(String attraction);
	void postQuestion(String attraction, String user_id, String question);
	void postAnswer(String q_id, String answer);
	void faqNotification(String user_id);
	void topAttractions(String title);
}
