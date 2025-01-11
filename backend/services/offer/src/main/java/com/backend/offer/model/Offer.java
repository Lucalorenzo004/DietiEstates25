package com.backend.offer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "L'offerta dovrebbe essere relativa ad un immobile")
    @Column(name = "estate_id",nullable = false)
    private Long idEstate;

    @NotEmpty(message = "L'offerta dovrebbe esser stata effettuata da un utente")
    @Column(name = "user_id",nullable = false)
    private Long idUser;

    @NotEmpty(message = "L'offerta dovrebbe avere un importo offerto")
    @Column(name = "price",nullable = false)
    private float price;

    @NotEmpty(message = "L'offerta dovrebbe mantenere uno stato (accettata/rifiutata/consegnata)")
    @Column(name = "status",nullable = false)
    private Status status;

    @CreatedDate
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false,insertable = false)
    private Date updatedAt;
}
