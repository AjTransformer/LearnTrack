package util;

public class IdGenerator {
    private static int StudentId =1;
    public static int getNextStudentId() {
        return StudentId++;
    }
}
