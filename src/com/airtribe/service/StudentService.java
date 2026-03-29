package service;

import entity.Course;
import entity.Student;
import repository.StudentRepository;
import util.IdGenerator;

import java.util.List;

public class StudentService {
    private static final StudentRepository studentRepo = StudentRepository.getInstance();

    public StudentService(){
        // repository is shared via singleton, nothing else to do here
    }

    public boolean addStudent(String firstName , String lastName, String email, String batch){
        int id = IdGenerator.getNextStudentId();

        // Normalize email: treat null or blank the same
        boolean hasEmail = email != null && !email.isBlank();

        Student s;
        if (!hasEmail) {
            s = new Student(id, firstName, lastName, batch, true);
        } else {
            s = new Student(id, firstName, lastName, email, batch, true);
        }

        return studentRepo.addStudentToList(s);
    }

    public static Student findStudentById(int id){
        return studentRepo.findStudentById(id);
    }

    public static void viewAll(){
        studentRepo.viewAll();
    }

    public void displayNameIdCourse(){
        studentRepo.displayNameIdCourse();
    }

    public void displayNameIdCourse(Student student){
        student.displayNameIdCourse();
    }

    public static void setActive(Student s){
        s.setActiveStatus(!s.getActiveStatus());
    }

    public List<Course> findStudentCourseList(Student student) {
        return student.getCourseListOfStudent();
    }

    public void displayStudentsNameId() {
        studentRepo.viewStudentByNameAndId();
    }
}
