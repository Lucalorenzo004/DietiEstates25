package com.ctlfab.estatesearch.mappers;

import com.ctlfab.estatesearch.dto.LocationDTO;
import com.ctlfab.estatesearch.entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {PoiMapper.class, EstateMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LocationMapper {

    /**
     * Map {@link Location} to {@link LocationDTO}
     * @param location {@link Location} to map
     * @return {@link LocationDTO} object
     */
    @Mapping(target = "poi", source = "poiList")
    LocationDTO toDTO(Location location);

    /**
     * Map {@link LocationDTO} to {@link Location}
     * @param locationDTO {@link LocationDTO} to map
     * @return {@link Location} object
     */
    @Mapping(target = "poiList", source = "poi")
    Location toEntity(LocationDTO locationDTO);
}
