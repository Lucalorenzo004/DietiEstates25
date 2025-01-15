package com.ctlfab.estatehandle.service.impl;


import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.mapper.FileMapper;
import com.ctlfab.estatehandle.model.File;
import com.ctlfab.estatehandle.repository.FileRepository;
import com.ctlfab.estatehandle.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class FileServiceImp implements FileService{
    private final FileRepository fileRepository;
    private final FileMapper mapper;

    @Override
    public List<FileDTO> getFilesByEstateId(long estateId) {
        log.info("Fetching files by estate id {}", estateId);
        List<FileDTO> filesDTO  = new LinkedList<>();

        for(File file : fileRepository.findAllFileByEstateId(estateId)){
            FileDTO fileDTO = mapper.mapToDTO(file);
            filesDTO.add(fileDTO);
        }

        log.info("Files fetched successfully");
        return filesDTO;
    }

    @Override
    public FileDTO saveFile(FileDTO fileDTO, EstateDTO estateDTO) {
        log.info("Saving file {}", fileDTO);

        File file = mapper.mapToEntity(fileDTO, estateDTO);
        file = fileRepository.save(file);

        log.info("File saved successfully");
        return mapper.mapToDTO(file);
    }

    @Override
    public FileDTO updateFile(FileDTO fileDTO, EstateDTO estateDTO) {
        log.info("Updating file {}", fileDTO);

        File file = mapper.mapToEntity(fileDTO, estateDTO);
        file = fileRepository.save(file);

        log.info("File updated successfully");
        return mapper.mapToDTO(file);
    }

    @Override
    public boolean deleteFile(long id) {
        log.info("Deleting file {}", id);

        fileRepository.deleteById(id);

        log.info("File deleted successfully");
        return true;
    }
}
