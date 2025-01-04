package com.ctlfab.estatehandle.dto;

import jakarta.persistence.Column;
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
    @Column(name = "name", nullable = false)
    private String name;
}
