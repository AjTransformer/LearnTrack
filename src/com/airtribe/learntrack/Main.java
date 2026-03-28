package learntrack;

import exception.InvalidInputException;
import util.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Welcome ...");

        Map<Integer, Runnable> mainMenu = new HashMap<>();
        mainMenu.put(1, StudentUI::studentMenu);
        mainMenu.put(2, CourseUi::courseMenu);
        mainMenu.put(3, EnrollmentUI::enrollmentMenu);

        while (true) {  // <-- outer loop keeps returning to main menu
            System.out.println("\nSelect one option to perform.");
            System.out.println("1 : Student Management");
            System.out.println("2 : Course Management");
            System.out.println("3 : Enrollment Management");
            System.out.println("0 : Exit");  // <-- always give a way out

            int option;
            while (true) {
                try {
                    option = Integer.parseInt(scn.nextLine());
                    if (option == 0) {
                        System.out.println("Goodbye!");
                        scn.close();
                        return; // clean exit
                    }
                    if (!InputValidator.isValidMainOption(option)) {
                        throw new InvalidInputException("Invalid option. Please try again.");
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                } catch (InvalidInputException e) {
                    System.out.println(e.getMessage());
                }
            }

            mainMenu.get(option).run(); // after this returns, outer while loops back
        }
    }
}