package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.PoiDTO;

public interface PoiService {
    /**
     * Saves a new POI.
     *
     * @param poiDTO The {@link PoiDTO} object containing details of the POI to be saved.
     * @return The saved {@link PoiDTO}
     */
    PoiDTO save(PoiDTO poiDTO);

    /**
     * Deletes an existing POI by its ID.
     *
     * @param poiId The ID of the POI to be deleted.
     * @return {@code true} if the POI was successfully deleted, {@code false} otherwise.
     */
    boolean delete(long poiId);
}
