package com.webApp.school.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
public class Teacher extends User {

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

    public Teacher(User user, String password) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getRole());
        setPassword(password);
    }

}
