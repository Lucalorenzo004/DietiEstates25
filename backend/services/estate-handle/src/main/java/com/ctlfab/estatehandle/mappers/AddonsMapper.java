package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.AddonDTO;
import com.ctlfab.estatehandle.entities.Addon;
import org.mapstruct.Mapper;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring"
)
public interface AddonsMapper {
    Addon toEntity(AddonDTO addonDTO);

    AddonDTO toDTO(Addon addon);
}
