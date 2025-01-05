package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddonsDTO {
    private Long id;

    @NotEmpty(message = "An addons should have a name")
    private String name;

    @Override
    public String toString() {
        return "AddonsDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
