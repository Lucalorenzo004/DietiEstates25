package com.ctlfab.estatesearch.entities;

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

    @NotEmpty(message = "A estate should have a description")
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull(message = "A estate should be for rent or for sale")
    @Column(name = "rental", nullable = false)
    private Boolean rental;

    @NotNull(message = "A estate should have a price")
    @Column(name = "price", nullable = false)
    private Float price;

    @NotNull(message = "A estate should have a mtq")
    @Column(name = "mtq", nullable = false)
    private Integer mtq;

    @NotEmpty(message = "A estate should have an energy class")
    @Column(name = "energy_class", nullable = false)
    private String energyClass;

    @NotNull(message = "A estate should have at least 1 room")
    @Column(name = "rooms", nullable = false)
    private Integer rooms;

    @NotNull(message = "A estate should have at least 1 service")
    @Column(name = "services", nullable = false)
    private Integer services;

    @NotNull(message = "A estate should have a location")
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @NotNull(message = "A estate should be saved by an employee")
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", updatable = false)
    private Timestamp updatedAt;

    @ManyToMany
    @JoinTable(
            name = "estate_addon",
            joinColumns = @JoinColumn(name = "estate_id"),
            inverseJoinColumns = @JoinColumn(name = "addon_id")
    )
    private List<Addon> addons;

    @OneToMany(mappedBy = "estate", cascade = CascadeType.ALL)
    private List<File> files;

    @NotNull(message = "A estate should have a category")
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "favorite_estate",
            joinColumns = @JoinColumn(name = "estate_id"))
    private List<FavoriteEstate> favoriteEstates;

    @Override
    public String toString() {
        return "Estate{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", description='" + description + '\'' +
                ", rental=" + rental +
                ", price=" + price +
                ", mtq=" + mtq +
                ", energyClass='" + energyClass + '\'' +
                ", rooms=" + rooms +
                ", services=" + services +
                ", location=" + location +
                ", userId=" + userId +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", addons=" + addons +
                ", files=" + files +
                '}';
    }
}
