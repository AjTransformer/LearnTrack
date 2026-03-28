package util;

import entity.Course;
import entity.Student;
import enums.EnrollmentStatus;
import service.EnrollmentService;
import service.StudentService;

import java.util.Map;

public class InputValidator {

    public static boolean isValidMainOption(int option) {
        return option>=1 && option <=3;
    }

    public static boolean isValidStudentOption(int option) {
        return option>=1 && option<=4;
    }

    public static boolean isValidEmail(String email) {
        return email.contains("@") && email.contains(".");
    }

    public static boolean isValidName(String name) {
        // Only letters (uppercase/lowercase), no digits, no special characters
        return name != null && name.matches("[a-zA-Z]+");
    }

    public static boolean isValidOption(int option) {
        return option >=1 && option <=3;
    }

    public static Student isValidStudentId(int option) {
        EnrollmentService enroll = new EnrollmentService();
        return enroll.findStudentById(option);
    }

    public static boolean isValidCourseId(int option, Student student) {
        EnrollmentService enroll = new EnrollmentService();
        return enroll.findCourseByIdInCourseList(option) && !enroll.courseAlreadyAssigned(option , student);
    }

    public static boolean isValidOptionForEnrollment(int option) {
        return option >=1 && option <=4;
    }
}
