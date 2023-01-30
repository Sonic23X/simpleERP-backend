package org.ouvio.simple.service;

import org.ouvio.simple.dto.NewUserDTO;
import org.ouvio.simple.dto.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    UserDTO findById(long id);

    UserDTO findByEmail(String email);

    UserDTO save(NewUserDTO data);

    void update(long id, UserDTO data) throws Exception;

    void delete(long id) throws Exception;
}
