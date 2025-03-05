package com.ctlfab.estatehandle.services;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FavoriteEstateDTO;


public interface EstateService {
    /**
     * Saves a new estate.
     * @param estateDTO The {@link EstateDTO} object containing details of the estate to be saved.
     * @return The saved {@link EstateDTO}.
     */
    EstateDTO save(EstateDTO estateDTO);

    /**
     * Deletes an estate by its ID.
     * @param estateId The ID of the estate to be deleted.
     * @return {@code true} if the estate was successfully deleted, {@code false} otherwise.
     */
    boolean delete(long estateId);

    /**
     * Add or Remove favorite relationship between user and estate.
     * @param favoriteEstateDTO data about relationship.
     * @return {@code true} if the estate was successfully added o removed to favorite, {@code false} otherwise.
     */
    boolean favorite(FavoriteEstateDTO favoriteEstateDTO);
}
