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
        Map<Integer, Runnable> map = new HashMap<Integer, Runnable>();

        map.put(1, StudentUI::addStudent);
        map.put(2, StudentUI::viewStudent);
        map.put(3, StudentUI::searchStudent);
        map.put(4, StudentUI::setActive);

        int option;
        System.out.println("Select one option to perform.");
        System.out.println("1 : Add new student");
        System.out.println("2 : View all students");
        System.out.println("3 : Search student by ID");
        System.out.println("4 : Active/Deactivate a student Id");
        System.out.println("5 : Go back to main menu");

        while (true) {
            try {
                option = Integer.parseInt(scn.nextLine());
                if (option == 5) {
                    return;
                } else if (!InputValidator.isValidStudentOption(option)) {
                    throw new InvalidInputException("Invalid option selected. Please try again.");
                }
                map.get(option).run();
                break; //for valid input
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void addStudent(){
        String firstName;
        String lastName;

        // Validate first name
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
        while (true) {
            System.out.println("Enter the student last name:");
            lastName = scn.nextLine().trim();

            if (InputValidator.isValidName(lastName)) {
                break;
            } else {
                System.out.println("Invalid last name! Only letters allowed. Please try again.");
            }
        }

        //validate email
        String email;
        while (true) {
            System.out.println("Enter email (optional, press - to skip):");
            email = scn.next();
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

        //Validate batch
        System.out.println("Enter the student batch");
        String batch = scn.next();

        StudentService studentService = new StudentService();
        boolean status = studentService.addStudent(firstName,lastName,email,batch);
        if(status){
            System.out.println("Student Added Successfully.");
        }else{
            System.out.println("Error while adding student");
        }
    }

    public static void viewStudent(){
        StudentService.viewAll();
    }

    public static void searchStudent(){
        System.out.println("Enter the student id: ");
        int id  = scn.nextInt();
        try{
            Student s = StudentService.findStudentById(id);
            s.displayInfo();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void setActive(){
        System.out.println("Enter the student id: ");
        int id  = scn.nextInt();
        try{
            Student s = StudentService.findStudentById(id);
            System.out.println("Currently student status is "+getActualStatus(s.getActiveStatus()));
            while(true){
                System.out.println("You want to change this status (Y/N)");
                String ans = scn.nextLine();
                if(ans.equalsIgnoreCase("Y")) break;
                else if(ans.equalsIgnoreCase("N")) return;
                else System.out.println("Wrong input inserted..try again");
            }

            s.setActiveStatus(!s.getActiveStatus());
            System.out.println("Status changed successfully.");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getActualStatus(boolean status){
        if(status) return "Active";
        else return "InActive";
    }
}
