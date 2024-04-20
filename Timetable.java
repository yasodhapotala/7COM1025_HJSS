import java.util.*;


public class Timetable {
    private Map<String, Map<Integer, Map<String, Session>>> timetable;
    public Map<Integer, Coach> coaches;
    public Map<Integer, String> sessionDays = new HashMap<>();
    private final int LEARNERS = 4;
    Scanner sc = new Scanner(System.in);
    int sessionID = 1;
    public Map<Integer, String> sessionTimingsAndId = new LinkedHashMap<>();
    int id = 1;

    public Timetable() {
        timetable = new HashMap<>();
        coaches = new HashMap<>();
        initializeCoaches();
        initializeTimetable();
    }

    private void initializeCoaches() {
        // Predefine four coaches
        coaches.put(1, new Coach("Helen"));
        coaches.put(2, new Coach("Mark"));
        coaches.put(3, new Coach("Ivana"));
        coaches.put(4, new Coach("Charles"));
    }

    private void initializeTimetable() {
        // Initialize the timetable for 4 weeks
        for (int week = 1; week <= 4; week++) {
            Map<Integer, Map<String, Session>> weekTimetable = new HashMap<>();

            // Initialize sessions for Monday, Wednesday, Thursday, and Saturday
            for (int day = 1; day <= 4; day++) {
                Map<String, Session> dayTimetable = new HashMap<>();
                if (day == 4) {
                    dayTimetable.put("2-3pm", new Session(coaches.get(1), 1, 0,sessionID));
                    sessionTimingsAndId.put(id, "2-3pm");
                    id++;
                    sessionID++;// Saturday sessions
                    dayTimetable.put("3-4pm", new Session(coaches.get(2), 2, 0,sessionID));
                    sessionTimingsAndId.put(id, "3-4pm");
                    id++;
                    sessionID++;
                } else {
                    // Weekday sessions
                    dayTimetable.put("4-5pm", new Session(coaches.get(4), 3, 0,sessionID));
                    sessionTimingsAndId.put(id, "4-5pm");
                    id++;
                    sessionID++;
                    dayTimetable.put("5-6pm", new Session(coaches.get(3), 4, 0,sessionID));
                    sessionTimingsAndId.put(id, "5-6pm");
                    id++;
                    sessionID++;
                    dayTimetable.put("6-7pm", new Session(coaches.get(1), 5, 0,sessionID));
                    sessionTimingsAndId.put(id, "6-7pm");
                    id++;
                    sessionID++;
                }
                weekTimetable.put(day, dayTimetable);
            }

            // Add the week timetable to the overall timetable
            timetable.put("Week " + week, weekTimetable);
            sessionDays.put(1, "Monday");
            sessionDays.put(2, "Wednesday");
            sessionDays.put(3, "Thursday");
            sessionDays.put(4, "Saturday");
        }
    }

