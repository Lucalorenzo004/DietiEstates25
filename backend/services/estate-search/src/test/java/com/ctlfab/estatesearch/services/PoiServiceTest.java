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
    void testFindByLatAndLngValidPositiveCoordinates() {
        Float lat = 89.0F;
        Float lng = 178.0F;

        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);

        verify(repository, times(1)).findByLatAndLng(lat, lng);
        verify(mapper, times(1)).toDTO(poi);
    }

    @Test
    void testFindByLatAndLngValidNegativeCoordinates() {
        Float lat = -1.0f;
        Float lng = -1.0f;

        poi.setLat(lat);
        poi.setLng(lng);

        poiDTO.setLat(lat);
        poiDTO.setLng(lng);

        when(repository.findByLatAndLng(lat, lng)).thenReturn(Optional.of(poi));
        when(mapper.toDTO(poi)).thenReturn(poiDTO);

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNotNull(result);
        assertEquals(poiDTO, result);

        verify(repository, times(1)).findByLatAndLng(lat, lng);
        verify(mapper, times(1)).toDTO(poi);
    }

    @Test
    void testFindByLatAndLngInvalidLatitude() {
        Float lat = 91.0f;
        Float lng = 50.0f;

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
        verify(repository, times(1)).findByLatAndLng(any(), any());
    }

    @Test
    void testFindByLatAndLngInvalidLongitude() {
        Float lat = 90.0f;
        Float lng = 181.0f;

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
        verify(repository, times(1)).findByLatAndLng(any(), any());
    }

    @Test
    void testFindByLatAndLngInvalidLatitudeAndLongitude() {
        Float lat = -91.0f;
        Float lng = -181.0f;

        PoiDTO result = service.findByLatAndLng(lat, lng);

        assertNull(result);
        verify(repository, times(1)).findByLatAndLng(any(), any());
    }
}
