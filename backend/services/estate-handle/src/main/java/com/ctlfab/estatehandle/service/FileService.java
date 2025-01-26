package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;

import java.util.List;

public interface FileService {
    /**
     * Retrieve file by estate ID
     * @param estateId ID of Estate
     * @return List of File related to the Estate with ID passed as argument
     */
    List<FileDTO> getFilesByEstateId(long estateId);

    /**
     * Saves a new file.
     *
     * @param fileDTO The {@link FileDTO} object containing details of the file to be saved.
     * @param estateDTO The {@link EstateDTO} object containing details of the related to the file
     * @return The saved {@link FileDTO}
     */
    FileDTO saveFile(FileDTO fileDTO, EstateDTO estateDTO);

    /**
     * Updates an existing file
     *
     * @param fileDTO The {@link FileDTO} object containing updated details of the file.
     *                The file ID must be present to identify the file to be updated.
     * @param estateDTO The {@link EstateDTO} object containing details of the related to the file
     * @return The updated {@link FileDTO}.
     */
    FileDTO editFile(FileDTO fileDTO, EstateDTO estateDTO);

    /**
     * Deletes an existing file by its ID.
     *
     * @param fileId The ID of the file to be deleted.
     * @return {@code true} if the file was successfully deleted, {@code false} otherwise.
     */
    boolean deleteFile(long fileId);
}
