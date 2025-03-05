package com.ctlfab.estatesearch.services;

import com.ctlfab.estatesearch.dto.EstateDTO;
import com.ctlfab.estatesearch.dto.FilterDTO;

import java.util.List;

public interface EstateService {

    /**
     * Fetch all the estate that match the filter passed as the argument.
     * @param filterDTO Filter criteria {@link FilterDTO}.
     * @return List of {@link EstateDTO}.
     */
    List<EstateDTO> getAll(FilterDTO filterDTO);

    /**
     * Fetch estate by its ID.
     * @param id ID of estate.
     * @return {@link EstateDTO} object.
     */
    EstateDTO getById(long id);

    /**
     * Fetch favorite user estate.
     * @param userID ID of user.
     * @return {@link EstateDTO} object.
     */
    List<EstateDTO> getFavorite(long userID);
}
