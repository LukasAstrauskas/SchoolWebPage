package com.webApp.school.repository;

import com.webApp.school.model.EnrolCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrolCourseRepository extends JpaRepository<EnrolCourse, Long> {
}
