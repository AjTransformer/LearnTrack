package service;

import entity.Course;
import entity.Student;
import enums.EnrollmentStatus;
import repository.EnrollmentRepository;

import java.util.List;
import java.util.Map;

public class EnrollmentService {

    public void displayStudentsNameIdCourse(){
        StudentService enroll = new StudentService();
        enroll.displayNameIdCourse();
    }

    public void displayAllEnrollCourseList(Student student){
        StudentService enroll = new StudentService();
        enroll.displayNameIdCourse(student);
    }

    public void displayStudentsNameId(){
        StudentService enroll = new StudentService();
        enroll.displayStudentsNameId();
    }

    public Student findStudentById(int option) {
        return StudentService.findStudentById(option);
    }

    public boolean addStudentToEnrollmentList(Student s){
        return EnrollmentRepository.add(s);
    }

    public static void setEnrollmentStatus(Student s, int courseId , int enrollStatus){
        EnrollmentRepository.setEnrollmentStatus(s,courseId , enrollStatus);
    }

    public void displayAllEnrollCourseList() {
        CourseService course = new CourseService();
        course.displayCourses();
    }

    public boolean findCourseByIdInCourseList(int option) {
        Course course= CourseService.findCourseById(option);
        if(course!=null)return true;
        return false;
    }

    public boolean courseAlreadyAssigned(int courseId, Student student) {
        StudentService checkCourseList = new StudentService();
        List<Course> courseList = checkCourseList.findStudentCourseList(student);

        if (courseList == null || courseList.isEmpty()) {
            return false;
        }

        for (Course course : courseList) {
            if (course.getId() == courseId) {
                System.out.println("Course Is Already Assigned To This Student !!");
                return true;
            }
        }
        return false;
    }

    public EnrollmentStatus getEnrollmentStatus(Student student, int courseId) {
        return EnrollmentRepository.getEnrollStatus(student,courseId);
    }
}
