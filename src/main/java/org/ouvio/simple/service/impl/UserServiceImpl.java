package org.ouvio.simple.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.ouvio.simple.dto.EmailDTO;
import org.ouvio.simple.dto.NewUserDTO;
import org.ouvio.simple.dto.UserDTO;
import org.ouvio.simple.entity.Company;
import org.ouvio.simple.entity.User;
import org.ouvio.simple.mapper.CompanyMapper;
import org.ouvio.simple.mapper.UserMapper;
import org.ouvio.simple.producer.EmailProducer;
import org.ouvio.simple.repository.CompanyRepository;
import org.ouvio.simple.repository.UserRepository;
import org.ouvio.simple.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private UserMapper mapper;
    private CompanyMapper companyMapper;
    private UserRepository repository;
    private CompanyRepository companyRepository;
    private EmailProducer producer;

    @Autowired
    public UserServiceImpl(UserMapper mapper, UserRepository repository, CompanyMapper companyMapper, CompanyRepository companyRepository, EmailProducer producer) {
        this.mapper = mapper;
        this.repository = repository;
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
        this.producer = producer;
    }

    public List<UserDTO> findAll() {
        List<User> users = repository.findAll();

        return users.stream().map(mapper::toDTO).toList();
    }

    public UserDTO findById(long id) {
        Optional<User> result = repository.findById(id);

        if (result.isEmpty()) {
            return null;
        }

        User user = result.get();
        log.info("Usuario encontrado: " + user.toString());
        return mapper.toDTO(user);
    }

    public UserDTO findByEmail(String email) {
        return null;
    }

    public UserDTO save(NewUserDTO data) {
        Optional<Company> result = companyRepository.findById(data.getCompany());

        if (!result.isEmpty()) {
            Company company = result.get();

            User user = new User();
            user.setName(data.getName());
            user.setFirstSurname(data.getFirstSurname());
            user.setSecondSurname(data.getSecondSurname());
            user.setEmail(data.getEmail());
            user.setPassword(new BCryptPasswordEncoder().encode(data.getPassword()));
            user.setType(data.getType());
            user.setCompany(company);
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            log.info("Usuario creado: " + user.toString());

            log.info("Enviando correo...");
            EmailDTO mail = new EmailDTO();
            mail.setFrom("noreply@simpleerp.com");
            mail.setTo(user.getEmail());
            mail.setSubject("Gracias por usar simple ERP");
            mail.setBody("Bienvenido a simple ERP");
            producer.sendEmail(mail);

            return mapper.toDTO(repository.save(user));
        }

        log.error("Compañia invalida");
        return null;
    }

    public void update(long id, UserDTO data) throws Exception {
        Optional<User> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe el usuario");
        }

        User user = result.get();

        user.setName(data.getName());
        user.setFirstSurname(data.getFirstSurname());
        user.setSecondSurname(data.getSecondSurname());
        user.setEmail(data.getEmail());

        repository.save(user);

        log.info("Usuario actualizado: " + user.toString());
    }

    public void delete(long id) throws Exception {
        Optional<User> result = repository.findById(id);

        if (result.isEmpty()) {
            throw new Exception("No existe el usuario");
        }
        log.info("Usuario eliminado, id: " + id);
        repository.deleteById(id);
    }
}
