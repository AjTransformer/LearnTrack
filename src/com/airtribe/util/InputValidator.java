package util;

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
}
