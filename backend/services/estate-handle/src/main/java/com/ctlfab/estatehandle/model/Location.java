package com.ctlfab.estatehandle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "location")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A location should have a latitude")
    @Column(name = "latitude", nullable = false)
    private String latitude;

    @NotEmpty(message = "A location should have a longitude")
    @Column(name = "longitude", nullable = false)
    private String longitude;

    @NotEmpty(message = "A location should have a region")
    @Column(name = "region", nullable = false)
    private String region;

    @NotEmpty(message = "A location should have a city")
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty(message = "A location should have a cap")
    @Column(name = "cap", nullable = false)
    private String cap;

    @NotEmpty(message = "A location should have a address")
    @Column(name = "address", nullable = false)
    private String address;

    @NotEmpty(message = "A location should have a reference to a nearby school")
    @Column(name = "near_school", nullable = false)
    private boolean nearSchool;

    @NotEmpty(message = "A location should have a reference to a nearby park")
    @Column(name = "near_park", nullable = false)
    private boolean nearPark;

    @NotEmpty(message = "A location should have a reference to a nearby public transport")
    @Column(name = "near_transport", nullable = false)
    private boolean nearTransport;

    @NotEmpty(message = "A location should refer to at least one estate")
    @OneToMany
    private List<Estate> estates;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", cap='" + cap + '\'' +
                ", address='" + address + '\'' +
                ", nearSchool=" + nearSchool +
                ", nearPark=" + nearPark +
                ", nearTransport=" + nearTransport +
                ", updatedAt=" + updatedAt +
                ", createdAt=" + createdAt +
                '}';
    }
}
