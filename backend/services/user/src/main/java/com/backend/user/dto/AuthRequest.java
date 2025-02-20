package com.backend.user.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthRequest {
    @NotNull(message = "è richiesta l'email dell'utente")
    private String email;

    @NotNull(message = "è richiesta la password dell'utente")
    private String password;
}
