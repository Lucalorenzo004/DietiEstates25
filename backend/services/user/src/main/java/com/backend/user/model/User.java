package com.backend.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty(message = "L'utente dovrebbe avere un nome")
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty(message = "L'utente dovrebbe avere un cognome")
    @Column(name = "surname",nullable = false)
    private String surname;

    @NotEmpty(message = "L'utente dovrebbe specificare una email")
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @NotEmpty(message = "L'utente dovrebbe specificare una password")
    @Column(name = "password",nullable = false)
    private String password;

    @NotEmpty(message = "L'utente dovrebbe specificare la modalità di autenticazione")
    @Column(name = "provider",nullable = false)
    private String provider;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false,insertable = false)
    private Date updatedAt;

    private Role role;

}
