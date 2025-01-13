package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;

import java.util.List;


public interface EstateService {
    List<EstateDTO> getAllEstates();
    EstateDTO addEstate(EstateDTO estateDTO);
    EstateDTO editEstate(EstateDTO estateDTO);
    boolean deleteEstate(long estateId);
}
