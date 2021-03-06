package com.webApp.school.service;

import com.webApp.school.model.Course;
import com.webApp.school.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService implements MyService<Course, Long> {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        if (course.getTeacher() == null || course.getTeacher().getId() == 0) {
            course.setTeacher(null);
        }
        if (course.getId() != null) {
            update(course);
            return course;
        } else {
            return courseRepository.save(course);
        }
    }

    @Override
    public Course getById(Long aLong) {
        return courseRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        courseRepository.deleteById(aLong);
    }

    @Override
    public void update(Course courseNewInfo) {
        Course toUpdate = getById(courseNewInfo.getId());
        toUpdate.setName(courseNewInfo.getName());
        toUpdate.setDescription(courseNewInfo.getDescription());
        toUpdate.setTeacher(courseNewInfo.getTeacher());
        toUpdate.setCredits(courseNewInfo.getCredits());
        toUpdate.setStartDate(courseNewInfo.getStartDate());
        toUpdate.setEndDate(courseNewInfo.getEndDate());
        courseRepository.save(toUpdate);
    }

    public Optional<Course> findByName(String name){
        return courseRepository.findByName(name);
    }
}
