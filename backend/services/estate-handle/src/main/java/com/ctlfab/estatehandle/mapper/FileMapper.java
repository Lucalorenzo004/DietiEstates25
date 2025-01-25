package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.model.File;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * Class mapper for file
 * Author: Fabrizio Ciotola
 */
@Component
public class FileMapper{

    /**
     *  Map File to FileDTO
     * @param file File to map
     * @return FileDTO
     */
    public static FileDTO mapToDTO(File file){
        return FileDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .bucket(file.getBucket())
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();
    }

    /**
     * Map MultipartFile to FileDTO
     * @param file MultipartFile to map
     * @param bucket Bucket
     * @return FileDTO
     */
    public static FileDTO mapToDTO(MultipartFile file, String bucket){
        return FileDTO.builder()
                .bucket(bucket)
                .name(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .build();
    }

    /**
     * Map FileDTO to File
     * @param fileDTO FileDTO to map
     * @param estateDTO EstateDTO related to the file
     * @return File
     */
    public static File mapToEntity(FileDTO fileDTO, EstateDTO estateDTO){
        Estate estate = EstateMapper.mapToEntity(estateDTO);

        return File.builder()
                .id(fileDTO.getId())
                .name(fileDTO.getName())
                .bucket(fileDTO.getBucket())
                .size(fileDTO.getSize())
                .contentType(fileDTO.getContentType())
                .estate(estate)
                .build();
    }
}
