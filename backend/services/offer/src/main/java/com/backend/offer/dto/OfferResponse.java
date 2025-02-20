package com.backend.offer.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferResponse {
    private Long id;

    private float price;

    private String status;

    private Date createdAt;

    private Date updatedAt;
}
