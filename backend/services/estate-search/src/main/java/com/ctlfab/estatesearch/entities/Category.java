package com.ctlfab.estatesearch.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "category")
public class Category {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private List<Estate> estates;

    @Override
    public String toString() {
        return "Addon{" +
                "name='" + name + '\'' +
                ", estates=" + estates +
                '}';
    }
}
