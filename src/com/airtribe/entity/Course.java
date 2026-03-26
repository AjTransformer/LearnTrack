package entity;

public class Course {
    private int id;
    private String courseName;
    private String description;
    private int durationInWeeks;
    private boolean active;

    public Course(String courseName, int id, String description, int durationInWeeks, boolean active) {
        this.courseName = courseName;
        this.id = id;
        this.description = description;
        this.durationInWeeks = durationInWeeks;
        this.active = active;
    }

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
}
