package com.ctlfab.estatesearch.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    private Long id;

    private String countyCode;

    @NotEmpty(message = "A location should have an county")
    private String county;

    private String city;

    private String postalCode;

    private String street;

    private Float lat;

    private Float lng;

    private List<PoiDTO> poi;

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", countyCode='" + countyCode + '\'' +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", poi=" + poi +
                '}';
    }
}
