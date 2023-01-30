package org.ouvio.simple.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    @Size(max = 100, min = 3, message = "El nombre no es válido")
    @NotEmpty(message = "El campo nombre es obligatorio")
    private String name;

    @Column(name = "first_surname", nullable = false)
    @Size(max = 100, min = 3, message = "El apellido paterno no es válido")
    @NotEmpty(message = "El apellido paterno es obligatorio")
    private String firstSurname;

    @Column(name = "second_surname", nullable = false)
    @Size(max = 100, min = 3, message = "El apellido materno no es válido")
    @NotEmpty(message = "El apellido materno es obligatorio")
    private String secondSurname;

    @Column(name = "email", nullable = false, unique = true)
    @Email
    @UniqueElements(message = "El correo ya está ocupado")
    @NotEmpty(message = "El campo contraseña es obligatorio")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "El campo contraseña es obligatorio")
    private String password;

    @Column(name = "type", nullable = false)
    private String type;

    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
    private Company company;

    @Column(name = "created_at")
    @CreatedDate
    private Date createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Date updatedAt;

}
