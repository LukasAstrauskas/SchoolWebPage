package com.webApp.school.service;

import com.webApp.school.model.Admin;
import com.webApp.school.model.Student;
import com.webApp.school.model.Teacher;
import com.webApp.school.model.User;
import com.webApp.school.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements MyService<User, Long> {

    private final UserRepository userRepository;
    PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        if (user.getId() != null) {
            update(user);
            return user;
        } else {
            User userToSave;

// Todo move to new class
            String rawPassword = user.getEmail().split("@")[0];

            String password = encoder.encode(rawPassword);
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
            return userRepository.save(userToSave);
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

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow();

    }


    @Override
    public void update(User userWithNewInfo) {
        User toUpdate = getById(userWithNewInfo.getId());
        toUpdate.setName(userWithNewInfo.getName());
        toUpdate.setSurname(userWithNewInfo.getSurname());
        toUpdate.setEmail(userWithNewInfo.getEmail());
        toUpdate.setRole(userWithNewInfo.getRole());
        userRepository.save(toUpdate);
    }
}
