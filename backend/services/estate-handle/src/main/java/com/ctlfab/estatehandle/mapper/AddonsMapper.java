package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.AddonDTO;
import com.ctlfab.estatehandle.model.Addon;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = EstateMapper.class, componentModel = "spring")
public interface AddonsMapper {
    @Mapping(target = "estates", ignore = true)
    Addon toEntity(AddonDTO addonDTO);

    AddonDTO toDTO(Addon addon);
}
