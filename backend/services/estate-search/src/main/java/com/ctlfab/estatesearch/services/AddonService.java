package com.ctlfab.estatesearch.services;

import com.ctlfab.estatesearch.dto.AddonDTO;

import java.util.List;

public interface AddonService {

    /**
     * Fetch all addons.
     * @return List of {@link AddonDTO}.
     */
    List<AddonDTO> getAll();
}

