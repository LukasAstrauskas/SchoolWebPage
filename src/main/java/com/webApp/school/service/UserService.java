package com.webApp.school.service;

import com.webApp.school.model.*;
import com.webApp.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements MyService<User, Long> {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void save(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        if (user.getId() != null) {
            update(user);
        } else {
            User userToSave;
//        TODO generate password
            String password = "pass";
            switch (user.getRole()) {
                case "ADMIN":
                    userToSave = new Admin(user, password);
                    break;
                case "STUDENT":
                    userToSave = new Student(user, password);
                    break;
                case "TEACHER":
                    userToSave = new Teacher(user, password);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + user.getRole());
            }
            userRepository.save(userToSave);
        }
    }

    @Override
    public User getById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User userWithNewInfo) {
        User toUpdate = getById(userWithNewInfo.getId());

        System.out.println(toUpdate.getId() + " " + toUpdate.getName());

        toUpdate.setName(userWithNewInfo.getName());
        toUpdate.setSurname(userWithNewInfo.getSurname());
        toUpdate.setEmail(userWithNewInfo.getEmail());
        toUpdate.setRole(userWithNewInfo.getRole());
        userRepository.save(toUpdate);
    }

    public void userMeth() {

    }

    public List<User> getAllTeachers() {

        return getAll().stream().filter(user ->
                    user.getRole().equals("TEACHER")
        ).collect(Collectors.toList());
    }
}
