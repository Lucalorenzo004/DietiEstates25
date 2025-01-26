package com.ctlfab.estatehandle.model;

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

    @NotEmpty(message = "A file should have a name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "A file should have a content type")
    @Column(name = "content_type", nullable = false)
    private String contentType;

    @NotNull(message = "A file should have a size")
    @Column(name = "size", nullable = false)
    private long size;

    @NotNull(message = "A file should have a reference to an estate")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_id")
    private Estate estate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Timestamp updatedAt;

    @Override
    public String toString() {
        return "File{" +
                "id=" + id +
                ", bucket='" + bucket + '\'' +
                ", name='" + name + '\'' +
                ", size=" + size +  '\'' +
                ", contentType='" + contentType + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
