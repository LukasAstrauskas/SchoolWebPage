package com.webApp.school.repository;

import com.webApp.school.model.Teacher;
import com.webApp.school.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Teacher getByEmail(String email);
}
