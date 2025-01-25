package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;

import java.util.List;


/**
 * Estate Service
 * Author: Fabrizio Ciotola
 */
public interface EstateService {
    List<EstateDTO> getAllEstates();

    /**
     * Save a new Estate
     * @param estateDTO EstateDTO to save
     * @param filesDTO File related to Estate
     * @return EstateDTO saved
     */
    EstateDTO addEstate(EstateDTO estateDTO, List<FileDTO> filesDTO);

    /**
     * Update Estate
     * @param estateDTO EstateDTO to update
     * @param filesDTO File related to Estate
     * @return EstateDTO updated
     */
    EstateDTO editEstate(EstateDTO estateDTO, List<FileDTO> filesDTO);

    /**
     * Delete estate by its ID
     * @param estateId ID of estate
     * @return True if the deletion was successful
     */
    boolean deleteEstate(long estateId);
}
