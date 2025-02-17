package com.backend.user.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    private Long id;

    @NotNull(message = "è richiesto il nome dell'utente")
    private String name;

    @NotNull(message = "è richiesto il cognome dell'utente")
    private String surname;

    @NotNull(message = "è richiesta l'email dell'utente")
    private String email;

    @Size(min = 8, message = "la password deve avere almeno 8 caratteri")
    private String password;

    private String provider;

    @NotNull(message = "è richiesto il ruolo dell'utente")
    private String role;
}
