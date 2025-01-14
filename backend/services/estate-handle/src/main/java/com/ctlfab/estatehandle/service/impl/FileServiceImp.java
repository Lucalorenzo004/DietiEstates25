package com.ctlfab.estatehandle.service.impl;

import com.amazonaws.AmazonClientException;
import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.model.Estate;
import com.ctlfab.estatehandle.model.File;
import com.ctlfab.estatehandle.repository.FileRepository;
import com.ctlfab.estatehandle.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class FileServiceImp implements FileService{
    private final FileRepository fileRepository;

    @Override
    public List<FileDTO> getFilesByEstateId(long estateId) {
        log.info("Fetching files by estate id {}", estateId);
        List<FileDTO> filesDTO  = new ArrayList<>();

        for(File file : fileRepository.findAllFileByEstateId(estateId)){
            filesDTO.add(mapToDTO(file));
        }

        log.info("Files fetched successfully");
        return filesDTO;
    }

    @Override
    public FileDTO saveFile(FileDTO fileDTO, EstateDTO estateDTO) {
        log.info("Saving file {}", fileDTO);

        File file = mapToEntity(fileDTO, estateDTO);
        file = fileRepository.save(file);

        log.info("File saved successfully");
        return mapToDTO(file);
    }

    @Override
    public FileDTO updateFile(FileDTO fileDTO, EstateDTO estateDTO) {
        log.info("Updating file {}", fileDTO);

        File file = mapToEntity(fileDTO, estateDTO);
        file = fileRepository.save(file);

        log.info("File updated successfully");
        return mapToDTO(file);
    }

    @Override
    public boolean deleteFile(long id) {
        log.info("Deleting file {}", id);

        fileRepository.deleteById(id);

        log.info("File deleted successfully");
        return true;
    }

    private FileDTO mapToDTO(File file) {
        return FileDTO.builder()
                .id(file.getId())
                .name(file.getName())
                .bucket(file.getBucket())
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();
    }

    private File mapToEntity(FileDTO fileDTO, EstateDTO estateDTO) {
        Estate estate = Estate.builder().id(estateDTO.getId()).build();

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
