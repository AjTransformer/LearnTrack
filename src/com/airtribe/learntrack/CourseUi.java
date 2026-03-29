package learntrack;

import entity.Course;
import enums.CourseStatus;
import exception.InvalidInputException;
import service.CourseService;
import util.InputValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CourseUi {
    static Scanner scn = new Scanner(System.in);

    public static void courseMenu() {
        Map<Integer, Runnable> map = new HashMap<>();
        map.put(1, CourseUi::addCourse);
        map.put(2, CourseUi::viewAllCourse);
        map.put(3, CourseUi::changeActiveStatus);

        while (true) {
            // menu printed inside loop so it shows on every iteration
            System.out.println("\nSelect one option to perform.");
            System.out.println("1 : Add new course");
            System.out.println("2 : View all courses");
            System.out.println("3 : Activate/Deactivate a course");
            System.out.println("0 : Back to main menu");

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

    public static void addCourse() {
        String courseName;
        while (true) {
            System.out.println("Enter The Course Name:");
            courseName = scn.nextLine().trim();
            if (InputValidator.isValidName(courseName)) {
                break;
            } else {
                System.out.println("Invalid course name! Only letters allowed. Please try again.");
            }
        }

        System.out.println("Enter Details About The Course:");
        String description = scn.nextLine().trim();

        System.out.println("Enter the Duration Of Course In Weeks:");
        int batchDurationInWeeks;
        while (true) {
            try {
                batchDurationInWeeks = Integer.parseInt(scn.nextLine());
                if (batchDurationInWeeks <= 0) {
                    System.out.println("Duration must be greater than 0.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        CourseService courseService = new CourseService();
        boolean status = courseService.addCourse(courseName, description, batchDurationInWeeks);
        if (status) {
            System.out.println("Course Added Successfully.");
        } else {
            System.out.println("Error While Adding Course!!");
        }
    }

    public static void viewAllCourse() {
        try {
            CourseService.viewAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void changeActiveStatus() {
        try {
            System.out.println("Enter The Course Id:");
            int id = Integer.parseInt(scn.nextLine());
            CourseService courseService = new CourseService();
            Course course = courseService.findCourseById(id);

            if (course.isActive()) {
                System.out.println("Currently course status is " + CourseStatus.ACTIVATE.toString());
            } else {
                System.out.println("Currently course status is " + CourseStatus.DEACTIVATE.toString());
            }

            while (true) {
                System.out.println("You want to change this status (Y/N)");
                String ans = scn.nextLine();
                if (ans.equalsIgnoreCase("Y")) break;
                else if (ans.equalsIgnoreCase("N")) return;
                else System.out.println("Wrong Input Inserted. Try Again.");
            }

            CourseService.setActive(course);
            System.out.println("Status changed successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number for Course Id.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
