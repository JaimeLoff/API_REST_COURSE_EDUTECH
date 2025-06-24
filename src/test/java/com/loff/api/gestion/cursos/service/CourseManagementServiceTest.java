package com.loff.api.gestion.cursos.service;

import com.loff.api.gestion.cursos.model.CourseManagement;
import com.loff.api.gestion.cursos.repository.CourseRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CourseManagementServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseManagementService courseService;

    private AutoCloseable closeable;

    private CourseManagement course;
    private UUID courseId;

    @BeforeEach
    void setup() {
        closeable = MockitoAnnotations.openMocks(this);

        courseId = UUID.randomUUID();
        course = new CourseManagement();
        course.setIdCurso(courseId);
        course.setNombreCurso("Curso de Prueba");
        // setea otros campos si los tienes
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void testGetAllCourse() {
        List<CourseManagement> courses = Collections.singletonList(course);
        when(courseRepository.findAll()).thenReturn(courses);

        List<CourseManagement> result = courseService.getAllCourse();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(courseId, result.get(0).getIdCurso());
        verify(courseRepository, times(1)).findAll();
    }

    @Test
    void testGetCourseById_Found() {
        when(courseRepository.findById(courseId)).thenReturn(Optional.of(course));

        CourseManagement result = courseService.getCourseById(courseId);

        assertNotNull(result);
        assertEquals(courseId, result.getIdCurso());
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testGetCourseById_NotFound() {
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());

        CourseManagement result = courseService.getCourseById(courseId);

        //devuelve objeto vacío
        assertNotNull(result);
        assertNull(result.getIdCurso());
        verify(courseRepository, times(1)).findById(courseId);
    }

    @Test
    void testAddCourse() {
        when(courseRepository.save(course)).thenReturn(course);

        CourseManagement result = courseService.addCourse(course);

        assertNotNull(result);
        assertEquals(courseId, result.getIdCurso());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testUpdateCourse() {
        when(courseRepository.save(course)).thenReturn(course);

        CourseManagement result = courseService.updateCourse(course);

        assertNotNull(result);
        assertEquals(courseId, result.getIdCurso());
        verify(courseRepository, times(1)).save(course);
    }

    @Test
    void testDeleteCourse() {
        doNothing().when(courseRepository).deleteById(courseId);

        courseService.deleteCourse(courseId);

        verify(courseRepository, times(1)).deleteById(courseId);
    }
}
