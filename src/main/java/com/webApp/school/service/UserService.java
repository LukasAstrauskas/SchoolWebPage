package com.webApp.school.service;

import com.webApp.school.model.*;
import com.webApp.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService implements MyService<User, UUID> {

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
        User userToSave;
//        TODO generate password
        String password = "pass";
        switch (user.getRole()) {
            case "ROLE_ADMIN":
                userToSave = new Admin(user, password);
                break;
            case "ROLE_STUDENT":
                userToSave = new Student(user, password);
                break;
            case "ROLE_TEACHER":
                userToSave = new Teacher(user, password);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + user.getRole());
        }
        userRepository.save(userToSave);
    }

    @Override
    public User getById(UUID id) {
        return userRepository.getById(id);
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(User userWithNewInfo) {
        User toUpdate = getById(userWithNewInfo.getUuid());
        toUpdate.setName(userWithNewInfo.getName());
        toUpdate.setSurname(userWithNewInfo.getSurname());
        toUpdate.setEmail(userWithNewInfo.getEmail());
        toUpdate.setRole(userWithNewInfo.getRole());
        userRepository.save(toUpdate);
    }

    public void userMeth() {

    }
}
