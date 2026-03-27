package service;

import entity.Course;
import enums.CourseStatus;
import repository.CourseRepository;
import util.IdGenerator;

public class CourseService {
    static CourseRepository courseRepository;

    public CourseService(){
        courseRepository = courseRepository.getInstance();
    }

    public boolean addCourse(String courseName, String description, int batchDurationInWeeks){
        int id = IdGenerator.getNextCourseId();
        boolean status = getStatusOfCourse(CourseStatus.ACTIVATE);
       Course course = new Course();
        course.setId(id);
        course.setCourseName(courseName);
        course.setDescription(description);
        course.setDurationInWeeks(batchDurationInWeeks);
        course.setActive(status);

        return CourseRepository.addCourseToList(course);
    }

    public static void setActive(Course course){
        course.setActive(!course.isActive());
    }

    private static boolean getStatusOfCourse(CourseStatus courseStatus) {
        if(courseStatus == CourseStatus.ACTIVATE){
            return true;
        }
        return false;
    }

    public static Course findCourseById(int id){
        return courseRepository.findCourseById(id);
    }

    public static void viewAll(){
        CourseRepository.viewAllCourse();
    }
}
