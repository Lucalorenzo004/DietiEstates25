package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    @NotNull(message = "A location should have id")
    private Long id;

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

    private List<PoiDTO> poiDTOList;

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", county='" + county + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", street='" + street + '\'' +
                ", lat=" + lat +
                ", lng=" + lng +
                ", poiDTOList=" + poiDTOList +
                '}';
    }
}
