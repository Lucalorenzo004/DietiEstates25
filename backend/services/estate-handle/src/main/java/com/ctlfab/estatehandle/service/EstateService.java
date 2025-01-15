package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;

import java.util.List;


public interface EstateService {
    List<EstateDTO> getAllEstates();
    EstateDTO addEstate(EstateDTO estateDTO, List<FileDTO> filesDTO);
    EstateDTO editEstate(EstateDTO estateDTO, List<FileDTO> filesDTO);
    boolean deleteEstate(long estateId);
}
