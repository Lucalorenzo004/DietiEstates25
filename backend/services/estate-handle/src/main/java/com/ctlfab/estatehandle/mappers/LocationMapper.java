package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.entities.Location;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {PoiMapper.class, EstateMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface LocationMapper {
    LocationDTO toDTO(Location location);
    Location toEntity(LocationDTO locationDTO);
}
