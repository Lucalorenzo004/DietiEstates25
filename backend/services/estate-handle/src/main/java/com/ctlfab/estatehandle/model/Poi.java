package com.ctlfab.estatehandle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "poi")
public class Poi {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A POI should have a latitude")
    @Column(name = "lat", nullable = false)
    private Float lat;

    @NotEmpty(message = "A POI should have a longitude")
    @Column(name = "lng", nullable = false)
    private Float lng;

    @NotEmpty(message = "A POI should have a category")
    @Column(name = "category", nullable = false)
    private String category;

    @NotEmpty(message = "A POI should reference to a location")
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;


    @Override
    public String toString() {
        return "Poi{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                ", category='" + category + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", location=" + location +
                '}';
    }
}
