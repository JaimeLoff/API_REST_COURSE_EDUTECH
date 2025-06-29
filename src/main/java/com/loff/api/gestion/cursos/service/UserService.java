package com.loff.api.gestion.cursos.service;

import com.loff.api.gestion.cursos.model.dto.response.UserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${users.api.url}")
    private String userApiUrl;

    public UserResponseDto fetchUserById(UUID userId) {
        String url = userApiUrl + "/api/v1/users/" + userId;
        return restTemplate.getForObject(url, UserResponseDto.class);
    }

}
