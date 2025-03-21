package com.ctlfab.estatesearch.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstateDTO {
    private Long id;

    @NotEmpty(message = "A estate should have a title")
    private String title;

    @NotEmpty(message = "A estate should have a description")
    private String description;

    @NotNull(message = "A estate should be for rent or for sale")
    private Boolean rental;

    @NotNull(message = "A estate should have a price")
    private Float price;

    @NotNull(message = "A estate should have a mtq")
    private Integer mtq;

    @NotEmpty(message = "A estate should have an energy class")
    private String energyClass;

    @NotNull(message = "A estate should have at least 1 room")
    private Integer rooms;

    @NotNull(message = "A estate should have at least 1 service")
    private Integer services;

    @NotNull(message = "A estate should have a location")
    private LocationDTO location;

    @NotNull(message = "A estate should be saved by an employee")
    private Long userId;

    private List<AddonDTO> addons;

    private List<FileDTO> files;

    @NotNull(message = "A estate should have a category")
    private CategoryDTO category;

    @Override
    public String toString() {
        return "EstateDTO{" +
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
                '}';
    }
}
