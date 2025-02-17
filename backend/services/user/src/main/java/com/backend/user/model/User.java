package com.backend.user.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Size(min = 8, message = "la password deve avere almeno 8 caratteri")
    @Column(name = "password",nullable = false)
    private String password;

    @NotEmpty(message = "L'utente dovrebbe specificare la modalità di autenticazione")
    @Column(name = "provider",nullable = false)
    private String provider;

    @CreatedDate
    @Column(name = "created_at",updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",insertable = false)
    private Date updatedAt;

    @Column(name = "role",nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public String getUsername() {
        return email;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
