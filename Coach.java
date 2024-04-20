import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Coach {
    private String name;
    ArrayList<Integer> rating = new ArrayList<>();
    private int averageRating;
    private Map<Integer, String> remarks = new HashMap<>();
    ArrayList<String> review = new ArrayList<>();


    public Coach(String name) {
        this.name = name;
        remarks.put(1, "Very dissatisfied");
        remarks.put(2, "Dissatisfied");
        remarks.put(3, "Ok");
        remarks.put(4, "Satisfied");
        remarks.put(5, "Very Satisfied");
        setRating(1);
    }

    public String getName() {
        return name;
    }

    public void setRating(int rating) {
        this.rating.add(rating);
    }
    //Calculates the average rating
    public int getAverageRating(){
        int sum = 0;
        int len = rating.size();
        for (int i =0; i< rating.size(); i++){
            sum+=rating.get(i);
        }
        averageRating = sum / len;
        return averageRating;
    }
    public String  getRemarks(int averageRating){
        return remarks.get(averageRating);
    }

    public void setReview(String review){
        this.review.add(review);
    }


}
