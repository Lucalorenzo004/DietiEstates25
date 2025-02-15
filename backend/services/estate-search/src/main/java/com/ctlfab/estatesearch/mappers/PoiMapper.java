package com.ctlfab.estatesearch.mappers;

import com.ctlfab.estatesearch.dto.PoiDTO;
import com.ctlfab.estatesearch.entities.Poi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = LocationMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PoiMapper {
    PoiDTO toDTO(Poi poi);
    Poi toEntity(PoiDTO poiDTO);
}
