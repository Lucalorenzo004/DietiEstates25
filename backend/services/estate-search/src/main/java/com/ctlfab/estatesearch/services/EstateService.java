package com.ctlfab.estatesearch.services;

import com.ctlfab.estatesearch.dto.EstateDTO;
import com.ctlfab.estatesearch.dto.FilterDTO;

import java.util.List;

public interface EstateService {
    List<EstateDTO> getAllEstates(FilterDTO filterDTO);
}
