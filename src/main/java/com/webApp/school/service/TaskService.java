package com.webApp.school.service;

import com.webApp.school.model.Task;
import com.webApp.school.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements MyService<Task, Long> {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public void save(Task task) {
        taskRepository.save(task);
    }
//
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
        Task toUpdate = getById(newInfo.getId());
        toUpdate.setName(newInfo.getName());
        toUpdate.setDescription(newInfo.getDescription());
        toUpdate.setReference(newInfo.getReference());
        taskRepository.save(toUpdate);
    }
}
