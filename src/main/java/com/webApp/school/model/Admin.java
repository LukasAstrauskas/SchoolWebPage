package com.webApp.school.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "admins")
@Getter
@Setter
@NoArgsConstructor
public class Admin extends User {

    public Admin(User user, String password) {
        super(user.getName(), user.getSurname(), user.getEmail(), user.getRole());
        setPassword(password);
    }

}
