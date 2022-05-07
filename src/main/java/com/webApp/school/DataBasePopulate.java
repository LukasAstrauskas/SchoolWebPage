package com.webApp.school;


import com.webApp.school.service.CourseService;
import com.webApp.school.service.TaskService;
import com.webApp.school.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

//@Component
public class DataBasePopulate implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final TaskService taskService;
    private final CourseService courseService;

    public DataBasePopulate(UserService userService,
                            TaskService taskService,
                            CourseService courseService) {
        this.userService = userService;
        this.taskService = taskService;
        this.courseService = courseService;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

//        userService.save(new User("Matt", "Donny", "dony@ml",
//                "ROLE_TEACHER"));

//        Task task = new Task();
//        task.setName("New Task I");
//        Course course = new Course();
//        course.setName("New Course");
//
//
//        userService.userMeth();
//        taskService.save(task);
//        courseService.save(course);
    }
}
