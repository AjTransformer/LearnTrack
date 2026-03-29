package repository;

import entity.Student;
import exception.EmptyListException;
import exception.EntityNotFoundException;

import java.util.*;

public class StudentRepository {
    private List<Student> students = new ArrayList<>();

    // singleton support
    private static StudentRepository instance;

    private StudentRepository() {}

    public static StudentRepository getInstance() {
        if (instance == null) {
            instance = new StudentRepository();
        }
        return instance;
    }

    public boolean addStudentToList(Student student){
        try{
            return students.add(student);
        }catch (Exception e){
            System.out.println("Error while saving the data");
            return false;
        }
    }

    public Student findStudentById(int id){
        if (students.isEmpty()) {
            throw new EmptyListException("No students available to display");
        }
        return students.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() ->new EntityNotFoundException("Student with ID " + id + " not found"));
    }

    public void viewAll(){
        if (students.isEmpty()) {
            throw new EmptyListException("No students available to display");
        }
        students.forEach(Student::displayInfo);
    }

    public void viewStudentByNameAndId(){
        if (students.isEmpty()) {
            throw new EmptyListException("No students available to display");
        }
        students.forEach(Student::displayNameAndID);
    }

    public void displayNameIdCourse(){
        if (students.isEmpty()) {
            throw new EmptyListException("No students available to display");
        }
        students.forEach(Student::displayNameIdCourse);
    }

    public void setActive(Student s,boolean status){
        if (students.isEmpty()) {
            throw new EmptyListException("No students available to change status, please add student before changing status.");
        }
        s.setActiveStatus(status);
    }
}
