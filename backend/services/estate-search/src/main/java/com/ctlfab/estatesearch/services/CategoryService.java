package com.ctlfab.estatesearch.services;

import com.ctlfab.estatesearch.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    /**
     * Fetch all categories.
     * @return List of {@link CategoryDTO}.
     */
    List<CategoryDTO> getAll();
}
