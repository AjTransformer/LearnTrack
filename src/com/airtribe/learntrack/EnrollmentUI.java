package learntrack;

import exception.InvalidInputException;
import service.EnrollmentService;
import util.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class EnrollmentUI {
    static Scanner scn = new Scanner(System.in);

    static void enrollmentMenu() {
        Map<Integer, Runnable> map = new HashMap<>();

        map.put(1, () -> enrollStudent());
//        map.put(2, () -> viewAllEnrollment());
//        map.put(3, () -> setEnrollment());

        int option;

        System.out.println("Select one option to perform.");
        System.out.println("1 : Enroll a student in a course");
        System.out.println("2 : View enrollments for a student");
        System.out.println("3 : Mark enrollment as completed/cancelled");

        while (true) {
            try {
                option = Integer.parseInt(scn.nextLine());

                if (!InputValidator.isValidOption(option)) {
                    throw new InvalidInputException("Invalid option selected. Please try again.");
                }

                Runnable action = map.get(option);

                if (action != null) {
                    action.run();
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void enrollStudent(){
        System.out.println("Enter The Student ID,Whom You Want To Enroll");
        EnrollmentService enrollList = new EnrollmentService();
        enrollList.displayAllNotEnrolledStudent();
        int option;
        while (true){
            try{
                option = scn.nextInt();

                if(!InputValidator.isValidStudentId(option)){
                    throw new RuntimeException("Please , choose the correct ID to enroll");
                }
                break;
            }catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
