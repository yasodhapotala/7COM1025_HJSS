public class Bookings {
    private int learnerID;
    private String learnerName;
    private String week;
    private int day;
    private int sessionID;
    private int bookingID;
    private String status;
    private int grade;



    public Bookings(int learnerID, String learnerName,String week, int day, int sessionID, int bookingID, String status, int grade){
        this.learnerID = learnerID;
        this.learnerName = learnerName;
        this.week = week;
        this.day = day;
        this.sessionID = sessionID;
        this.bookingID = bookingID;
        this.status = status;
        this.grade = grade;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public void setSessionID(int sessionID) {
        this.sessionID = sessionID;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getLearnerName() {
        return learnerName;
    }

    public void setLearnerName(String learnerName) {
        this.learnerName = learnerName;
    }

    public int getLearnerID() {
        return learnerID;
    }

    public void setLearnerID(int learnerID) {
        this.learnerID = learnerID;
    }

    public int getGrade() {
        return grade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
