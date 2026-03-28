package entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;



    public void setId(int id) {
        this.id = id;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDurationInWeeks(int durationInWeeks) {
        this.durationInWeeks = durationInWeeks;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getDescription() {
        return description;
    }

    public int getDurationInWeeks() {
        return durationInWeeks;
    }

    public boolean isActive() {
        return active;
    }

    public void displayInfo() {
        System.out.println("----- Course Details -----");
        System.out.println("Course ID        : " + id);
        System.out.println("Course Name      : " + courseName);
        System.out.println("Description      : " + description);
        System.out.println("Duration         : " + durationInWeeks + " week(s)");
        System.out.println("Status           : " + (active ? "Active" : "Inactive"));
        System.out.println("--------------------------");
    }

    public void displayCourseIdNameStatus() {
        System.out.println("----- Course Details -----");
        System.out.println("Course ID        : " + id);
        System.out.println("Course Name      : " + courseName);
        System.out.println("Status           : " + (active ? "Active" : "Inactive"));
        System.out.println("--------------------------");
    }
}
