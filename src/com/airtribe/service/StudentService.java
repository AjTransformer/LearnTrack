package service;

import entity.Course;
import entity.Student;
import repository.StudentRepository;
import util.IdGenerator;

import java.util.List;


public class StudentService {
    static StudentRepository studentRepository;

    public StudentService(){
        studentRepository = studentRepository.getInstance();
    }

    public boolean addStudent(String firstName , String lastName, String email, String batch){
        int id = IdGenerator.getNextStudentId();
        Student s;
        if(email.isBlank()){
            s= new Student(id,firstName,lastName,batch,true);
        }else{
            s= new Student(id,firstName,lastName,email,batch,true);
        }
        /*When we create a student , we will add that to the enrollment list and studentList */
        EnrollmentService enrollmentService = new EnrollmentService();
        return studentRepository.addStudentToList(s) && enrollmentService.addStudentToEnrollmentList(s);
    }

    public static Student findStudentById(int id){
        return studentRepository.findStudentById(id);
    }

    public static void viewAll(){
        studentRepository.viewAll();
    }

    public void displayNameIdCourse(){
        studentRepository.displayNameIdCourse();
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
        studentRepository.viewStudentByNameAndId();
    }

    public void displayCourseList(Student student) {

    }
}
