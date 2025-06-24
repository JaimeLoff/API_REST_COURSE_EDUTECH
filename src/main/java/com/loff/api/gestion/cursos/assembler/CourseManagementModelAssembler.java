package com.loff.api.gestion.cursos.assembler;

import com.loff.api.gestion.cursos.controller.CourseManagementControllerV2;
import com.loff.api.gestion.cursos.model.CourseManagement;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CourseManagementModelAssembler implements RepresentationModelAssembler<CourseManagement, EntityModel<CourseManagement>> {

    @Override
    public EntityModel<CourseManagement> toModel(CourseManagement course) {
        return EntityModel.of(course,
                linkTo(methodOn(CourseManagementControllerV2.class).getById(course.getIdCurso())).withSelfRel(),
                linkTo(methodOn(CourseManagementControllerV2.class).getAll()).withRel("cursos"));
    }

}
