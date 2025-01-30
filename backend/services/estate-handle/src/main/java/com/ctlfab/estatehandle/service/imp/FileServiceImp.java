package com.ctlfab.estatehandle.service.imp;


import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.mapper.EstateMapper;
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
    private final FileRepository repository;
    private final FileMapper fileMapper;
    private final EstateMapper estateMapper;

    @Override
    public List<FileDTO> getByEstateId(long estateId) {
        log.info("Fetching files by estate id {}", estateId);
        List<FileDTO> filesDTO  = new LinkedList<>();

        for(File file : repository.findAllFileByEstateId(estateId)){
            FileDTO fileDTO = fileMapper.toDto(file);
            filesDTO.add(fileDTO);
        }

        log.info("Files fetched successfully");
        return filesDTO;
    }

    @Override
    public FileDTO save(FileDTO fileDTO, EstateDTO estateDTO) {
        log.info("Saving file {}", fileDTO);

        File file = fileMapper.toEntity(fileDTO);
        file.setEstate(estateMapper.toEntity(estateDTO));

        file = repository.save(file);
        fileDTO = fileMapper.toDto(file);

        log.info("File {} saved successfully", fileDTO);
        return fileDTO;
    }

    @Override
    public boolean delete(long id) {
        log.info("Deleting file {}", id);

        repository.deleteById(id);

        log.info("File deleted successfully");
        return true;
    }
}
