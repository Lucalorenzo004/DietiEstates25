package com.ctlfab.estatehandle.services.imp;


import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.mappers.EstateMapper;
import com.ctlfab.estatehandle.mappers.FileMapper;
import com.ctlfab.estatehandle.entities.File;
import com.ctlfab.estatehandle.repositories.FileRepository;
import com.ctlfab.estatehandle.services.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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

    @Override
    public FileDTO save(FileDTO fileDTO, EstateDTO estateDTO) {
        log.info("Saving file {}", fileDTO);

        File file = fileMapper.toEntity(fileDTO);

        file.setBucket(bucket);
        String url = "https://dieti-estate25.s3.eu-north-1.amazonaws.com/" + file.getName();
        file.setUrl(url);
        file.setEstate(estateMapper.toEntity(estateDTO));

        file = repository.save(file);
        FileDTO savedFileDTO = fileMapper.toDTO(file);

        log.info("File {} saved successfully", savedFileDTO);
        return savedFileDTO;
    }

    @Override
    public boolean delete(long id) {
        log.info("Deleting file {}", id);

        repository.deleteById(id);

        log.info("File deleted successfully");
        return true;
    }
}
