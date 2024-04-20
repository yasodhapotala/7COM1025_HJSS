import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Actions {
    Scanner sc = new Scanner(System.in);
    Timetable timetable = new Timetable();
    private ArrayList<Learner> learners = new ArrayList<>();
    private ArrayList<Bookings> bookings = new ArrayList<>();
    Learner learner = null;
    private static int ID = 1;
    public static int BOOKINGID = 1;
    private final String BOOKED = "booked";
    private final String CANCELLED = "cancelled";
    private final int MINAGE = 4;
    private final int MAXAGE = 11;
    int choice;

    public Actions() {
        preDefinedLearners();
    }

    //Predefine a number of learners
    public void preDefinedLearners() {
        Learner learner = new Learner("Prem", "Male", 10, "1234567890", 2, ID);
        ID++;
        learners.add(learner);
    }

    //Register new learners
    public boolean registerLearner() {

        System.out.println("\t\tREGISTER LEARNER");
        System.out.println("\t\t________________");
        System.out.print("ENTER LEARNER NAME: ");
        String name = sc.next();
        System.out.print("ENTER LEARNER AGE: ");
        int age = sc.nextInt();
        System.out.print("ENTER LEARNER GENDER: ");
        String gender = sc.next();
        System.out.print("ENTER EMERGENCY CONTACT: ");
        String eContact = sc.next();
        System.out.print("ENTER CURRENT GRADE: ");
        int grade = sc.nextInt();

        if (isValidAge(age) && isValidGrade(grade)) {
            Learner learner = new Learner(name, gender, age, eContact, grade, ID);
            learners.add(learner);
            ID++;
            return true;
        }

        return false;
    }

    //Returns an Array list of the learners class
    public ArrayList<Learner> getLearners() {
        return learners;
    }

    public void viewTimetable() {
        System.out.print("Enter Learner ID: ");
        int id = sc.nextInt();
        Learner obj = null;
        boolean isLearnerAvailable = false;
        for (Learner learner1 : learners) {
            if (id == learner1.getLearnerId()) {
                obj = learner1;
                isLearnerAvailable = true;
                break;
            }
        }
        if (isLearnerAvailable) {
            System.out.print("Enter Week (1,2,3,4): ");
            String weekNumber = sc.next();
            System.out.println("Entered Week: " + weekNumber);
            if (weekNumber.equals("1") || weekNumber.equals("2") || weekNumber.equals("3") || weekNumber.equals("4")) {
                System.out.println("1.Grade");
                System.out.println("2.Days");
                System.out.println("3.Coach");
                System.out.println("4.Back to menu");
                System.out.print("Select View: ");
                choice = sc.nextInt();
                System.out.println();

                switch (choice) {
                    case 1: {
                        System.out.println("Grade view");
                        boolean isNotSuccessful = true;
                        while (isNotSuccessful) {
                            System.out.println("Enter Grade To Check Availability:");
                            int grade = sc.nextInt();
                            if (grade == obj.getGrade() || grade == (obj.getGrade() + 1)) {
                                int sessionID = timetable.viewTimetableByGrade(grade, weekNumber);
                                boolean isAlreadyBooked = false;
                                for(Bookings booking : bookings){
                                    if (id == booking.getLearnerID() && sessionID == booking.getSessionID()){
                                        isAlreadyBooked = true;
                                        break;
                                    }
                                }
                                if(!isAlreadyBooked){
                                    Bookings booking = timetable.bookSession(obj.getLearnerId(), obj.getName(), weekNumber, sessionID, BOOKINGID, BOOKED, obj.getGrade());
                                    if (booking != null) {
                                        for (Learner learner1 : learners) {
                                            if (id == learner1.getLearnerId()) {
                                                learner1.setGrade(booking.getGrade());
                                            }
                                        }
                                        bookings.add(booking);
                                        System.out.println("Booking Successful");
                                    }
                                    else {
                                        System.out.println("Error booking retry!!");
                                    }
                                }else{
                                    System.out.println("Already booked");
                                }
                                isNotSuccessful = false;
                            } else {
                                System.out.println("Learner can only select either current grade or one grade higher!!");
                            }
                        }
                        break;
                    }
                    case 2: {
                        System.out.println("Day view");
                        boolean isNotSuccessful = true;
                        while (isNotSuccessful) {
                            System.out.println("1. Monday");
                            System.out.println("2. Wednesday");
                            System.out.println("3. Thursday");
                            System.out.println("4. Friday");
                            System.out.print("Enter Day To Check Availability(1,2,3,4): ");
                            int day = sc.nextInt();
                            if (day >= 1 && day <= 4) {
                                int sessionID = timetable.viewTimetableByDay(day, weekNumber);
                                boolean isAlreadyBooked = false;
                                for (Bookings booking: bookings){
                                    if (id == booking.getLearnerID() && sessionID == booking.getSessionID()){
                                        isAlreadyBooked = true;
                                    }
                                }
                                if(!isAlreadyBooked){
                                    Bookings booking = timetable.bookSession(obj.getLearnerId(), obj.getName(), weekNumber, sessionID, BOOKINGID, BOOKED, obj.getGrade());
                                    if (booking != null) {
                                        for (Learner learner1 : learners) {
                                            if (id == learner1.getLearnerId()) {
                                                learner1.setGrade(booking.getGrade());
                                            }
                                        }
                                        bookings.add(booking);
                                        System.out.println("Booking Successful");
                                    }
                                    else {
                                        System.out.println("Error booking retry!!");
                                    }
                                }
                                else {
                                    System.out.println("Already Booked!!");
                                }

                                isNotSuccessful = false;
                            } else {
                                System.out.println("Invalid Day!!");
                            }
                        }
                        break;
                    }
                    case 3: {
                        System.out.println("Coach view");
                        boolean isNotSuccessful = true;
                        while (isNotSuccessful) {
                            System.out.println("1.Helen");
                            System.out.println("2.Mark");
                            System.out.println("3.Ivana");
                            System.out.println("4.Charles");
                            System.out.print("Type Coach Name: ");
                            String coachName = sc.next();
                            int sessionID = timetable.viewTimetableByCoach(coachName, weekNumber);

                            if (sessionID == 0) {
                                System.out.println("Enter a valid name!!");
                                System.out.println("____________________");
                            } else {
                                boolean isAlreadyBooked = false;
                                for (Bookings booking: bookings){
                                    if (id == booking.getLearnerID() && sessionID == booking.getLearnerID()){
                                        isAlreadyBooked = true;
                                        break;
                                    }
                                }
                                if (!isAlreadyBooked){
                                    Bookings booking = timetable.bookSession(obj.getLearnerId(), obj.getName(), weekNumber, sessionID, BOOKINGID, BOOKED, obj.getGrade());
                                    if (booking != null) {
                                        for (Learner learner1 : learners) {
                                            if (id == learner1.getLearnerId()) {
                                                learner1.setGrade(booking.getGrade());
                                            }
                                        }
                                        bookings.add(booking);
                                        System.out.println("Booking Successful");
                                    }
                                    else {
                                        System.out.println("Error booking retry!!");
                                    }
                                }

                                isNotSuccessful = false;
                            }
                        }
                        break;
                    }
                    default: {
                        break;
                    }
                }
            } else {
                System.out.println("Invalid Week(back to menu)!!");
            }
        } else {
            System.out.println("Invalid Learner ID!!");
        }

    }

    public void learnersReport() {
        //Todo: create learners report
//        for (Bookings booking: bookings){
//            System.out.println("Learner Name; "+ booking.getLearnerName()+"Session ID: "+ booking.getSessionID()+" Status "+booking.getStatus());
//        }
    }

    public void cancelBooking(){
        System.out.print("Enter Learner ID: ");
        int id = sc.nextInt();
        Learner obj = null;
        boolean isLearnerAvailable = false;
        for (Learner learner1 : learners) {
            if (id == learner1.getLearnerId()) {
                obj = learner1;
                isLearnerAvailable = true;
                break;
            }
        }
        if (isLearnerAvailable){
            boolean isBookingAvailable = false;
            for (Bookings booking: bookings){
                if (id == booking.getLearnerID()){
                    System.out.println("Booking ID: "+booking.getBookingID()+"\nSession ID: "+booking.getSessionID()+"\nSession Time: "+timetable.sessionTimingsAndId.get(booking.getSessionID())+"\nBooking Status: "+booking.getStatus());
                    isBookingAvailable = true;
                }
            }
            if (isBookingAvailable){
                System.out.println("Enter Session ID to cancel: ");
                int sessionID = sc.nextInt();
                for (Bookings booking: bookings){
                    if (booking.getSessionID() == sessionID && Objects.equals(booking.getStatus(), BOOKED)){
                        booking.setStatus(CANCELLED);
                        timetable.cancelSession(sessionID, booking.getWeek());
                        break;
                    }
                }
            }else {
                System.out.println("No Bookings Available!!");
            }
        }
        else {
            System.out.println("Invalid Lerner ID");
        }
    }
    public boolean isValidAge(int age){
        if (age>= MINAGE && age <=MAXAGE){
            return true;
        }
        return false;
    }

    public boolean isValidGrade(int grade){
        if (grade >= 1 && grade <=5){
            return  true;
        }
        return false;
    }

}
