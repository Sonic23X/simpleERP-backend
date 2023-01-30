package org.ouvio.simple.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.ouvio.simple.dto.UserDTO;
import org.ouvio.simple.entity.User;
import org.ouvio.simple.mapper.UserMapper;
import org.ouvio.simple.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {/*

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserServiceImpl service;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        Date today = new Date();

        user = new User();
        user.setId(1);
        user.setName("Alfredo");
        user.setFirstSurname("Ortega");
        user.setSecondSurname("Ramirez");
        user.setEmail("omar.alfredo49@gmail.com");
        user.setType("Admin");
        user.setPassword("123124");
        user.setCreatedAt(today);
        user.setUpdatedAt(today);
    }

    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(user));
        assertNotNull(service.findAll());
    }

    @Test
    void findById() {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(user));
        assertNotNull(service.findById(1L));
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }*/
}