package com.ctlfab.estatehandle.dto;

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

    @NotEmpty(message = "A location should have an county code")
    private String countyCode;

    @NotEmpty(message = "A location should have an county")
    private String county;

    @NotEmpty(message = "A location should have an city")
    private String city;

    @NotEmpty(message = "A location should have an postal code")
    private String postalCode;

    @NotEmpty(message = "A location should have an street")
    private String street;

    private Float lat;

    private Float lng;

    private List<PoiDTO> poi;

    public LocationDTO(String countyCode, String county, String city, String postalCode, String street, Float lat, Float lng) {
        this.countyCode = countyCode;
        this.county = county;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
        this.lat = lat;
        this.lng = lng;
    }

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
