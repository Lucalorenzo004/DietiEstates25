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

    /**
     * Map {@link File} to {@link FileDTO}
     * @param file {@link File} to map
     * @return {@link FileDTO} object
     */
    FileDTO toDTO(File file);

    /**
     * Map {@link FileDTO} to {@link File}
     * @param fileDTO {@link FileDTO} to map
     * @return {@link File} object
     */
    File toEntity(FileDTO fileDTO);
}
