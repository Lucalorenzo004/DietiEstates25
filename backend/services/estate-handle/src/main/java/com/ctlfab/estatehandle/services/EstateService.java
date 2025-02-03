package com.ctlfab.estatehandle.services;

import com.ctlfab.estatehandle.dto.EstateDTO;

import java.util.List;

public interface EstateService {
    List<EstateDTO> getAllEstates();

    /**
     * Saves a new estate.
     *
     * @param estateDTO The {@link EstateDTO} object containing details of the estate to be saved.
     * @return The saved {@link EstateDTO}
     */
    EstateDTO save(EstateDTO estateDTO);

    /**
     * Deletes an estate by its ID.
     *
     * @param estateId The ID of the estate to be deleted.
     * @return {@code true} if the estate was successfully deleted, {@code false} otherwise.
     */
    boolean delete(long estateId);
}
