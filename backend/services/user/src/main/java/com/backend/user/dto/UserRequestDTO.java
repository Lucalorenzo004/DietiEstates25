package com.backend.user.dto;

import com.backend.user.model.Role;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDTO {
    private Long id;

    @NotNull(message = "è richiesto il nome dell'utente")
    private String name;

    @NotNull(message = "è richiesto il cognome dell'utente")
    private String surname;

    @NotNull(message = "è richiesta l'email dell'utente")
    private String email;

    @NotNull(message = "è richiesta la password dell'utente")
    private String password;

    @NotNull(message = "è richiesto il provider dell'utente")
    private String provider;

    @NotNull(message = "è richiesto il ruolo dell'utente")
    private String role;
}
