package learntrack;

import entity.Student;
import exception.InvalidInputException;
import service.StudentService;
import util.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentUI {
    static Scanner scn = new Scanner(System.in);

    public static void studentMenu() {
        Map<Integer, Runnable> map = new HashMap<>();
        map.put(1, StudentUI::addStudent);
        map.put(2, StudentUI::viewStudent);
        map.put(3, StudentUI::searchStudent);
        map.put(4, StudentUI::setActive);

        while (true) {
            // menu inside loop — reprints after every action
            System.out.println("\nSelect one option to perform.");
            System.out.println("1 : Add new student");
            System.out.println("2 : View all students");
            System.out.println("3 : Search student by ID");
            System.out.println("4 : Activate/Deactivate a student");
            System.out.println("0 : Go back to main menu");

            try {
                int option = Integer.parseInt(scn.nextLine());
                if (option == 0) return;
                if (!InputValidator.isValidStudentOption(option)) {
                    throw new InvalidInputException("Invalid option selected. Please try again.");
                }
                map.get(option).run(); // no break — returns to menu after action
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addStudent() {
        // Validate first name
        String firstName;
        while (true) {
            System.out.println("Enter the student first name:");
            firstName = scn.nextLine().trim();
            if (InputValidator.isValidName(firstName)) {
                break;
            } else {
                System.out.println("Invalid first name! Only letters allowed. Please try again.");
            }
        }

        // Validate last name
        String lastName;
        while (true) {
            System.out.println("Enter the student last name:");
            lastName = scn.nextLine().trim();
            if (InputValidator.isValidName(lastName)) {
                break;
            } else {
                System.out.println("Invalid last name! Only letters allowed. Please try again.");
            }
        }

        // Validate email
        String email;
        while (true) {
            System.out.println("Enter email (optional, press - to skip):");
            email = scn.nextLine().trim();
            if (email.equals("-")) {
                email = null;
                break;
            }
            if (InputValidator.isValidEmail(email)) {
                break;
            } else {
                System.out.println("Invalid email format. Try again.");
            }
        }

        // Validate batch
        System.out.println("Enter the student batch:");
        String batch = scn.nextLine().trim();

        StudentService studentService = new StudentService();
        boolean status = studentService.addStudent(firstName, lastName, email, batch);
        if (status) {
            System.out.println("Student Added Successfully.");
        } else {
            System.out.println("Error while adding student.");
        }
    }

    public static void viewStudent() {
        try {
            StudentService.viewAll(); //
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void searchStudent() {
        try {
            System.out.println("Enter the student id:");
            int id = Integer.parseInt(scn.nextLine());
            if(id==0)return;
            Student s = StudentService.findStudentById(id);
            s.displayInfo();
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for Student Id.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setActive() {
        try {
            System.out.println("Enter the student id:");
            int id = Integer.parseInt(scn.nextLine());
            Student s = StudentService.findStudentById(id);

            while (true) {
                System.out.println("Currently student status is " + getActualStatus(s.getActiveStatus()));
                System.out.println("You want to change this status (Y/N)");
                String ans = scn.nextLine();
                if(ans.equals("0"))return;
                else if (ans.equalsIgnoreCase("Y")) break;
                else if (ans.equalsIgnoreCase("N")) return;
                else System.out.println("Wrong input inserted. Try again.");
            }
            StudentService.setActive(s);
            System.out.println("Status changed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for Student Id.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static String getActualStatus(boolean status) {
        return status ? "Active" : "Inactive";
    }
}