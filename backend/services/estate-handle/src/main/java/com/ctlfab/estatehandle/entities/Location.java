package com.ctlfab.estatehandle.entities;

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

    @NotEmpty(message = "A location should have an county")
    @Column(name = "county", nullable = false)
    private String county;

    @NotEmpty(message = "A location should have an city")
    @Column(name = "city", nullable = false)
    private String city;

    @NotEmpty(message = "A location should have an postal code")
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotEmpty(message = "A location should have an street")
    @Column(name = "street", nullable = false)
    private String street;

    //@NotNull(message = "A location should have a latitude")
    @Column(name = "lat", nullable = false)
    private Float lat;

    //@NotNull(message = "A location should have a longitude")
    @Column(name = "lng", nullable = false)
    private Float lng;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    List<Poi> poiList;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL)
    List<Estate> estates;

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", poiList=" + poiList +
                ", estates=" + estates +
                '}';
    }

}
