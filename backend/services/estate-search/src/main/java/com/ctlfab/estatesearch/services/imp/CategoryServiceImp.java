package com.ctlfab.estatesearch.services.imp;

import com.ctlfab.estatesearch.dto.CategoryDTO;
import com.ctlfab.estatesearch.entities.Category;
import com.ctlfab.estatesearch.mappers.CategoryMapper;
import com.ctlfab.estatesearch.repositories.CategoryRepository;
import com.ctlfab.estatesearch.services.AddonService;
import com.ctlfab.estatesearch.services.CategoryService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class CategoryServiceImp implements CategoryService {
    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    @Cacheable(cacheNames = "category-cache")
    @Override
    public List<CategoryDTO> getAll() {
        log.info("Fetching all Categories");

        List<Category> categories = repository.findAll();
        List<CategoryDTO> categoriesDTO = new LinkedList<>();
        for (Category category : categories){
            categoriesDTO.add(mapper.toDTO(category));
        }

        log.info("Categories fetched successfully");
        return categoriesDTO;
    }
}
