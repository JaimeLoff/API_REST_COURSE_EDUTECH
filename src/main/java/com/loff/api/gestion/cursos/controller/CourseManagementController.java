package com.loff.api.gestion.cursos.controller;

import com.loff.api.gestion.cursos.model.CourseManagement;
import com.loff.api.gestion.cursos.service.CourseManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping("/api/v1/course")
public class CourseManagementController {

    @Autowired
    private CourseManagementService courseManagementService;

    @GetMapping()
    public List<CourseManagement> getAllCourse(){
        return courseManagementService.getAllCourse();
    }

    @GetMapping("/{idCourse}")
    public ResponseEntity<Object> getCourseById(@PathVariable UUID idCourse){
        CourseManagement courseManagement = courseManagementService.getCourseById(idCourse);
        if (courseManagement.getIdCurso() != null) {
            return new ResponseEntity<>(courseManagement, HttpStatus.OK);
        } else {
            Map<String,String> map = new HashMap<String,String>();
            map.put("message","Course not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Object> addCourse(@Valid @RequestBody CourseManagement course){
        return new ResponseEntity<>(courseManagementService.addCourse(course), HttpStatus.CREATED);
    }

    @PutMapping("/{idCourse}")
    public ResponseEntity<Object> updateCourse(@PathVariable UUID idCourse,@Valid @RequestBody CourseManagement course){
        //Validar que idCourse exista para el update
        CourseManagement courseManagement = courseManagementService.getCourseById(idCourse);
        if (courseManagement == null) {
            Map<String,String> map = new HashMap<String,String>();
            map.put("message","Course not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseManagementService.updateCourse(course), HttpStatus.OK);
    }

    @DeleteMapping("/{idCourse}")
    public ResponseEntity<Object> deleteCourse(@PathVariable UUID idCourse){
        CourseManagement courseManagement = courseManagementService.getCourseById(idCourse);
        if(courseManagement.getIdCurso() == null){
            Map<String,String> map = new HashMap<>();
            map.put("message", "Course not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        courseManagementService.deleteCourse(idCourse);
        Map<String,String> map = new HashMap<>();
        map.put("message","Course deleted successfully");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
