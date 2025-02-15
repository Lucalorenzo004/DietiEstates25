package com.ctlfab.estatesearch.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    @NotEmpty(message = "A category should have a name")
    private String name;

    @Override
    public String toString() {
        return "AddonDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
