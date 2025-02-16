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

    /**
     * Saves file metadata related to estate.
     * @param fileDTO The {@link FileDTO} object containing details of the file to be saved.
     * @param estateDTO The {@link EstateDTO} object containing details of the related to the file.
     * @return The saved {@link FileDTO}.
     */
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

    /**
     * Deletes an existing file by its ID.
     * @param file File's name to be deleted.
     * @return {@code true} if the file was successfully deleted, {@code false} otherwise.
     */
    @Override
    public boolean delete(String file) {
        log.info("Deleting file {}", file);

        int deletedRows = repository.deleteByName(file);
        if (deletedRows == 0) {
            log.info("File deletion failed");
            return false;
        }

        log.info("File deleted successfully");
        return true;
    }
}
