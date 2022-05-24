package com.webApp.school.service;

import com.webApp.school.model.Task;
import com.webApp.school.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements MyService<Task, Long> {

    private final TaskRepository taskRepository;
    private final EnrolTaskService enrolTaskService;

    @Autowired
    public TaskService(TaskRepository taskRepository, EnrolTaskService enrolTaskService) {
        this.taskRepository = taskRepository;
        this.enrolTaskService = enrolTaskService;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        if (task.getId() != null) {
            update(task);
        } else {
            Task save = taskRepository.save(task);
            enrolTaskService.addTaskToStudents(task, task.getCourse().getId());
        }
        return task;
    }

    @Override
    public Task getById(Long aLong) {
        return taskRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
        taskRepository.deleteById(aLong);
    }

    @Override
    public void update(Task newInfo) {
        Long taskID = newInfo.getId();
        Task toUpdate = getById(taskID);
        if (toUpdate.getCourse().equals(newInfo.getCourse())) {
            toUpdate.setName(newInfo.getName());
            toUpdate.setDescription(newInfo.getDescription());
            toUpdate.setReference(newInfo.getReference());
            taskRepository.save(toUpdate);
        } else {
            deleteById(taskID);
            newInfo.setId(null);
            save(newInfo);
        }
    }
}
