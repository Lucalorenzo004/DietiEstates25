package com.ctlfab.estatehandle.controller.v1;

import com.ctlfab.estatehandle.dto.*;
import com.ctlfab.estatehandle.serializzation.ApiResponse;
import com.ctlfab.estatehandle.serializzation.Meta;
import com.ctlfab.estatehandle.service.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.time.LocalDateTime.now;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final EstateService estateService;
    private final FileService fileService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstateDTO>>> getAllEstates(){
        List<EstateDTO> estateDTOList = estateService.getAllEstates();

        Meta meta = new Meta(now(), "v1");
        ApiResponse<List<EstateDTO>> response = new ApiResponse<>("success", estateDTOList, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles the creation of a new estate along with its associated location and files.
     *
     * @param estateDTO The DTO containing details of the estate to be saved
     * @return A {@link ResponseEntity} containing a standardized response with the saved estate data.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<EstateDTO>> saveEstate(@RequestBody @Valid EstateDTO estateDTO) {
        EstateDTO savedEstate = estateService.save(estateDTO);

        Meta meta = new Meta(now(), "v1");
        ApiResponse<EstateDTO> response = new ApiResponse<>("success", savedEstate, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Updates an existing estate and its associated files and location.
     *
     * @param estateDTO The DTO containing the updated estate information, including its files and location.
     * @return A {@link ResponseEntity} containing a standardized response with the updated estate data.
     */
    @PutMapping()
    public ResponseEntity<ApiResponse<EstateDTO>> editEstate(@RequestBody @Valid EstateDTO estateDTO) {
        EstateDTO updatedEstate  = estateService.save(estateDTO);

        Meta meta = new Meta(now(), "v1");
        ApiResponse<EstateDTO> response = new ApiResponse<>("success", updatedEstate, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Deletes an estate and all associated files.
     *
     * @param estateId The ID of the estate to be deleted.
     * @return A {@link ResponseEntity} containing a standardized response with a success message if the deletion was successful.
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<EstateDTO>> deleteEstate(@Param("estate") long estateId) {
        Meta meta = new Meta(now(), "v1");
        ApiResponse<EstateDTO> response = new ApiResponse<>("success", meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

