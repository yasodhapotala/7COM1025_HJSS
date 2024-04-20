
//Class Learner to maintain the learners
public class Learner {
    private String name;
    private String gender;
    private int age;
    private String emergencyContact;
    private int grade;
    private int learnerId;

    public Learner(String name, String gender, int age, String emergencyContact, int grade, int id) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.grade = grade;
        this.learnerId = id;
    }

    public int getLearnerId() {
        return learnerId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int updatedGrade) {
        this.grade = updatedGrade;
    }

    public String getName() {
        return name;
    }



}
