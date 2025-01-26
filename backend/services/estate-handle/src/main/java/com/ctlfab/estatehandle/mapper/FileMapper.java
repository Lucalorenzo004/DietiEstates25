package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.model.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileDTO toDto(File file);

    @Mapping(target = "estate", ignore = true)
    File toEntity(FileDTO fileDTO);
}
