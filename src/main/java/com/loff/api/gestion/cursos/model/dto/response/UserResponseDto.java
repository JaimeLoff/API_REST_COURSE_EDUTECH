package com.loff.api.gestion.cursos.model;

import java.time.LocalDate;
import java.util.UUID;

public record UserResponseDto(
    UUID userId,
    String firstName,
    String lastName,
    LocalDate birthDate,
    String email,
    String phone,
    String address,
    String rol,
    boolean active
) {}
