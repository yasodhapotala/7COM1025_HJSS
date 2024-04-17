import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class Timetable {
    private Map<String, Map<Integer, Map<String, Session>>> timetable;
    private Map<Integer, Coach> coaches;
    private Map<Integer, String> sessionDays = new HashMap<>();
    private final int LEARNERS = 4;

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
                    dayTimetable.put("2-3pm", new Session(coaches.get(1), 1, 0)); // Saturday sessions
                    dayTimetable.put("3-4pm", new Session(coaches.get(2), 2, 0));
                } else {
                    // Weekday sessions
                    dayTimetable.put("4-5pm", new Session(coaches.get(4), 3, 0));
                    dayTimetable.put("5-6pm", new Session(coaches.get(3), 4, 0));
                    dayTimetable.put("6-7pm", new Session(coaches.get(1), 5, 0));
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

    public void viewTimetableByDay(int day, String weekNumber) {
        // Display the timetable for the specified day
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
                    if (weekDay.getKey() == day) {
                        System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                        for (Map.Entry<String, Session> session : weekDay.getValue().entrySet()) {
                            int vacancies = LEARNERS - session.getValue().getEnrolledLearners();
                            System.out.println("Time: " + session.getKey() + ", Coach: " + session.getValue().getCoach().getName() + ", Grade: " + session.getValue().getGrade() + ", Vacancies: " + vacancies);
                            System.out.println("_________________________________________________________");
                        }
                    }
                }


            }
        }
    }

    public void viewTimetableByGrade(int grade, String weekNumber) {
        // Display the timetable for the specified grade level
        for (Map.Entry<String, Map<Integer, Map<String, Session>>> week : timetable.entrySet()) {
            if (week.getKey().equals("Week " + weekNumber)) {
                System.out.println("Week: " + week.getKey());
                for (Map.Entry<Integer, Map<String, Session>> weekDay : week.getValue().entrySet()) {
//                System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                    for (Map.Entry<String, Session> sessionEntry : weekDay.getValue().entrySet()) {
                        Session session = sessionEntry.getValue();
                        if (session.getGrade() == grade) {
                            System.out.println("Day: " + sessionDays.get(weekDay.getKey()));
                            int vacancies = LEARNERS - session.getEnrolledLearners(); // Calculate vacancies based on enrolled learners
                            System.out.println("Time: " + sessionEntry.getKey() + ", Coach: " + session.getCoach().getName() +
                                    ", Grade: " + session.getGrade() + ", Vacancies: " + vacancies);
                            System.out.println("_________________________________________________________");
                        }
                    }
                }


            }

        }
    }


    public boolean viewTimetableByCoach(String coachName, String weekNumber) {
        // Display the timetable for the specified coach
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
                            System.out.println("Time: " + sessionEntry.getKey() + ", Coach: " + session.getCoach().getName() +
                                    ", Grade: " + session.getGrade() + ", Vacancies: " + vacancies);
                            isInvalidCoach = false;
                            System.out.println("_________________________________________________________");
                        }

                    }
                }
            }
        }
        return isInvalidCoach;
    }

}

class Session {
    private Coach coach;
    private int grade;
    private int enrolledLearners;

    public Session(Coach coach, int grade, int enrolledLearners) {
        this.coach = coach;
        this.grade = grade;
        this.enrolledLearners = enrolledLearners;
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

}
