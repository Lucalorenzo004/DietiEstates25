package com.ctlfab.estatehandle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
@Table(name = "estate")
public class Estate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A estate should have a title")
    @Column(name = "title", nullable = false)
    private String title;

    @NotEmpty(message = "A estate should have a category")
    @Column(name = "category", nullable = false)
    private String category;

    @NotEmpty(message = "A estate should have a description")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "A estate should be for rent or for sale")
    @Column(name = "rental", nullable = false)
    private boolean rental;

    @NotNull(message = "A estate should have a price")
    @Column(name = "price", nullable = false)
    private float price;

    @NotNull(message = "A estate should have a mtq")
    @Column(name = "mtq", nullable = false)
    private int mtq;

    @NotNull(message = "A estate should have an energy class")
    @Column(name = "energy_class", nullable = false)
    private String energyClass;

    @NotNull(message = "A estate should have at least 1 room")
    @Column(name = "rooms", nullable = false)
    private int rooms;

    @NotNull(message = "A estate should have at least 1 service")
    @Column(name = "services", nullable = false)
    private int services;

    @NotNull(message = "A estate should have a location")
    @ManyToOne(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NotNull(message = "A estate should be saved by an employee")
    @Column(name = "user_id", nullable = false)
    private long userId;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Addons> addons;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "estate", fetch = FetchType.LAZY)
    private List<File> files;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "Estate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", rental=" + rental +
                ", price=" + price +
                ", mtq='" + mtq + '\'' +
                ", energyClass='" + energyClass + '\'' +
                ", rooms=" + rooms +
                ", services=" + services +
                ", location=" + location +
                ", userId=" + userId +
                ", addons=" + addons +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
