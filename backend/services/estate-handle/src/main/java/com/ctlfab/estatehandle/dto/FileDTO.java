package com.ctlfab.estatehandle.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
    private Long id;

    private String url;

    @NotEmpty(message = "A file should have a name")
    private String name;

    @NotEmpty(message = "A file should have a content type")
    private String contentType;

    @NotNull(message = "A file should have a size")
    private Long size;

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                '}';
    }
}
