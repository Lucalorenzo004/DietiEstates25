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

    @NotEmpty(message = "A file should have a bucket reference")
    private String bucket;

    @NotEmpty(message = "A file should have a url reference")
    private String url;

    @NotEmpty(message = "A file should have a name")
    private String name;

    @NotEmpty(message = "A file should have a content type")
    private String contentType;

    @NotEmpty(message = "A file should have a size")
    private Long size;

    @Override
    public String toString() {
        return "FileDTO{" +
                "id=" + id +
                ", bucket='" + bucket + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                '}';
    }
}
