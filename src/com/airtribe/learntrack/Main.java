package learntrack;

import exception.InvalidInputException;
import util.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
//    public static void main(String[] args) {
//        System.out.println("Welcome ...");
//        Scanner scn = new Scanner(System.in);
//        System.out.println("Select one option to perform.");
//        System.out.println("1 : Student Management");
//        System.out.println("2 : Course Management");
//        System.out.println("3 : Enrollment Management");
//        int option = scn.nextInt();
//
//        switch (option){
//            case 1:
//                System.out.println("Select one option to perform.");
//                System.out.println("1 : Add new student");
//                System.out.println("2 : View all students");
//                System.out.println("3 : Search student by ID");
//                System.out.println("4 : Deactivate a student");
//                break;
//            case 2:
//                System.out.println("Select one option to perform.");
//                System.out.println("1 : Add new course");
//                System.out.println("2 : View all courses");
//                System.out.println("3 : Activate a course");
//                System.out.println("3 : Deactivate a course");
//                break;
//            case 3:
//                System.out.println("Select one option to perform.");
//                System.out.println("1 : Enroll a student in a course");
//                System.out.println("2 : View enrollments for a student");
//                System.out.println("3 : Mark enrollment as completed/cancalled");
//                break;
//            default:
//                System.out.println("Invalid option selected, please choose the correct option");
//        }
//    }
    static Scanner scn = new Scanner(System.in);
    public static void main(String[] args){
        System.out.println("Welcome ...");

        Map<Integer, Runnable> mainMenu = new HashMap<>();
        mainMenu.put(1, StudentUI::studentMenu);
        mainMenu.put(2, CourseUi::courseMenu);
        mainMenu.put(3, EnrollmentUI::enrollmentMenu);

        System.out.println("Select one option to perform.");
        System.out.println("1 : Student Management");
        System.out.println("2 : Course Management");
        System.out.println("3 : Enrollment Management");
        int option;
        while(true){
            try {
                option = Integer.parseInt(scn.nextLine());

                if (!InputValidator.isValidMainOption(option)) {
                    throw new InvalidInputException("Invalid option selected. Please try again.");
                }

                break; // valid input

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
        mainMenu.get(option).run();
        scn.close();
    }
}