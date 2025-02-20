package com.backend.user.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;

    private String name;

    private String surname;

    private String email;

    private String password;

    private String provider;

    private String agency;

    private String role;
}
