package com.ctlfab.estatesearch.services;


import com.ctlfab.estatesearch.dto.PoiDTO;
import com.ctlfab.estatesearch.entities.Poi;

public interface PoiService {

    /**
     * Check if the poi already exists.
     * @param lat Lat of poi.
     * @param lng Lng of poi.
     * @return  {@link Poi} if location's data exists, null otherwise.
     */
    PoiDTO findByLatAndLng(Float lat, Float lng);
}
