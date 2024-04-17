public class Learner {
    private String name;
    private String gender;
    private int age;
    private int emergencyContact;
    private int grade;
    private int learnerId;
    private final int MINAGE = 4;
    private final int MAXAGE = 11;

    public Learner(String name, String gender, int age, int emergencyContact, int grade, int id) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.emergencyContact = emergencyContact;
        this.grade = grade;
        this.learnerId = id;
    }

    public int getMinAge() {
        return MINAGE;
    }

    public int getMaxAge() {
        return MAXAGE;
    }

    public int getLearnerId() {
        return learnerId;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
