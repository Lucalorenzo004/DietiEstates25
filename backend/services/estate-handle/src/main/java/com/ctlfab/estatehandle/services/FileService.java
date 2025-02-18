package com.ctlfab.estatehandle.services;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;


public interface FileService {
    /**
     * Saves file metadata related to estate.
     * @param fileDTO The {@link FileDTO} object containing details of the file to be saved.
     * @param estateDTO The {@link EstateDTO} object containing details of the related to the file.
     * @return The saved {@link FileDTO}.
     */
    FileDTO save(FileDTO fileDTO, EstateDTO estateDTO);

    /**
     * Deletes an existing file by its ID.
     * @param file File's name to be deleted.
     * @return {@code true} if the file was successfully deleted, {@code false} otherwise.
     */
    boolean delete(String file);
}
