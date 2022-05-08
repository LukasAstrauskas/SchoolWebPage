package com.webApp.school.service;

import com.webApp.school.model.*;
import com.webApp.school.repository.EnrolCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolCourseService implements MyService<EnrolCourse, Long> {

    private final EnrolCourseRepository enrolCourseRepository;
    private final EnrolTaskService enrolTaskService;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrolCourseService(EnrolCourseRepository enrolCourseRepository,
                              EnrolTaskService enrolTaskService,
                              StudentService studentService,
                              CourseService courseService) {
        this.enrolCourseRepository = enrolCourseRepository;
        this.enrolTaskService = enrolTaskService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    public List<EnrolCourse> getAll() {
        return null;
    }

    @Override
    public void save(EnrolCourse enrolCourse) {
        enrolCourseRepository.save(enrolCourse);
    }


    @Override
    public EnrolCourse getById(Long aLong) {
        return enrolCourseRepository.getById(aLong);
    }

    //    unlink student from course
    @Override
    public void deleteById(Long aLong) {
//        unlink tasks from student
        enrolTaskService.unlinkTasksFromStudent(getById(aLong));
//      unlink student from course
        enrolCourseRepository.deleteById(aLong);
    }

    @Override
    public void update(EnrolCourse enrolCourse) {

    }

    public void addStudentToCourse(Long studentID, Long courseID) {
        Student student = studentService.getById(studentID);
        Course course = courseService.getById(courseID);
// making student-course conection entity
        save(new EnrolCourse(student, course));
// add course tasks to student
        enrolTaskService.addTasksToStudent(student, course.getTasks());
    }
}
