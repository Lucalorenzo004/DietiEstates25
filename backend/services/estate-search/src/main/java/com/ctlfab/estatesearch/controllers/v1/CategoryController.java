package com.ctlfab.estatesearch.controllers.v1;

import com.ctlfab.estatesearch.dto.CategoryDTO;
import com.ctlfab.estatesearch.serialization.ApiResponse;
import com.ctlfab.estatesearch.serialization.Meta;
import com.ctlfab.estatesearch.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-search-api/v1/categories")
public class CategoryController {
    private final CategoryService service;

    /**
     * Handles HTTP GET requests to fetch categories.
     * @return A {@link ResponseEntity} containing a standardized response with a List of {@link CategoryDTO}.
     */
    @GetMapping
    public ResponseEntity<ApiResponse<List<CategoryDTO>>> getCategories() {
        Meta meta = new Meta(now(), "v1");
        String status = "Categories retrieved";
        List<CategoryDTO> categories = service.getAll();
        ApiResponse<List<CategoryDTO>> response = new ApiResponse<>(status, categories, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
