package com.backend.offer.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "L'offerta dovrebbe essere relativa ad un immobile")
    @Column(name = "estate_id",nullable = false)
    private Long idEstate;

    @NotNull(message = "L'offerta dovrebbe esser stata effettuata da un utente")
    @Column(name = "user_email",nullable = false)
    private String emailUser;

    @NotNull(message = "L'offerta dovrebbe avere un importo offerto")
    @Column(name = "price",nullable = false)
    private Float price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",nullable = false)
    private Status status;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false,insertable = false)
    private Date updatedAt;
}
