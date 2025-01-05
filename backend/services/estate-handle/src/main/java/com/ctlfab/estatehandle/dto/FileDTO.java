package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
    private Long id;

    @NotEmpty(message = "A file should have a url reference")
    private String url;

    @NotEmpty(message = "A file should have a name")
    private String name;

    @NotEmpty(message = "A file should have an extension")
    private String extension;

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
