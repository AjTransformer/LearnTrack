package entity;

import enums.EnrollmentStatus;
import repository.EnrollmentRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Enrollment {
    private int id;
    private int studentId;
    private int courseId;
    private Date enrollmentDate;
    private EnrollmentStatus enrollmentStatus;

    public Enrollment(int enrollmentId , int studentId, int courseId, Date enrollmentDate, EnrollmentStatus enrollmentStatus) {
        this.id = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.enrollmentStatus = enrollmentStatus;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public EnrollmentStatus getEnrollmentStatus() {
        return enrollmentStatus;
    }

    public void setEnrollmentStatus(EnrollmentStatus status) {
        this.enrollmentStatus = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void displayStudentEnrollment(Student student){
        if (student == null) {
            System.out.println("Student is null. Cannot display enrollments.");
            return;
        }

        List<Enrollment> enrollmentList;
        try {
            enrollmentList = EnrollmentRepository.findByStudent(student);
        } catch (RuntimeException e) { // covers EmptyListException / EntityNotFoundException
            System.out.println(e.getMessage());
            return;
        }

        System.out.println("Student ID: " + student.getId());
        System.out.println("Enrolled Courses:");

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        for (Enrollment e : enrollmentList) {
            String dateStr = e.getEnrollmentDate() != null ? sdf.format(e.getEnrollmentDate()) : "N/A";
            System.out.println("  Course ID: " + e.getCourseId()
                    + ", Status: " + e.getEnrollmentStatus()
                    + ", Enrolled On: " + dateStr);
        }
    }
}
