package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.model.Location;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    LocationDTO toDto(Location location);
    Location toEntity(LocationDTO locationDTO);
}
