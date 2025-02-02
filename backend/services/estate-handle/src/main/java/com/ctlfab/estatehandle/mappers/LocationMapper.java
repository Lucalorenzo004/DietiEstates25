package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.entities.Location;
import org.mapstruct.Mapper;

@Mapper(
        uses = {PoiMapper.class, EstateMapper.class},
        componentModel = "spring"
)
public interface LocationMapper {
    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO locationDTO);
}
