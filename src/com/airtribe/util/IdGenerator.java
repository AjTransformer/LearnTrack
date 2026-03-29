package util;

public class IdGenerator {
    private static int StudentId =1,CourseId =1,EnrollmentId = 1;
    public static int getNextStudentId() {
        return StudentId++;
    }

    public static int getNextCourseId() { return CourseId++; }

    public static int getEnrollmentId() {
        return EnrollmentId++;
    }
}
