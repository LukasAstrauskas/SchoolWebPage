package com.webApp.school.service;

import com.webApp.school.model.Course;
import com.webApp.school.model.EnrolCourse;
import com.webApp.school.model.Student;
import com.webApp.school.repository.EnrolCourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolCourseService implements MyService<EnrolCourse, Long> {

    private final EnrolCourseRepository enrolCourseRepository;
    private final StudentService studentService;
    private final CourseService courseService;

    public EnrolCourseService(EnrolCourseRepository enrolCourseRepository,
                              StudentService studentService,
                              CourseService courseService) {
        this.enrolCourseRepository = enrolCourseRepository;
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

    @Override
    public void deleteById(Long aLong) {
        enrolCourseRepository.deleteById(aLong);
    }

    @Override
    public void update(EnrolCourse enrolCourse) {

    }

    public void addStudentToCourse(Long studentID, Long courseID) {
        Student student = studentService.getById(studentID);
        Course course = courseService.getById(courseID);
        save(new EnrolCourse(student, course));
    }
}
