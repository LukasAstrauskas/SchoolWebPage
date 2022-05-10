package com.webApp.school.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student extends User {

    @ManyToOne
    @JoinColumn(name = "house_id")
    private House house;

    @OneToMany(mappedBy = "student", cascade = {CascadeType.ALL})
    private List<EnrolCourse> enrolCourses;

    public Student(User user, String password) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getRole());
        setPassword(password);
    }
}
