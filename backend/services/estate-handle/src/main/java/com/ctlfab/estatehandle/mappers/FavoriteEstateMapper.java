package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.FavoriteEstateDTO;
import com.ctlfab.estatehandle.entities.Estate;
import com.ctlfab.estatehandle.entities.FavoriteEstate;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FavoriteEstateMapper {

    /**
     * Map {@link FavoriteEstate} to {@link FavoriteEstateDTO}
     * @param favoriteEstate {@link FavoriteEstate} to map
     * @return {@link FavoriteEstateDTO} object
     */
    FavoriteEstateDTO toDTO(FavoriteEstate favoriteEstate);

    /**
     * Map {@link FavoriteEstateDTO} to {@link FavoriteEstate}
     * @param favoriteEstateDTO {@link FavoriteEstateDTO} to map
     * @return {@link FavoriteEstate} object
     */
    FavoriteEstate toEntity(FavoriteEstateDTO favoriteEstateDTO);
}
