package com.ctlfab.estatesearch.services;

import com.ctlfab.estatesearch.dto.FileDTO;

import java.util.List;

public interface FileService {
    /**
     * Retrieve file by estate ID
     * @param estateId ID of Estate
     * @return List of File related to the Estate with ID passed as argument
     */
    List<FileDTO> getByEstateId(long estateId);
}
