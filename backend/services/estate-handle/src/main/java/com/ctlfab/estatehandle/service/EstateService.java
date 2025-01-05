package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;

import java.util.List;

public interface EstateService {
    List<EstateDTO> getEstatesByEmplyeeId(long emplyeeId);
    EstateDTO addEstate(EstateDTO estate);
    EstateDTO editEstate(EstateDTO estate);
    boolean deleteEstate(long estateId);
}
