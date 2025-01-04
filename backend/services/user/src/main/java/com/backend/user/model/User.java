package com.backend.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;
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

    @NotEmpty
    @Column(name = "name",nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "surname",nullable = false)
    private String surname;

    @NotEmpty
    @Column(name = "email",nullable = false,unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "provider",nullable = false)
    private String provider;

    @CreatedDate
    @Column(name = "createdAt",nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updatedAt",nullable = false,insertable = false)
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id",nullable = false)
    private Role role;

}
