package entity;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person{
    private List<Course> courseListOfStudent ;

    public Student(int id , String firstName, String lastName, String email, String batch , boolean active){
        super(id,firstName,lastName,email,batch,active);
        courseListOfStudent = new ArrayList<>();
    }

    public Student(int id , String firstName, String lastName, String batch , boolean active){
        super(id,firstName,lastName,batch,active);
        courseListOfStudent = new ArrayList<>();
    }

    public int getId(){
        return id;
    }

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }

    public String getEmail(){
        return email;
    }

    public String getBatchName(){
        return batch;
    }

    public boolean getActiveStatus(){
        return active;
    }

    public void setActiveStatus(boolean active){
        this.active = active;
    }

    public List<Course> getCourseListOfStudent(){
        return courseListOfStudent;
    }

    public void setCourseListOfStudent(Course course){
        courseListOfStudent.add(course);
    }

    @Override
    public void displayInfo() {
        System.out.println("\n===== Student Information =====");
        System.out.printf("ID           : %s%n", getId());
        System.out.printf("First Name   : %s%n", getFirstName());
        System.out.printf("Last Name    : %s%n", getLastName());
        System.out.printf("Email ID     : %s%n", getEmail());
        System.out.printf("Batch Name   : %s%n", getBatchName());
        System.out.printf("Status       : %s%n", getActiveStatus() ? "Active" : "Inactive");
        System.out.println("===============================\n");
    }

    public void displayNameAndID(){
        System.out.println("\n===== Student Information =====");
        System.out.printf("ID           : %s%n", getId());
        System.out.printf("First Name   : %s%n", getFirstName());
        System.out.printf("Last Name    : %s%n", getLastName());
        System.out.println("===============================\n");
    }

    public void displayNameIdCourse(){
        System.out.println("\n===== Student Information =====");
        System.out.printf("ID           : %s%n", getId());
        System.out.printf("First Name   : %s%n", getFirstName());
        System.out.printf("Last Name    : %s%n", getLastName());

        if (courseListOfStudent == null || courseListOfStudent.isEmpty()) {
            System.out.printf("Courses      : No courses assigned%n");
        } else {
            System.out.print("Courses      : ");
            for (Course course : courseListOfStudent) {
                System.out.print(course.getCourseName() + " ");
            }
            System.out.println();
        }

        System.out.println("===============================\n");
    }

}
