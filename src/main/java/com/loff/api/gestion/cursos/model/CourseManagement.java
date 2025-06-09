package com.loff.api.gestion.cursos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
public class CourseManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCurso;
    @NotBlank(message = "Debe poner un nombre al curso")
    private String nombreCurso;
    private String tipoCurso;
    private String nivelCurso;
    @PositiveOrZero(message = "La cantidad de estudiantes no puede ser negativa")
    private int cantidadEstudiantes;
    private String docenteCargo;
    private int duracionCurso;
    @PositiveOrZero(message = "El precio no puede ser negativo")
    private int precioCurso;
}
