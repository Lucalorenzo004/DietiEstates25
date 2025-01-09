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

    private String password;

    private String provider;

    private Role role;
}
