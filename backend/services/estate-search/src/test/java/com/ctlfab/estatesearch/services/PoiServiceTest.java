package com.ctlfab.estatesearch.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.ctlfab.estatesearch.dto.PoiDTO;
import com.ctlfab.estatesearch.entities.Poi;
import com.ctlfab.estatesearch.mappers.PoiMapper;
import com.ctlfab.estatesearch.repositories.PoiRepository;
import com.ctlfab.estatesearch.services.imp.PoiServiceImp;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class PoiServiceTest {
    @Mock
    private PoiRepository repository;

    @Mock
    private PoiMapper mapper;

    @InjectMocks
    private PoiServiceImp service;

    private Poi poi;
    private PoiDTO poiDTO;

    @BeforeEach
    void setUp() {
        poi = new Poi();
        poiDTO = new PoiDTO();
    }


    @Test
    void testFindByLatAndLngValidCoordinates() {
        Float lat = 0.0F; //valore nominale
        Float lng = 0.0F; //valore nominale

        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);
    }

    @Test
    void testFindByLatAndLngValidMaxLongitude() {
        Float lat = 0.0F; //valore nominale
        Float lng = 180.0F; //valore massimo


        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);
    }

    @Test
    void testFindByLatAndLngValidMinLongitude() {
        Float lat = 0.0F; //valore nominale
        Float lng = -180.0F; //valore minimo

        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);
    }

    @Test
    void testFindByLatAndLngInvalidMaxLongitude() {
        Float lat = 0.0f; //valore nominale
        Float lng = 181.0f; //valore superiore al massimo

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
    }

    @Test
    void testFindByLatAndLngInvalidMinLongitude() {
        Float lat = 0.0f; //valore nominale
        Float lng = -181.0f; //valore inferiore al minimo

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
    }


    @Test
    void testFindByLatAndLngValidMaxLatitude() {
        Float lat = 90.0F; //valore massimo
        Float lng = 0.0F; //valore nominale

        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);
    }

    @Test
    void testFindByLatAndLngValidMinLatitude() {
        Float lat = -90.0F; //valore minimo
        Float lng = 0.0F; //valore nominale

        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);
    }

    @Test
    void testFindByLatAndLngInvalidMinLatitude() {
        Float lat = -91.0f; //valore inferiore al minimo
        Float lng = 0.0f; //valore nominale

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
    }

    @Test
    void testFindByLatAndLngInvalidMaxLatitude() {
        Float lat = 91.0f; //valore superiore al massimo
        Float lng = 0.0f; //valore nominale

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
    }
}
