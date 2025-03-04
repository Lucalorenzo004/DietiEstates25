package com.ctlfab.estatesearch.dto;

import com.ctlfab.estatesearch.entities.Category;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterDTO {
    private CategoryDTO category;
    private Boolean rental;
    private Float minPrice;
    private Float maxPrice;
    private Long minMtq;
    private Long maxMtq;
    private String energyClass;
    private Long minRooms;
    private Long maxRooms;
    private Long minServices;
    private Long maxServices;
    private Long userId;
    private LocationDTO location;
    private List<AddonDTO> addons;
}
