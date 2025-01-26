package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.model.Addons;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = EstateMapper.class, componentModel = "spring")
public interface AddonsMapper {
    @Mapping(target = "estates", ignore = true)
    Addons toEntity(AddonsDTO addonsDTO);

    AddonsDTO toDTO(Addons addons);
}
