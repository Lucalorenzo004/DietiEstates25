package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDTO {
    @NotEmpty(message = "A location should have id")
    private String id;

    @NotEmpty(message = "A location should have an address")
    private String addressLine1;

    @NotEmpty(message = "A location should have an address")
    private String addressLine2;

    @Override
    public String toString() {
        return "LocationDTO{" +
                "id='" + id + '\'' +
                ", addressLine1='" + addressLine1 + '\'' +
                ", addressLine2='" + addressLine2 + '\'' +
                '}';
    }
}
