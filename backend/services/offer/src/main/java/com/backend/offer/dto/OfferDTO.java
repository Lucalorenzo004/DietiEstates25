package com.backend.offer.dto;

import lombok.*;

import java.util.Date;

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

    private Date updatedAt;
}
