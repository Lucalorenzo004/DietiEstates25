package com.ctlfab.estatesearch.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "file")
public class File {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "A file should have a bucket reference")
    @Column(name = "bucket", nullable = false)
    private String bucket;

    @NotEmpty(message = "A file should have a url reference")
    @Column(name = "url", nullable = false)
    private String url;

    @NotEmpty(message = "A file should have a name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "A file should have a content type")
    @Column(name = "content_type", nullable = false)
    private String contentType;

    @NotNull(message = "A file should have a size")
    @Column(name = "size", nullable = false)
    private Long size;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Timestamp updatedAt;

    @ManyToOne
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", bucket='" + bucket + '\'' +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                ", size=" + size +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", estate=" + estate +
                '}';
    }



}
