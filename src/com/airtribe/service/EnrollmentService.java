package service;

import entity.Course;
import entity.Enrollment;
import entity.Student;
import enums.EnrollmentStatus;
import exception.EmptyListException;
import exception.EntityNotFoundException;
import repository.EnrollmentRepository;
import util.IdGenerator;

import java.util.Date;
import java.util.List;

public class EnrollmentService {

    public void displayStudentsNameIdCourse() {
        StudentService enroll = new StudentService();
        // Let EmptyListException/EntityNotFoundException bubble up
        enroll.displayNameIdCourse();
    }

    public void displayAllEnrollCourseList(Student student) {
        StudentService enroll = new StudentService();
        enroll.displayNameIdCourse(student);
    }

    public void displayStudentsNameId() {
        StudentService enroll = new StudentService();
        try {
            enroll.displayStudentsNameId();
        } catch (EmptyListException | EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public Student findStudentById(int option) {
        return StudentService.findStudentById(option);
    }

    /**
     * Enroll a student into a course by creating an Enrollment record
     * and attaching the Course to the student's course list.
     */
    public boolean enrollStudentInCourse(Student student, int courseId) {
        if (student == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (!student.getActiveStatus()) {
            System.out.println("Student is Inactive, Please Activate The Student Before Enrolling To Course !!");
            return false;
        }

        // Create an Enrollment record
        Enrollment enrollment = new Enrollment(
                IdGenerator.getEnrollmentId(),
                student.getId(),
                courseId,
                new Date(),
                EnrollmentStatus.ACTIVE
        );

        boolean created = EnrollmentRepository.add(enrollment);
        if (!created) {
            // Enrollment already exists for this student-course pair
            return false;
        }

        // Keep Student.courseListOfStudent in sync for displayNameIdCourse()
        CourseService courseService = new CourseService();
        Course course = courseService.findCourseById(courseId);
        if (course != null) {
            student.setCourseListOfStudent(course);
        }

        return true;
    }

    /**
     * Change the enrollment status for a given student-course pair.
     */
    public void setEnrollmentStatus(Student s, int courseId, EnrollmentStatus status) {
        EnrollmentRepository.setEnrollmentStatus(s, courseId, status);
    }

    public void displayAllEnrollCourseList() {
        CourseService course = new CourseService();
        course.displayCourses();
    }

    public boolean isExistingCourseId(int id) {
        CourseService courseService = new CourseService();
        Course course = courseService.findCourseById(id);
        return course != null;
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
        return EnrollmentRepository.getEnrollStatus(student, courseId);
    }

    public void displayStudentEnrollments(Student student) {
        List<Enrollment> enrollments = EnrollmentRepository.findByStudent(student);
        CourseService courseService = new CourseService();
        System.out.println("\n===== Student Enrollments =====");
        System.out.printf("ID           : %s%n", student.getId());
        System.out.printf("First Name   : %s%n", student.getFirstName());
        System.out.printf("Last Name    : %s%n", student.getLastName());

        if (enrollments.isEmpty()) {
            System.out.println("Courses      : No courses assigned");
        } else {
            System.out.println("Courses      :");
            for (Enrollment e : enrollments) {
                Course c = courseService.findCourseById(e.getCourseId());
                String name = (c != null) ? c.getCourseName() : ("Course#" + e.getCourseId());
                System.out.println("             " + name + " (" + e.getEnrollmentStatus() + ")");
            }
        }
        System.out.println("===============================\n");
    }
}
