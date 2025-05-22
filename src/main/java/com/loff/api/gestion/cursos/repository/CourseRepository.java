package com.loff.api.gestion.cursos.repository;

import com.loff.api.gestion.cursos.model.CourseManagement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository<CourseManagement, UUID> {

}
