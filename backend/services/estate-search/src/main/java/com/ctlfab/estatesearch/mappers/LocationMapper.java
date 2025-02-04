package com.ctlfab.estatesearch.mappers;

import com.ctlfab.estatesearch.dto.LocationDTO;
import com.ctlfab.estatesearch.entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {PoiMapper.class, EstateMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LocationMapper {
    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO locationDTO);
}
