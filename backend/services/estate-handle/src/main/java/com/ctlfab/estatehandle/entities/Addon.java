package com.ctlfab.estatehandle.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "addon")
public class Addon {
    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "addons")
    private List<Estate> estates;

    @Override
    public String toString() {
        return "Addon{" +
                "name='" + name + '\'' +
                ", estates=" + estates +
                '}';
    }
}
