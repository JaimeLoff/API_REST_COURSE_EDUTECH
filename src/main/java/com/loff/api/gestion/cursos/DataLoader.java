package com.loff.api.gestion.cursos;

import com.loff.api.gestion.cursos.model.*;
import com.loff.api.gestion.cursos.repository.*;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import  org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

    Faker faker = new Faker();

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void run(String... args) throws Exception {
        //Generar Cursos
        for (int i = 0; i < 10; i++) {
            CourseManagement course = CourseManagement.builder()
                    //.idCourse(UUID)
                    .cantidadEstudiantes(faker.number().numberBetween(10,50))
                    .docenteCargo(faker.name().fullName())
                    .duracionCurso(faker.number().numberBetween(10,100)) //Duracion en horas
                    //.idCourse (UUID)
                    .nombreCurso(faker.educator().course())
                    .precioCurso(faker.number().numberBetween(100,1000)) //Precio del Curso
                    .tipoCurso(faker.options().option("Presencial", "Online", "Hibrido"))
                    .nivelCurso(faker.options().option("Basico", "Intermedio", "Avanzado"))
                    .build();

            courseRepository.save(course);

        }
    }
}
