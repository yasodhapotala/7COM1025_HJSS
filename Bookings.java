public class Bookings {
    private int learnerID;
    private String learnerName;
    private String week;
    private int day;
    private int sessionID;
    private int bookingID;
    private String status;
    private boolean booked;
    private boolean cancel;
    private boolean attended;
    private int grade;



    public Bookings(int learnerID, String learnerName,String week, int day, int sessionID, int bookingID, String status, int grade, boolean isBooked, boolean isCancelled, boolean isAttended){
        this.learnerID = learnerID;
        this.learnerName = learnerName;
        this.week = week;
        this.day = day;
        this.sessionID = sessionID;
        this.bookingID = bookingID;
        this.status = status;
        this.grade = grade;
        this.booked = isBooked;
        this.attended = isAttended;
        this.cancel = isCancelled;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getSessionID() {
        return sessionID;
    }

    public int getDay() {
        return day;
    }
    public String getWeek() {
        return week;
    }

    public int getLearnerID() {
        return learnerID;
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

    public boolean isBooked() {
        return booked;
    }


    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }
}
