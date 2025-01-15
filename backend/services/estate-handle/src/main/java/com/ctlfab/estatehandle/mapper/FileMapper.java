package com.ctlfab.estatehandle.mapper;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.model.File;
import com.ctlfab.estatehandle.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;


@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileMapper{
    private final FileRepository repository;
    private final EstateMapper estateMapper;

    public FileDTO mapToDTO(File file, String bucket){
        return FileDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .bucket(bucket)
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();
    }

    public FileDTO mapToDTO(MultipartFile file, String bucket){
        return FileDTO.builder()
                .bucket(bucket)
                .name(file.getOriginalFilename())
                .contentType(file.getContentType())
                .size(file.getSize())
                .build();
    }

    public File mapToEntity(FileDTO fileDTO, EstateDTO estateDTO){
        if(fileDTO.getId() != null){
            Optional<File> fileOptional = repository.findById(fileDTO.getId());
            if(fileOptional.isPresent()){
                return fileOptional.get();
            }
        }

        Estate estate = estateMapper.mapToEntity(estateDTO);
        return File.builder()
                .name(fileDTO.getName())
                .bucket(fileDTO.getBucket())
                .size(fileDTO.getSize())
                .contentType(fileDTO.getContentType())
                .estate(estate)
                .build();
    }
}
