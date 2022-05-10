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
public class EnrolTask {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private boolean status;

    private String comments;


    @ManyToOne
    @JoinColumn(name = "enrol_course_id")
    private EnrolCourse enrolCourse;


    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public EnrolTask(EnrolCourse enrolCourse, Task task) {
        this.enrolCourse = enrolCourse;
        this.task = task;
    }
}
