package com.ctlfab.estatesearch.mappers;

import com.ctlfab.estatesearch.dto.FileDTO;
import com.ctlfab.estatesearch.entities.File;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = EstateMapper.class,
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FileMapper {
    FileDTO toDto(File file);
    File toEntity(FileDTO fileDTO);
}
