package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;

import java.util.List;

/**
 * File Service
 * Author: Fabrizio Ciotola
 */
public interface FileService {
    /**
     * Retrieve file by estate ID
     * @param estateId ID of Estate
     * @return List of File related to the Estate with ID passed as argument
     */
    List<FileDTO> getFilesByEstateId(long estateId);

    /**
     * Save a new File
     * @param fileDTO FileDTO to save
     * @param estateDTO EstateDTO related to the File
     * @return FileDTO saved
     */
    FileDTO saveFile(FileDTO fileDTO, EstateDTO estateDTO);

    /**
     * Update File
     * @param fileDTO FileDTO to update
     * @param estateDTO EstateDTO related to the File
     * @return FileDTO updated
     */
    FileDTO updateFile(FileDTO fileDTO, EstateDTO estateDTO);

    /**
     * Delete File by its ID
     * @param id ID of file
     * @return True if the deletion was successful
     */
    boolean deleteFile(long id);
}
