package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PoiDTO {
    private Long id;

    @NotEmpty(message = "A POI should have a latitude")
    private Float lat;

    @NotEmpty(message = "A POI should have a longitude")
    private Float lng;

    @NotEmpty(message = "A POI should have a category")
    private String category;

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
