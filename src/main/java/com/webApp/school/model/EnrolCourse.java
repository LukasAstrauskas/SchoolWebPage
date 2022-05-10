package com.webApp.school.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class EnrolCourse {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String evaluation;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(mappedBy = "enrolCourse", cascade = {CascadeType.ALL})
    private List<EnrolTask> enrolTasks;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public EnrolCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}