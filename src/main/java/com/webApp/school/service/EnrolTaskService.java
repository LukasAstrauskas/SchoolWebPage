package com.webApp.school.service;

import com.webApp.school.model.Course;
import com.webApp.school.model.EnrolCourse;
import com.webApp.school.model.EnrolTask;
import com.webApp.school.model.Task;
import com.webApp.school.repository.EnrolTaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrolTaskService implements MyService<EnrolTask, Long> {

    private final EnrolTaskRepository enrolTaskRepository;
    private final CourseService courseService;

    public EnrolTaskService(EnrolTaskRepository enrolTaskRepository,
                            CourseService courseService) {
        this.enrolTaskRepository = enrolTaskRepository;
        this.courseService = courseService;
    }

    @Override
    public List<EnrolTask> getAll() {
        return enrolTaskRepository.findAll();
    }

    @Override
    public EnrolTask save(EnrolTask enrolTask) {
        enrolTaskRepository.save(enrolTask);
        return enrolTask;
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

    public void addTasksToStudent(EnrolCourse enrolCourse, List<Task> tasks) {
        tasks.forEach(task -> save(new EnrolTask(enrolCourse, task)));
    }

    public void unlinkTasksFromStudent(EnrolCourse enrolCourse) {
        enrolCourse.getEnrolTasks().forEach(
                enrolTask -> deleteById(enrolTask.getId())
        );
    }

    public void addTaskToStudents(Task task, Long courseID) {
        List<EnrolCourse> enrolCourses = courseService.getById(courseID).getEnrolCourses();
        if (enrolCourses != null) {
            enrolCourses
                    .forEach(enrolCourse -> {
                        System.out.println("Saving new Enr Task.");
                        save(new EnrolTask(enrolCourse, task));
                    });
        }
    }

    public void changeTaskStatus(Long enrTaskId) {
        EnrolTask enrolTask = getById(enrTaskId);
        enrolTask.setStatus(!enrolTask.isStatus());
        save(enrolTask);
    }
}
