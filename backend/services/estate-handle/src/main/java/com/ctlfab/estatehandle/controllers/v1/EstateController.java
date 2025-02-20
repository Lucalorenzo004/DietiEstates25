package com.ctlfab.estatehandle.controllers.v1;

import static java.time.LocalDateTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctlfab.estatehandle.dto.EstateDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import com.ctlfab.estatehandle.serialization.ApiResponse;
import com.ctlfab.estatehandle.serialization.Meta;
import com.ctlfab.estatehandle.services.EstateService;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final EstateService estateService;

    /**
     * Handles HTTP POST requests to create a new estate.
     * @param estateDTO The EstateDTO containing details of the estate to be saved
     * @return A {@link ResponseEntity} containing a standardized response with the saved estate data.
     */
    @PostMapping
    public ResponseEntity<ApiResponse<EstateDTO>> saveEstate(@RequestBody @Valid EstateDTO estateDTO) {
        EstateDTO savedEstate = estateService.save(estateDTO);

        Meta meta = new Meta(now(), "v1");
        String status = "Estate saved";
        ApiResponse<EstateDTO> response = new ApiResponse<>(status, savedEstate, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles HTTP PUT requests to update an existing estate.
     * @param estateDTO The EstateDTO containing the updated estate information.
     * @return A {@link ResponseEntity} containing a standardized response with the updated estate data.
     */
    @PutMapping()
    public ResponseEntity<ApiResponse<EstateDTO>> editEstate(@RequestBody @Valid EstateDTO estateDTO) {
        EstateDTO updatedEstate  = estateService.save(estateDTO);

        Meta meta = new Meta(now(), "v1");
        String status = "Estate updated";
        ApiResponse<EstateDTO> response = new ApiResponse<>(status, updatedEstate, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles HTTP DELETE requests to delete an existing estate.
     * @param estateId The ID of the estate to be deleted.
     * @return A {@link ResponseEntity} containing a standardized response with a success message if the deletion was successful.
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<EstateDTO>> deleteEstate(@RequestParam(value = "estate") Long estateId) {
        estateService.delete(estateId);

        Meta meta = new Meta(now(), "v1");
        String status = "Estate deleted";
        ApiResponse<EstateDTO> response = new ApiResponse<>(status, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

