package com.backend.offer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferRequest {
    private Long id;

    @NotNull(message = "è richiesto l'id dell'immobile a cui è riferita l'offerta")
    private Long idEstate;

    @NotNull(message = "è richiesta l'email dell'utente che ha effettuato l'offerta")
    private String emailUser;

    @NotNull(message = "è richiesto l'importo dell'offerta")
    private Float price;

    @NotNull(message = "è richiesto lo stato dell'offerta (accettata/rifiutata/consegnata)")
    private String status;
}
