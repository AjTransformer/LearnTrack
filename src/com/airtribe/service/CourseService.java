package service;

import entity.Course;
import enums.CourseStatus;
import repository.CourseRepository;
import util.IdGenerator;

public class CourseService {
    private static final CourseRepository courseRepo = CourseRepository.getInstance();

    public boolean addCourse(String courseName, String description, int batchDurationInWeeks){
        int id = IdGenerator.getNextCourseId();
        boolean status = getStatusOfCourse(CourseStatus.ACTIVATE);
        Course course = new Course();
        course.setId(id);
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(batchDurationInWeeks);
        course.setActive(status);

        return courseRepo.addCourseToList(course);
    }

    public static void setActive(Course course){
        course.setActive(!course.isActive());
    }

    private static boolean getStatusOfCourse(CourseStatus courseStatus) {
        return courseStatus == CourseStatus.ACTIVATE;
    }

    public Course findCourseById(int id){
        return courseRepo.findCourseById(id);
    }

    public static void viewAll(){
        courseRepo.viewAllCourse();
    }

    public void displayCourses() {
        courseRepo.displayCourseIdNameStatus();
    }
}
