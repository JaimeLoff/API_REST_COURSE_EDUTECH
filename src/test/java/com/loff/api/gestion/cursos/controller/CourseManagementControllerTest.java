package com.loff.api.gestion.cursos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loff.api.gestion.cursos.model.CourseManagement;
import com.loff.api.gestion.cursos.service.CourseManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CourseManagementController.class)
public class CourseManagementControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseManagementService courseManagementService;

    private CourseManagement course;
    private UUID courseId;

    @BeforeEach
    void setUp() {
        courseId = UUID.randomUUID();
        course = new CourseManagement();
        course.setIdCurso(courseId);
        course.setNombreCurso("Java Básico");
        // agrega más campos si es necesario
    }

    @Test
    void testGetAllCourses() throws Exception {
        List<CourseManagement> courses = Arrays.asList(course);
        when(courseManagementService.getAllCourse()).thenReturn(courses);

        mockMvc.perform(get("/api/v1/course"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idCurso").value(courseId.toString()));
    }

    @Test
    void testGetCourseById_Found() throws Exception {
        when(courseManagementService.getCourseById(courseId)).thenReturn(course);

        mockMvc.perform(get("/api/v1/course/{id}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idCurso").value(courseId.toString()));
    }

    @Test
    void testGetCourseById_NotFound() throws Exception {
        CourseManagement empty = new CourseManagement(); // idCurso = null
        when(courseManagementService.getCourseById(courseId)).thenReturn(empty);

        mockMvc.perform(get("/api/v1/course/{id}", courseId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Course not found"));
    }

    @Test
    void testAddCourse() throws Exception {
        when(courseManagementService.addCourse(any(CourseManagement.class))).thenReturn(course);

        mockMvc.perform(post("/api/v1/course")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(course)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idCurso").value(courseId.toString()));
    }

    @Test
    void testDeleteCourse_Success() throws Exception {
        when(courseManagementService.getCourseById(courseId)).thenReturn(course);
        Mockito.doNothing().when(courseManagementService).deleteCourse(courseId);

        mockMvc.perform(delete("/api/v1/course/{id}", courseId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Course deleted successfully"));
    }

    @Test
    void testDeleteCourse_NotFound() throws Exception {
        CourseManagement notFound = new CourseManagement(); // idCurso = null
        when(courseManagementService.getCourseById(courseId)).thenReturn(notFound);

        mockMvc.perform(delete("/api/v1/course/{id}", courseId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Course not found"));
    }
}
