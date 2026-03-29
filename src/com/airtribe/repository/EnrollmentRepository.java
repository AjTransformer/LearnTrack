package repository;

import entity.Enrollment;
import entity.Student;
import enums.EnrollmentStatus;
import exception.EmptyListException;
import exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EnrollmentRepository {

    // In-memory storage of all enrollments (one per student-course pair)
    private static final List<Enrollment> enrollments = new ArrayList<>();

    /**
     * Add a new enrollment. If the same student-course pair already exists, do not duplicate it.
     */
    public static boolean add(Enrollment enrollment) {
        if (enrollment == null) {
            throw new IllegalArgumentException("Enrollment cannot be null");
        }

        boolean exists = enrollments.stream().anyMatch(e ->
                e.getStudentId() == enrollment.getStudentId() &&
                        e.getCourseId() == enrollment.getCourseId());

        if (exists) {
            System.out.println("Enrollment already exists for student ID " + enrollment.getStudentId() +
                    " and course ID " + enrollment.getCourseId());
            return false;
        }

        return enrollments.add(enrollment);
    }

    /**
     * Update the enrollment status for a given student and course.
     */
    public static void setEnrollmentStatus(Student s, int courseId, EnrollmentStatus newStatus) {
        if (s == null) {
            throw new IllegalArgumentException("Student cannot be null");
        }
        if (newStatus == null) {
            throw new IllegalArgumentException("Enrollment status cannot be null");
        }

        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getStudentId() == s.getId() && e.getCourseId() == courseId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        "Enrollment not found for student ID " + s.getId() + " and course ID " + courseId
                ));

        enrollment.setEnrollmentStatus(newStatus);
    }

    /**
     * Get the current enrollment status for a given student-course pair.
     */
    public static EnrollmentStatus getEnrollStatus(Student student, int courseId) {
        if (enrollments.isEmpty()) {
            throw new EmptyListException("No active enrollments found");
        }

        Enrollment enrollment = enrollments.stream()
                .filter(e -> e.getStudentId() == student.getId() && e.getCourseId() == courseId)
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException(
                        "Enrollment not found for student ID " + student.getId() + " and course ID " + courseId
                ));

        return enrollment.getEnrollmentStatus();
    }

    /**
     * Return all enrollments for a given student.
     */
    public static List<Enrollment> findByStudent(Student student) {
        if (enrollments.isEmpty()) {
            throw new EmptyListException("No enrollments available");
        }

        List<Enrollment> result = enrollments.stream()
                .filter(e -> e.getStudentId() == student.getId())
                .collect(Collectors.toList());

        if (result.isEmpty()) {
            throw new EntityNotFoundException("No enrollments found for student ID " + student.getId());
        }

        return result;
    }
}
