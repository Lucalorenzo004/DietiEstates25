package com.ctlfab.estatesearch.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoiDTO {
    private Long id;

    @NotNull(message = "A POI should have a latitude")
    private Float lat;

    @NotNull(message = "A POI should have a longitude")
    private Float lng;

    @NotEmpty(message = "A POI should have a category")
    private String category;

    public PoiDTO(Float lat, Float lng, String category) {
        this.lat = lat;
        this.lng = lng;
        this.category = category;
    }

    @Override
    public String toString() {
        return "PoiDTO{" +
                "id=" + id +
                ", lat=" + lat +
                ", lng=" + lng +
                ", category='" + category + '\'' +
                '}';
    }
}
