package com.webApp.school.service;

import com.webApp.school.model.EnrolCourse;
import com.webApp.school.model.EnrolTask;
import com.webApp.school.model.Student;
import com.webApp.school.model.Task;
import com.webApp.school.repository.EnrolTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnrolTaskService implements MyService<EnrolTask, Long> {

    private final EnrolTaskRepository enrolTaskRepository;

    public EnrolTaskService(EnrolTaskRepository enrolTaskRepository) {
        this.enrolTaskRepository = enrolTaskRepository;
    }

    @Override
    public List<EnrolTask> getAll() {
        return enrolTaskRepository.findAll();
    }

    @Override
    public void save(EnrolTask enrolTask) {
        enrolTaskRepository.save(enrolTask);
    }

    @Override
    public EnrolTask getById(Long aLong) {
        return enrolTaskRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        enrolTaskRepository.deleteById(aLong);
    }

    @Override
    public void update(EnrolTask enrolTask) {
        enrolTaskRepository.save(enrolTask);
    }

    public void addTasksToStudent(Student student, List<Task> tasks) {
        tasks.forEach(task -> save(new EnrolTask(student, task)));
    }

    public void unlinkTasksFromStudent(EnrolCourse enrolCourse) {
//        Student from whom to remove tasks, Student unlinked from course
        Student student = enrolCourse.getStudent();
//        Tasks which  should be removed from student
        List<Task> tasks = enrolCourse.getCourse().getTasks();

//        getting all students tasks
        getAll().stream()
//                filtering to one student tasks
                .filter(enrolTask -> enrolTask.getStudent().equals(student))
//                filter to tasks to remove
                .filter(enrolTask ->
                        tasks.stream()
                                .anyMatch(task -> task.equals(enrolTask.getTask()))
                )
//                removing tasks from student
                .forEach(enrolTask -> deleteById(enrolTask.getId()));
    }

}
