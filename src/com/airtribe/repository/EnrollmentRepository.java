package repository;

import entity.Student;
import exception.EmptyListException;
import exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentRepository {
    List<Student> activeEnrollStudent = new ArrayList<>();
    List<Student> completedEnrollStudent = new ArrayList<>();
    List<Student> cancelEnrollStudent = new ArrayList<>();

    public void addToActiveList(Student s){
        activeEnrollStudent.add(s);
    }

    public void addToEnrollList(Student s){
        completedEnrollStudent.add(s);
    }

    public void addToCancelList(Student s){
        cancelEnrollStudent.add(s);
    }

    public List<Student> getActiveList(){
        return activeEnrollStudent;
    }

    public List<Student> getCompletedEnrollStudentList(){
        return completedEnrollStudent;
    }

    public List<Student> getCancelEnrollStudentList(){
        return cancelEnrollStudent;
    }

    public Student findStudentByIdInActiveList(int id){
        if (activeEnrollStudent.isEmpty()) {
            throw new EmptyListException("No Students Available To Display In Active List");
        }
        return activeEnrollStudent.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() ->new EntityNotFoundException("Student with ID " + id + " not found"));
    }

    public void displayActiveStudent(){

    }
}
