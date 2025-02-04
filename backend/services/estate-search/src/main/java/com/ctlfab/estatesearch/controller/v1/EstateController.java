package com.ctlfab.estatesearch.controller.v1;

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

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstateDTO>>> getEstateByFilter(@RequestBody(required = false) FilterDTO filterDTO){

         Meta meta = new Meta(now(), "v1");
         String status = "Estate retrieved";
         List<EstateDTO> estateDTOList = estateService.getAllEstates(filterDTO);
         ApiResponse<List<EstateDTO>> response = new ApiResponse<>(status, estateDTOList, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

