package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    private Long id;

    @NotEmpty(message = "A location should have a latitude")
    private String latitude;

    @NotEmpty(message = "A location should have a longitude")
    private String longitude;

    @NotEmpty(message = "A location should have a region")
    private String region;

    @NotEmpty(message = "A location should have a city")
    private String city;

    @NotEmpty(message = "A location should have a cap")
    private String cap;

    @NotEmpty(message = "A location should have a address")
    private String address;

    @NotEmpty(message = "A location should have a reference to a nearby school")
    private boolean nearSchool;

    @NotEmpty(message = "A location should have a reference to a nearby park")
    private boolean nearPark;

    @NotEmpty(message = "A location should have a reference to a nearby public transport")
    private boolean nearTransport;

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id=" + id +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", region='" + region + '\'' +
                ", city='" + city + '\'' +
                ", cap='" + cap + '\'' +
                ", address='" + address + '\'' +
                ", nearSchool=" + nearSchool +
                ", nearPark=" + nearPark +
                ", nearTransport=" + nearTransport +
                '}';
    }
}
