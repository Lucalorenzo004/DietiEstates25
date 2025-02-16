package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.FileDTO;
import com.ctlfab.estatesearch.mappers.FileMapper;
import com.ctlfab.estatesearch.entities.File;
import com.ctlfab.estatesearch.repositories.FileRepository;
import com.ctlfab.estatesearch.services.FileService;
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
    private final FileRepository repository;
    private final FileMapper fileMapper;

    @Override
    public List<FileDTO> getByEstateId(long estateId) {
        log.info("Fetching files by estate id {}", estateId);
        List<FileDTO> filesDTO  = new LinkedList<>();

        for(File file : repository.findAllFileByEstateId(estateId)){
            FileDTO fileDTO = fileMapper.toDTO(file);
            filesDTO.add(fileDTO);
        }

        log.info("Files fetched successfully");
        return filesDTO;
    }
}
