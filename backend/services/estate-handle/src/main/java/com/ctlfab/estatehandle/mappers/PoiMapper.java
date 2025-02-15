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
    PoiDTO toDTO(Poi poi);
    Poi toEntity(PoiDTO poiDTO);
}
