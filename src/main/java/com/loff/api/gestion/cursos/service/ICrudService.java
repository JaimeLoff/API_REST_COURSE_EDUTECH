package com.loff.api.gestion.cursos.service;

import com.loff.api.gestion.cursos.model.CourseManagement;

import java.util.List;
import java.util.UUID;

public interface ICrudService {
    public List<CourseManagement> getAllCourse();
    public CourseManagement getCourseById(UUID idCourse);
    public CourseManagement addCourse(CourseManagement course);
    public void deleteCourse(UUID idCourse);
    public CourseManagement updateCourse(CourseManagement course);
}
