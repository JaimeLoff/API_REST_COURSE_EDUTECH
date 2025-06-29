package com.loff.api.gestion.cursos.controller;

import com.loff.api.gestion.cursos.model.CourseManagement;
import com.loff.api.gestion.cursos.model.dto.response.UserResponseDto;
import com.loff.api.gestion.cursos.service.CourseManagementService;
import com.loff.api.gestion.cursos.service.UserService;
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
    private UserService userService;

    @Autowired
    private CourseManagementService courseManagementService;

    @GetMapping()
    public List<CourseManagement> getAllCourse(){
        return courseManagementService.getAllCourse();
    }

    @GetMapping("/{idCourse}/users/{userId}")
    public ResponseEntity<Object> getCourseWithUser(@PathVariable UUID idCourse, @PathVariable UUID userId) {
        CourseManagement course = courseManagementService.getCourseById(idCourse);
        if (course.getIdCurso() == null) {
            Map<String, String> map = new HashMap<>();
            map.put("message", "Course not found");
            return new ResponseEntity<>(map, HttpStatus.NOT_FOUND);
        }

        UserResponseDto user = userService.fetchUserById(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("course", course);
        response.put("user", user);

        return new ResponseEntity<>(response, HttpStatus.OK);
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
