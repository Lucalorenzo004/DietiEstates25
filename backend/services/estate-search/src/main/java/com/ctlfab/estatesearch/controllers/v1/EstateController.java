package com.ctlfab.estatesearch.controllers.v1;

import com.ctlfab.estatesearch.dto.EstateDTO;
import com.ctlfab.estatesearch.dto.FilterDTO;
import com.ctlfab.estatesearch.serialization.ApiResponse;
import com.ctlfab.estatesearch.serialization.Meta;
import com.ctlfab.estatesearch.services.EstateService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-search-api/v1/estates")
public class EstateController {
    private final EstateService estateService;

    /**
     * Handles HTTP GET requests to fetch estate data.
     * @param id ID of {@link EstateDTO} to fetch.
     * @return A {@link ResponseEntity} containing a standardized response with {@link EstateDTO} data.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<EstateDTO>> getEstateById(@PathVariable("id") Long id) {
        Meta meta = new Meta(now(), "v1");
        String status = "Estate retrieved";
        EstateDTO estateDTO = estateService.getById(id);
        ApiResponse<EstateDTO> response = new ApiResponse<>(status, estateDTO, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles HTTP POST requests to fetch estate data by filter.
     * @param filterDTO Filter criteria {@link FilterDTO} to be applied for fetching estate.
     * @return A {@link ResponseEntity} containing a standardized response with a List of {@link EstateDTO} data.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<List<EstateDTO>>> getEstateByFilter(@RequestBody(required = false) FilterDTO filterDTO){
         Meta meta = new Meta(now(), "v1");
         String status = "Estate retrieved";

         List<EstateDTO> estateDTOList;
         if(filterDTO.isFavorite()){
            estateDTOList = estateService.getFavorite(filterDTO.getUserId());
         }else {
             estateDTOList = estateService.getAll(filterDTO);
         }

         ApiResponse<List<EstateDTO>> response = new ApiResponse<>(status, estateDTOList, meta);
         return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

