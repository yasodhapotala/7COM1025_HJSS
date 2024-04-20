import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Actions actions = new Actions();
        Scanner sc = new Scanner(System.in);
        int choice;

        System.out.println("HATFIELD JUNIOR SWIMMING SCHOOL(HJSS)");
        System.out.println("_______________________________");
        System.out.println("Welcome to HJSS");
        System.out.println();
        boolean isRunning = true;

        //______________________
        while (isRunning) {
            System.out.println("Main Menu");
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
                    actions.viewTimetable();
                    break;
                }
                case 2: {
                    System.out.println("Session change successful");
                    break;
                }
                case 3: {
                    actions.cancelBooking();
                    break;
                }
                case 4: {
                    actions.learnersReport();
                    break;
                }
                case 5: {
                    System.out.println("coach report");
                    break;
                }
                case 6: {
                    boolean isRegisteredSuccessfully = actions.registerLearner();
                    if (isRegisteredSuccessfully){
                        System.out.println("_______________________________");
                        System.out.println("Learner Registered Successfully");
                        System.out.println("_______________________________");
                    }else {
                        System.out.println("____________________________________________________________________");
                        System.out.println("Registration failed. (Make sure the learners age is between 4 and 11,\n" +
                                "and grade is between 1 and 5.)");
                        System.out.println("____________________________________________________________________");
                    }
                    break;
                }
                case 7: {
                    System.out.println("Exiting Program....");
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


