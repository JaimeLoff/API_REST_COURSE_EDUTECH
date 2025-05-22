package com.loff.api.gestion.cursos.service;

import com.loff.api.gestion.cursos.model.CourseManagement;
import com.loff.api.gestion.cursos.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class CourseManagementService implements ICrudService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseManagement> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public CourseManagement getCourseById(UUID idCourse) {
        return courseRepository.findById(idCourse)
                .orElse(new CourseManagement());
    }

    @Override
    public CourseManagement addCourse(CourseManagement course) {
        return courseRepository.save(course);
    }

    @Override
    public void deleteCourse(UUID idCourse) {
        courseRepository.deleteById(idCourse);
    }

    @Override
    public CourseManagement updateCourse(CourseManagement course) {
        return courseRepository.save(course);
    }
}
