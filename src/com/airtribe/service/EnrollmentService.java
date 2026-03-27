package service;

import entity.Student;
import repository.EnrollmentRepository;

public class EnrollmentService {

    public void displayAllNotEnrolledStudent(){
        EnrollmentRepository enroll = new EnrollmentRepository();
        enroll.displayActiveStudent();
    }

    public Student findStudentByIdInActiveList(int option) {
        EnrollmentRepository enroll = new EnrollmentRepository();
        return enroll.findStudentByIdInActiveList(option);
    }
}
