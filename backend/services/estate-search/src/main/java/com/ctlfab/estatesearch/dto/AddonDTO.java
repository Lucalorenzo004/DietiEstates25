package com.ctlfab.estatesearch.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddonDTO {
    @NotEmpty(message = "An addon should have a name")
    private String name;

    @Override
    public String toString() {
        return "AddonDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
