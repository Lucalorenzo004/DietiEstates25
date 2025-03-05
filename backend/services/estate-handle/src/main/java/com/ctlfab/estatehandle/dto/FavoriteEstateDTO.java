package com.ctlfab.estatehandle.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteEstateDTO {
    private Long estateId;
    private Long userId;
    private boolean addToFavorite;
}
