package com.webApp.school.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "task")
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String description;

    private String reference;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "task", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<EnrolTask> enrolTasks;

    public Task(String name, String description, Course course) {
        this.name = name;
        this.description = description;
        this.course = course;
    }
}
