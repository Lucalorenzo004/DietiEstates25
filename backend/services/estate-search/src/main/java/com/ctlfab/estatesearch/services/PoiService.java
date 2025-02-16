package com.ctlfab.estatesearch.services;


import com.ctlfab.estatesearch.dto.PoiDTO;
import jakarta.validation.constraints.NotNull;

public interface PoiService {

    PoiDTO findByLatAndLng(Float lat, Float lng);
}
