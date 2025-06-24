package com.loff.api.gestion.cursos.controller;

import com.loff.api.gestion.cursos.assembler.CourseManagementModelAssembler;
import com.loff.api.gestion.cursos.model.CourseManagement;
import com.loff.api.gestion.cursos.service.CourseManagementService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/v2/course")
public class CourseManagementControllerV2 {

    @Autowired
    private CourseManagementService service;

    @Autowired
    private CourseManagementModelAssembler assembler;

    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<CourseManagement>> getAll() {
        List<EntityModel<CourseManagement>> cursos = service.getAllCourse().stream()
                .map(assembler::toModel)
                .toList();

        return CollectionModel.of(cursos,
                linkTo(methodOn(CourseManagementControllerV2.class).getAll()).withSelfRel());
    }

    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> getById(@PathVariable UUID id) {
        CourseManagement curso = service.getCourseById(id);
        if (curso.getIdCurso() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Course not found"));
        }
        return ResponseEntity.ok(assembler.toModel(curso));
    }

    @PostMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> create(@Valid @RequestBody CourseManagement curso) {
        CourseManagement newCurso = service.addCourse(curso);
        return ResponseEntity
                .created(linkTo(methodOn(CourseManagementControllerV2.class).getById(newCurso.getIdCurso())).toUri())
                .body(assembler.toModel(newCurso));
    }

    @PutMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> update(@PathVariable UUID id,
                                    @Valid @RequestBody CourseManagement curso) {
        CourseManagement existing = service.getCourseById(id);
        if (existing.getIdCurso() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Course not found"));
        }
        curso.setIdCurso(id);
        CourseManagement updated = service.updateCourse(curso);
        return ResponseEntity.ok(assembler.toModel(updated));
    }

    @DeleteMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        CourseManagement existing = service.getCourseById(id);
        if (existing.getIdCurso() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Course not found"));
        }
        service.deleteCourse(id);
        return ResponseEntity.ok(Map.of("message", "Course deleted successfully"));
    }
}
