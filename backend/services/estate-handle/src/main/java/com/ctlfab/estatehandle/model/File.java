package com.ctlfab.estatehandle.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty(message = "A file should have a url reference")
    @Column(name = "url", nullable = false)
    private String url;

    @NotEmpty(message = "A file should have a name")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "A file should have an extension")
    @Column(name = "extension", nullable = false)
    private String extension;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false, updatable = false)
    private Timestamp updatedAt;
}
