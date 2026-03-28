package learntrack;

import entity.Student;
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
        map.put(1, EnrollmentUI::enrollStudent);
        map.put(2, EnrollmentUI::viewAllEnrollment);
        map.put(3, EnrollmentUI::setEnrollment);

        while (true) {
            // menu inside loop — reprints after every action
            System.out.println("\nSelect one option to perform.");
            System.out.println("1 : Enroll a student in a course");
            System.out.println("2 : View enrollments for a student");
            System.out.println("3 : Mark enrollment as completed/cancelled");
            System.out.println("0 : Go back to main menu");

            try {
                int option = Integer.parseInt(scn.nextLine());
                if (option == 0) return;
                if (!InputValidator.isValidOption(option)) {
                    throw new InvalidInputException("Invalid option selected. Please try again.");
                }
                map.get(option).run();
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void enrollStudent() {
        EnrollmentService enrollList = new EnrollmentService();
        System.out.println("List of students with their course list:");
        enrollList.displayStudentsNameIdCourse();

        // Validate student ID
        Student student;
        while (true) {
            try {
                System.out.println("Enter the Student ID you want to enroll:");
                int studentId = Integer.parseInt(scn.nextLine());
                student = InputValidator.isValidStudentId(studentId);
                if (student == null) {
                    System.out.println("Please choose a correct ID to enroll.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        // Validate course ID
        System.out.println("Enter the Course ID:");
        enrollList.displayAllEnrollCourseList();
        while (true) {
            try {
                int courseId = Integer.parseInt(scn.nextLine());
                if (!InputValidator.isValidCourseId(courseId, student)) {
                    System.out.println("Please choose a correct course ID to enroll.");
                    continue;
                }
                enrollList.addStudentToEnrollmentList(student);
                System.out.println("Successfully added student to the course.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    static void viewAllEnrollment() {
        EnrollmentService enrollList = new EnrollmentService();
        System.out.println("Enter the Student ID whose enrollments need to be checked:");
        enrollList.displayStudentsNameId();

        Student student;
        while (true) {
            try {
                int studentId = Integer.parseInt(scn.nextLine());
                student = InputValidator.isValidStudentId(studentId);
                if (student == null) {
                    System.out.println("Please choose a correct ID.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Current enrollments of the student:");
        enrollList.displayAllEnrollCourseList(student);
    }

    static void setEnrollment() {
        EnrollmentService enrollList = new EnrollmentService();
        System.out.println("Enter the Student ID whose enrollment needs to be changed:");
        enrollList.displayStudentsNameId();

        Student student;
        while (true) {
            try {
                int studentId = Integer.parseInt(scn.nextLine());
                student = InputValidator.isValidStudentId(studentId);
                if (student == null) {
                    System.out.println("Please choose a correct ID.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Validate course ID
        System.out.println("Enter the Course ID whose enrollment status needs to be changed:");
        enrollList.displayAllEnrollCourseList();
        int courseId;
        while (true) {
            try {
                courseId = Integer.parseInt(scn.nextLine());
                if (!InputValidator.isValidCourseId(courseId, student)) {
                    System.out.println("Please choose a correct course ID.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Current enrollment status of course ID " + courseId + " is "
                + enrollList.getEnrollmentStatus(student, courseId).toString());

        // Change enrollment status
        while (true) {
            // ✅ menu inside loop — reprints on invalid input
            System.out.println("Select the status to change to:");
            System.out.println("1 : Completed");
            System.out.println("2 : Cancelled");
            System.out.println("3 : Active");
            System.out.println("0 : Exit");
            try {
                int statusOption = Integer.parseInt(scn.nextLine());
                if (statusOption == 0) return;
                if (!InputValidator.isValidOptionForEnrollment(statusOption)) {
                    System.out.println("Please choose a correct option. Try again.");
                    continue;
                }
                enrollList.setEnrollmentStatus(student, courseId, statusOption);
                System.out.println("Enrollment status updated successfully.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}