package com.webApp.school.service;

import com.webApp.school.model.*;
import com.webApp.school.repository.TeacherRepository;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService implements MyService<Teacher, Long> {

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }


    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }


    @Override
    public Teacher getById(Long id) {
        return teacherRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void update(Teacher newInfo) {
        Teacher toUpdate = getById(newInfo.getId());
        toUpdate.setName(newInfo.getName());
        toUpdate.setSurname(newInfo.getSurname());
        toUpdate.setEmail(newInfo.getEmail());
        toUpdate.setRole(newInfo.getRole());
        teacherRepository.save(toUpdate);
    }


    public List<User> getAllTeachersNoHouse() {
        return getAll().stream().filter(teacher ->
                teacher.getHouse() == null
        ).collect(Collectors.toList());
    }

    public List<Course> getCourses(Authentication auth) {
        Teacher teacher = teacherRepository.getByEmail(auth.getName());
        return teacher.getCourses();
    }

    public Task getTeacherTask(Authentication auth, Long id) {
        List<Task> taskList = new ArrayList<>();
        getCourses(auth).forEach(course -> {
            taskList.addAll(course.getTasks());
        });

        Task task1 = taskList.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst().get();
        return task1;
    }

    public Object getCourseByID(Long courseID, Authentication auth) {
        Course course1 = getCourses(auth).stream()
                .filter(course -> course.getId().equals(courseID))
                .findFirst().get();
        return course1;
    }
}
