package com.backend.offer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferDTO {
    private Long id;

    private Long idEstate;

    private String emailUser;

    private Float price;

    private String status;
}
