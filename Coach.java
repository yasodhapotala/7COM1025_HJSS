import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Coach {
    private String name;
    ArrayList<Integer> rating = new ArrayList<>();
    private int averageRating;
    private Map<Integer, String> remarks = new HashMap<>();


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
    public void getAverageRating(){
        int sum = 0;
        int len = rating.size();
        for (int i =0; i< rating.size(); i++){
            sum+=rating.get(i);
        }
        averageRating = sum / len;

    }

    public void coachReport(){
        getAverageRating();
        System.out.println("COACH REPORT");
        System.out.println("____________");
        System.out.println("Name: "+ name);
        System.out.println("Rating: "+ averageRating);
        System.out.println("Remarks: "+ remarks.get(averageRating));
    }
}
