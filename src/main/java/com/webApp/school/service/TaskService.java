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
        if (task.getCourse().getId() == 0) {
            task.setCourse(null);
        }
        if (task.getId() != null){
            update(task);
            return task;
        } else {
            Task savedTask = taskRepository.save(task);
            System.out.println("Course ID form Saved Task: "+savedTask.getCourse().getId());
            enrolTaskService.addTaskToStudents(savedTask, savedTask.getCourse().getId());
            return savedTask;
        }
    }

    @Override
    public Task getById(Long aLong) {
        return taskRepository.getById(aLong);
    }

    @Override
    public void deleteById(Long aLong) {
//        enrolTaskService.unlinkTaskFromStudents(getById(aLong));
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
