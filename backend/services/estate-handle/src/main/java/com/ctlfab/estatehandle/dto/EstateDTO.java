package com.ctlfab.estatehandle.dto;

import com.ctlfab.estatehandle.model.Addons;
import com.ctlfab.estatehandle.model.File;
import com.ctlfab.estatehandle.model.Location;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "A estate should have a category")
    private String category;

    @NotEmpty(message = "A estate should have a description")
    private String description;

    @NotEmpty(message = "A estate should be for rent or for sale")
    private boolean rental;

    @NotEmpty(message = "A estate should have a price")
    private Float price;

    @NotEmpty(message = "A estate should have a mtq")
    private String mtq;

    @NotEmpty(message = "A estate should have an energy class")
    private String energyClass;

    @NotEmpty(message = "A estate should have at least 1 room")
    private Integer rooms;

    @NotEmpty(message = "A estate should have at least 1 service")
    private Integer services;

    @NotEmpty(message = "A estate should have a location")
    private Location location;

    @NotEmpty(message = "A estate should be saved by an employee")
    private Long userId;

    private List<Addons> addons;

    @NotEmpty(message = "A estate should have a location")
    private List<File> files;

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
                ", files=" + files +
                '}';
    }
}
