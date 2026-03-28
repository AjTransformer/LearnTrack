package repository;

import entity.Student;
import enums.EnrollmentStatus;
import exception.EmptyListException;
import exception.EntityNotFoundException;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class EnrollmentRepository {
    static Map<Student , EnrollmentStatus> activeEnrollStudent = new HashMap<>();


    public static boolean add(Student s){
         try{
             activeEnrollStudent.put(s,EnrollmentStatus.ACTIVE);
             return true;
        }catch (Exception e){
            System.out.println("Error While Saving The Data In Enrollment List !!");
            return false;
        }
    }

    public static void setEnrollmentStatus(Student s, int courseId, int enrollStatus) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }

        boolean courseFound = s.getCourseListOfStudent().stream()
                .anyMatch(course -> course.getId() == courseId);

        if (!courseFound) {
            throw new EntityNotFoundException(
                    "Course with ID " + courseId + " is not assigned to student ID " + s.getId()
            );
        }

        EnrollmentStatus newStatus;
        switch (enrollStatus) {
            case 1:
                newStatus = EnrollmentStatus.COMPLETED;
                break;
            case 2:
                newStatus = EnrollmentStatus.CANCELLED;
                break;
            case 3:
                newStatus = EnrollmentStatus.ACTIVE;
                break;
            default:
                throw new IllegalArgumentException("Invalid enrollment status");
        }

        if (!activeEnrollStudent.containsKey(s)) {
            throw new EntityNotFoundException(
                    "Student with ID " + s.getId() + " not found in enrollment list"
            );
        }

        activeEnrollStudent.put(s, newStatus);
        System.out.println("Enrollment Changed Successfully !!");
    }

    //fetch student and courseId
    public static EnrollmentStatus getEnrollStatus(Student student, int courseId) {
        if (activeEnrollStudent.isEmpty()) {
            throw new EmptyListException("No active enrollments found");
        }

        boolean courseFound = student.getCourseListOfStudent().stream()
                .anyMatch(course -> course.getId() == courseId);

        if (!courseFound) {
            throw new EntityNotFoundException(
                    "Course with ID " + courseId + " is not assigned to student ID " + student.getId()
            );
        }

        EnrollmentStatus status = activeEnrollStudent.get(student);

        if (status == null) {
            throw new EntityNotFoundException(
                    "Student with ID " + student.getId() + " not found in active enrollment list"
            );
        }

        return status;
    }

    public Map.Entry<Student, EnrollmentStatus> findStudentByIdInActiveList(int id){
        if (activeEnrollStudent.isEmpty()) {
            throw new EmptyListException("No Students Available To Display In Active List");
        }

        return activeEnrollStudent.entrySet().stream()
                .filter(entry -> entry.getKey().getId() == id)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Student with ID " + id + " not found"));
    }

    public void displayActiveStudent() {
        if (activeEnrollStudent.isEmpty()) {
            throw new EmptyListException("No Students Available To Display In Active List");
        }

        for (Map.Entry<Student, EnrollmentStatus> entry : activeEnrollStudent.entrySet()) {
            Student student = entry.getKey();
            EnrollmentStatus status = entry.getValue();

            System.out.println(
                    "ID: " + student.getId() +
                            ", First Name: " + student.getFirstName() +
                            ", Last Name: " + student.getLastName() +
                            ", Enrollment Status: " + status
            );
        }
    }
}
