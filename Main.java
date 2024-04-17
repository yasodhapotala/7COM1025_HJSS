import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Timetable timetable = new Timetable();
        ArrayList<Learner> learners = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("HATFIELD JUNIOR SWIMMING SCHOOL(HJSS)");
        System.out.println("_______________________________");
        System.out.println("Welcome to HJSS");
        System.out.println();
        boolean isRunning = true;
        //Predefine learner
        Learner learner = new Learner("Prem", "Male", 5, 1234567890, 2, 1);
        learners.add(learner);
        //______________________
        while (isRunning) {
            System.out.println("1.Book Swimming Lesson");
            System.out.println("2.Change Swimming Lesson");
            System.out.println("3.Cancel Swimming Lesson");
            System.out.println("4.Monthly Learners Report");
            System.out.println("5.Monthly Coach Report");
            System.out.println("6.Register New Learner");
            System.out.println("7.Exit");
            System.out.print("Enter your option: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
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
                                    boolean isNotSuccessful = true;
                                    while (isNotSuccessful) {
                                        System.out.println("Enter Grade To Check Availability:");
                                        int grade = sc.nextInt();
                                        if (grade == obj.getGrade() || grade == (obj.getGrade() + 1)) {
                                            timetable.viewTimetableByGrade(grade, weekNumber);
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
                                        if (day >=1 && day <=4) {
                                            timetable.viewTimetableByDay(day, weekNumber);
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
                                        isNotSuccessful = timetable.viewTimetableByCoach(coachName, weekNumber);
                                    }
                                    break;
                                }
                                default: {
                                    break;
                                }

                            }
                        } else {
                            System.out.println("Invalid Week(back to menu)!!");
                            break;
                        }
                        System.out.println();
                        System.out.println("Booking confirmed");

                    }
                    else{
                        System.out.println("Invalid Learner ID!!");
                    }
                    break;
                }
                case 2: {
                    System.out.println("Session change successful");
                    break;
                }
                case 3: {
                    System.out.println("Session cancelled");
                    break;
                }
                case 4: {
                    System.out.println("learner report");
                    break;
                }
                case 5: {
                    System.out.println("coach report");
                    break;
                }
                case 6: {
                    System.out.println("Learner registered successfully");
                    break;
                }
                case 7: {
                    isRunning = false;
                    break;
                }
                default: {
                    System.out.println("Invalid Option");
                    break;
                }

            }
        }
    }
}


