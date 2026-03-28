package repository;

import entity.Course;
import entity.Student;
import exception.EmptyListException;
import exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CourseRepository {
    private static List<Course> courseList = new ArrayList<>();
    private static CourseRepository instance = null;
    private CourseRepository(){}

    public static CourseRepository getInstance(){
        if(instance==null){
            instance = new CourseRepository();
        }
        return instance;
    }

    public static boolean addCourseToList(Course course) {
        return courseList.add(course);
    }

    public static void viewAllCourse() {
        if (courseList.isEmpty()) {
            throw new EmptyListException("No Course Available To Display");
        }
        courseList.forEach(Course::displayInfo);
    }

    public static void displayCourseIdNameStatus() {
        if (courseList.isEmpty()) {
            throw new EmptyListException("No Course Available To Display");
        }
        courseList.forEach(Course::displayCourseIdNameStatus);
    }

    public Course findCourseById(int id) {
        if (courseList.isEmpty()) {
            throw new EmptyListException("No course available to display");
        }
        return courseList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() ->new EntityNotFoundException("Course With ID " + id + " Not Found"));
    }
}
