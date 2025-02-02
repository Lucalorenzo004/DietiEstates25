package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.entities.File;
import org.mapstruct.Mapper;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring"
)
public interface FileMapper {
    FileDTO toDto(File file);
    File toEntity(FileDTO fileDTO);
}