    public int viewTimetableByDay(int day, String weekNumber) {
        // Display the timetable for the specified day
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
                    if (weekDay.getKey() == day) {
                        System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                        for (Map.Entry<String, Session> session : weekDay.getValue().entrySet()) {
                            int vacancies = LEARNERS - session.getValue().getEnrolledLearners();
                            System.out.println("Session ID: "+session.getValue().getSessionID()+" Time: " + session.getKey() + ", Coach: " + session.getValue().getCoach().getName() + ", Grade: " + session.getValue().getGrade() + ", Vacancies: " + vacancies);
                            System.out.println("_______________________________________________________________");
                        }
                    }
                }

            }
        }
        System.out.println("Enter Session ID: ");
        int sessionId = sc.nextInt();
        return sessionId;
    }

    public int viewTimetableByGrade(int grade, String weekNumber) {
        // Display the timetable for the specified grade level
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
                    for (Map.Entry<String, Session> sessionEntry : weekDay.getValue().entrySet()) {
                        Session session = sessionEntry.getValue();
                        if (session.getGrade() == grade) {
                            System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                            int vacancies = LEARNERS - session.getEnrolledLearners(); // Calculate vacancies based on enrolled learners
                            System.out.println("Session ID: "+session.getSessionID()+", Time: " + sessionEntry.getKey() + ", Coach: " + session.getCoach().getName() +
                                    ", Grade: " + session.getGrade() + ", Vacancies: " + vacancies);
                            System.out.println("_______________________________________________________________");
                        }
                    }
                }
            }
        }
        System.out.println("Enter Session ID: ");
        int sessionId = sc.nextInt();
        return sessionId;
    }

    // Display the timetable for the specified coach
    public int viewTimetableByCoach(String coachName, String weekNumber) {

        boolean isInvalidCoach = true;
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
                    for (Map.Entry<String, Session> sessionEntry : weekDay.getValue().entrySet()) {
                        Session session = sessionEntry.getValue();
                        if (Objects.equals(session.getCoach().getName(), coachName)) {
                            System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                            int vacancies = LEARNERS - session.getEnrolledLearners(); // Calculate vacancies based on enrolled learners
                            System.out.println("Session ID: "+session.getSessionID()+", Time: " + sessionEntry.getKey() + ", Coach: " + session.getCoach().getName() +
                                    ", Grade: " + session.getGrade() + ", Vacancies: " + vacancies);
                            System.out.println("_______________________________________________________________");
                            isInvalidCoach = false;
                        }

                    }
                }
            }
        }
        if (!isInvalidCoach){
            System.out.println("Enter Session ID: ");
            int sessionId = sc.nextInt();
            return sessionId;
        }
        return 0;
    }

    //Book a session based on the availability
    public Bookings bookSession(int learnerID, String learnerName, String weekNumber, int sessionID, int bookingID, String status, int grade){
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
                    for (Map.Entry<String, Session> sessionEntry : weekDay.getValue().entrySet()) {
                        Session session = sessionEntry.getValue();
                        if (session.getSessionID() == sessionID) {
                            System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                            int vacancies = LEARNERS - session.getEnrolledLearners(); // Calculate vacancies based on enrolled learners
                            System.out.println("Session ID: "+session.getSessionID()+", Time: " + sessionEntry.getKey() + ", Coach: " + session.getCoach().getName() +
                                    ", Grade: " + session.getGrade() + ", Vacancies: " + vacancies);
                            System.out.println("Learner ID: "+ learnerID+" Learner Name: "+learnerName+" Booking ID: "+bookingID);
                            System.out.println("_______________________________________________________________");
                            if (vacancies > 0 && (session.getGrade() == grade || session.getGrade() == grade+1)){
                                session.setEnrolledLearners(1);
                                Bookings bookings = new Bookings(learnerID, learnerName, weekNumber, weekDay.getKey(), sessionID, bookingID, status, session.getGrade());
                                return bookings;
                            }
                            else {
                                System.out.println("Check vacancy and appropriate grade before booking!!");
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public void cancelSession(int sessionID, String weekNumber) {
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
                    for (Map.Entry<String, Session> sessionEntry : weekDay.getValue().entrySet()) {
                        Session session = sessionEntry.getValue();
                        if (session.getSessionID() == sessionID) {
                            session.setEnrolledLearners(-1);
                        }
                    }
                }
            }
        }
        System.out.println("Booking Cancelled");
    }
}

class Session {
    private Coach coach;
    private int grade;
    private int enrolledLearners;
    private int sessionID;

    public Session(Coach coach, int grade, int enrolledLearners, int sessionID) {
        this.coach = coach;
        this.grade = grade;
        this.enrolledLearners = enrolledLearners;
        this.sessionID = sessionID;
    }

    public Coach getCoach() {
        return coach;
    }

    public int getGrade() {
        return grade;
    }

    public int getEnrolledLearners() {
        return enrolledLearners;
    }

    public int getSessionID(){
        return sessionID;
    }

    public void setEnrolledLearners(int newLearners){
        enrolledLearners = enrolledLearners+ newLearners;
    }


}
