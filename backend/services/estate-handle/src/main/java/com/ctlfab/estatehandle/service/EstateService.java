package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;

import java.util.List;

public interface EstateService {
    /**
     * Retrieves a list of all estates.
     *
     * @return A list of {@link EstateDTO} objects representing all estates.
     */
    List<EstateDTO> getAllEstates();

    /**
     * Saves a new estate.
     *
     * @param estateDTO The {@link EstateDTO} object containing details of the estate to be saved.
     * @return The saved {@link EstateDTO}
     */
    EstateDTO saveEstate(EstateDTO estateDTO);

    /**
     * Updates an existing estate.
     *
     * @param estateDTO The {@link EstateDTO} object containing updated details of the estate.
     * @return The updated {@link EstateDTO}.
     */
    EstateDTO editEstate(EstateDTO estateDTO);

    /**
     * Deletes an estate by its ID.
     *
     * @param estateId The ID of the estate to be deleted.
     * @return {@code true} if the estate was successfully deleted, {@code false} otherwise.
     */
    boolean deleteEstate(long estateId);
}
