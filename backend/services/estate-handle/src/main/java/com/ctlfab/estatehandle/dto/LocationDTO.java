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
    @NotEmpty(message = "A location should have id")
    private Long id;

    @NotEmpty(message = "A location should have an county")
    private String county;

    @NotEmpty(message = "A location should have an city")
    private String city;

    @NotEmpty(message = "A location should have an postal code")
    private String postalCode;

    @NotEmpty(message = "A location should have an street")
    private String street;

    @NotEmpty(message = "A location should have an street number")
    private String streetNumber;

    @NotEmpty(message = "A location should have a latitude")
    private Float lat;

    @NotEmpty(message = "A location should have a longitude")
    private Float lng;

    private List<PoiDTO> poiDTOList;

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", streetNumber='" + streetNumber + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", poiDTOList=" + poiDTOList +
                '}';
    }
}
