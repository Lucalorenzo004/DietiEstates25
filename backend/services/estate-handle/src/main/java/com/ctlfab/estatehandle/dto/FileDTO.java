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

    @NotEmpty(message = "A file should have a bucket reference")
    private String bucket;

    @NotEmpty(message = "A file should have a name")
    private String name;

    @NotEmpty(message = "A file should have an content type")
    private String contentType;

    @NotNull(message = "A file should have a size")
    private long size;

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", bucket='" + bucket + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size='" + size +  '\'' +
                '}';
    }
}
