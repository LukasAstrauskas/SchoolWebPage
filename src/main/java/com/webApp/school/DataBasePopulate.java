package com.webApp.school;


import com.github.javafaker.Faker;
import com.webApp.school.model.Course;
import com.webApp.school.model.House;
import com.webApp.school.model.Task;
import com.webApp.school.model.User;
import com.webApp.school.service.CourseService;
import com.webApp.school.service.HouseService;
import com.webApp.school.service.TaskService;
import com.webApp.school.service.UserService;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;

@Component
public class DataBasePopulate implements ApplicationListener<ContextRefreshedEvent> {

    private final UserService userService;
    private final TaskService taskService;
    private final CourseService courseService;
    private final HouseService houseService;
    private final Faker faker = new Faker();

    public DataBasePopulate(UserService userService,
                            TaskService taskService,
                            CourseService courseService,
                            HouseService houseService) {
        this.userService = userService;
        this.taskService = taskService;
        this.courseService = courseService;
        this.houseService = houseService;
    }

    private void addHouses(int amount) {
        for (int i = 0; i < amount; i++) {
            String name = faker.gameOfThrones().house();
            houseService.save(new House(name));
        }
    }

    private void addCourses(int amount) {
        for (int i = 0; i < amount; i++) {

            String name = faker.educator().course();
            String desc = faker.twinPeaks().quote();
            int credits = new Random().nextInt(5) + 2;
            Course newCourse = new Course(name, desc, credits);


            courseService.save(newCourse);
            //            Adding 4 task for each new course.
//            addTasks(name, tasks);

        }
    }

    private void addTasks(String courseName, int tasks) {
        Optional<Course> optional = courseService.findByName(courseName);
        optional.ifPresent(course -> {
            for (int j = 0; j < tasks; j++) {
                String task = faker.elderScrolls().city();
                String description = faker.backToTheFuture().quote();
                taskService.save(new Task(task, description, course));
            }
        });
    }

    private void addStudents(int amount) {
        for (int i = 0; i < amount; i++) {

            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String email = surname.substring(0, 4) + "@ml";
            String role = "STUDENT";
            User user = new User(name, surname, email, role);
            userService.save(user);
        }
    }


    private void addTeachers(int amount) {
        for (int i = 0; i < amount; i++) {
            String name = faker.name().firstName();
            String surname = faker.name().lastName();
            String email = surname.substring(0, 4) + "@ml";
            String role = "TEACHER";
            User user = new User(name, surname, email, role);
            userService.save(user);
        }
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
//        courseService.getAll()
//                .forEach(course -> addTasks(course.getName(), 4));
    }
}
