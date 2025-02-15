package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.AddonDTO;
import com.ctlfab.estatehandle.entities.Addon;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AddonsMapper {
    Addon toEntity(AddonDTO addonDTO);

    AddonDTO toDTO(Addon addon);
}
