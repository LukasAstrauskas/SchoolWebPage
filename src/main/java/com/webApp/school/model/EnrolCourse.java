package com.webApp.school.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "student_uuid")
    private Student student;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;


    public EnrolCourse(Student student, Course course) {
        this.student = student;
        this.course = course;
    }
}