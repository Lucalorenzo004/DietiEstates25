package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.PoiDTO;
import com.ctlfab.estatehandle.entities.Poi;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = LocationMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PoiMapper {

    /**
     * Map {@link Poi} to {@link PoiDTO}
     * @param poi {@link Poi} to map
     * @return {@link PoiDTO} object
     */
    PoiDTO toDTO(Poi poi);

    /**
     * Map {@link PoiDTO} to {@link Poi}
     * @param poiDTO {@link PoiDTO} to map
     * @return {@link Poi} object
     */
    Poi toEntity(PoiDTO poiDTO);
}
