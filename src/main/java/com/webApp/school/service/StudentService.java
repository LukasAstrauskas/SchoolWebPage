package com.webApp.school.service;

import com.webApp.school.model.EnrolCourse;
import com.webApp.school.model.House;
import com.webApp.school.model.Student;
import com.webApp.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService implements MyService<Student, Long> {

    private final StudentRepository studentRepository;
    private final HouseService houseService;

    public StudentService(StudentRepository studentRepository,
                          HouseService houseService) {
        this.studentRepository = studentRepository;
        this.houseService = houseService;
    }


    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void save(Student student) {
        studentRepository.save(student);
    }


    @Override
    public Student getById(Long id) {
        return studentRepository.getById(id);
    }

    @Override
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void update(Student newInfo) {
        Student toUpdate = getById(newInfo.getId());
        toUpdate.setName(newInfo.getName());
        toUpdate.setSurname(newInfo.getSurname());
        toUpdate.setEmail(newInfo.getEmail());
        toUpdate.setRole(newInfo.getRole());
        toUpdate.setHouse(newInfo.getHouse());
        studentRepository.save(toUpdate);
    }


    public List<Student> getStudentsWithNoHouse() {
        return getAll().stream()
                .filter(student -> student.getHouse() == null)
                .collect(Collectors.toList());
    }

    public void setHouseToStudent(Long studentID, Long houseID) {
        Student student = getById(studentID);
        House house = houseService.getById(houseID);
        student.setHouse(house);
        studentRepository.save(student);
    }

    public void removeFromHouse(Long studentID) {
        Student student = getById(studentID);
        student.setHouse(null);
        studentRepository.save(student);
    }

    public void studentEnrolCourse(Long studentID, Long courseID) {
//        new EnrolCourse()
    }
}
