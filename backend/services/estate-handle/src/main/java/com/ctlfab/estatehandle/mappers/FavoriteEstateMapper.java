package com.ctlfab.estatehandle.mappers;

import com.ctlfab.estatehandle.dto.FavoriteEstateDTO;
import com.ctlfab.estatehandle.entities.Estate;
import com.ctlfab.estatehandle.entities.FavoriteEstate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

@Mapper(
        uses = {EstateMapper.class},
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FavoriteEstateMapper {

    /**
     * Map {@link FavoriteEstate} to {@link FavoriteEstateDTO}
     * @param favoriteEstate {@link FavoriteEstate} to map
     * @return {@link FavoriteEstateDTO} object
     */
    @Mapping(target = "estateId", source = "estate.id")
    FavoriteEstateDTO toDTO(FavoriteEstate favoriteEstate);

    /**
     * Map {@link FavoriteEstateDTO} to {@link FavoriteEstate}
     * @param favoriteEstateDTO {@link FavoriteEstateDTO} to map
     * @return {@link FavoriteEstate} object
     */
    @Mapping(target = "estate", source = "estateId", qualifiedByName = "mapEstateFromId")
    FavoriteEstate toEntity(FavoriteEstateDTO favoriteEstateDTO);

    @Named("mapEstateFromId")
    default Estate mapEstateFromId(Long estateId) {
        if (estateId == null) {
            return null;
        }

        Estate estate = new Estate();
        estate.setId(estateId);
        return estate;
    }
}
