package learntrack;

import entity.Student;
import enums.EnrollmentStatus;
import exception.EmptyListException;
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
            } catch (EmptyListException e) {
                // Handles case like "No students available to display" gracefully
                System.out.println(e.getMessage());
                // After showing message, loop will re-display enrollment menu instead of terminating program
            }
        }
    }

    static void enrollStudent() {
        EnrollmentService enrollList = new EnrollmentService();
        // Validate student ID
        Student student;
        while (true) {
            try {
                System.out.println("List of students with their course list:");
                enrollList.displayStudentsNameIdCourse();
                System.out.println("Enter the Student ID you want to enroll:");
                int studentId = Integer.parseInt(scn.nextLine());
                if(studentId==0){return;} //For exiting the loop in between.
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
            } catch (EmptyListException e) {
                System.out.println(e.getMessage());
                return; // no students to enroll, go back to enrollment menu
            }
        }

        // Validate course ID
        while (true) {
            try {
                System.out.println("Enter the Course ID:");
                enrollList.displayAllEnrollCourseList();
                int courseId = Integer.parseInt(scn.nextLine());
                if (!InputValidator.isValidCourseId(courseId, student)) {
                    System.out.println("Please choose a correct course ID to enroll.");
                    continue;
                }

                boolean created = enrollList.enrollStudentInCourse(student, courseId);
                if (!created) {
                    System.out.println("Student is already enrolled in this course.");
                } else {
                    System.out.println("Successfully added student to the course.");
                }
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
        Student student = null;
        while (true) {
            try {
                System.out.println("Enter the Student ID whose enrollments need to be checked:");
                int studentId = Integer.parseInt(scn.nextLine());
                if (studentId == 0) { return; } // exit to enrollment menu
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

        // Show enrollments using EnrollmentRepository-backed data
        enrollList.displayStudentEnrollments(student);
    }

    static void setEnrollment() {
        EnrollmentService enrollList = new EnrollmentService();
        Student student = null;
        while (true) {
            try {
                System.out.println("Enter the Student ID whose enrollment needs to be changed:");
                enrollList.displayStudentsNameId();
                int studentId = Integer.parseInt(scn.nextLine());
                if(studentId==0){return;}
                student = InputValidator.isValidStudentId(studentId);
                break;
            }catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        // Validate course ID
        int courseId;
        while (true) {
            try {
                System.out.println("Enter the Course ID whose enrollment status needs to be changed:");
                enrollList.displayAllEnrollCourseList();
                courseId = Integer.parseInt(scn.nextLine());
                if(courseId==0)return;
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

                EnrollmentStatus newStatus;
                switch (statusOption) {
                    case 1:
                        newStatus = EnrollmentStatus.COMPLETED;
                        break;
                    case 2:
                        newStatus = EnrollmentStatus.CANCELLED;
                        break;
                    case 3:
                        newStatus = EnrollmentStatus.ACTIVE;
                        break;
                    default:
                        System.out.println("Invalid option. Try again.");
                        continue;
                }

                enrollList.setEnrollmentStatus(student, courseId, newStatus);
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