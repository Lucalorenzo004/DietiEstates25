package com.ctlfab.estatehandle.controller.v1;

import static java.time.LocalDateTime.now;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.EstateResponse;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.service.EstateService;
import com.ctlfab.estatehandle.service.FileService;
import com.ctlfab.estatehandle.service.LocationService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final EstateService estateService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<EstateDTO>>> getAllEstates(){

         Meta meta = new Meta(now(), "v1");
         String status = "Estate retrieved";
         List<EstateDTO> estateDTOList = estateService.getAllEstates();
         ApiResponse<List<EstateDTO>> response = new ApiResponse<>(status, estateDTOList, meta);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles HTTP POST requests to create a new estate.
     *
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
     *
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
     *
     * @param estateId The ID of the estate to be deleted.
     * @return A {@link ResponseEntity} containing a standardized response with a success message if the deletion was successful.
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<EstateDTO>> deleteEstate(@Param("estate") long estateId) {
        Meta meta = new Meta(now(), "v1");
        String status = "Estate deleted";
        ApiResponse<EstateDTO> response = new ApiResponse<>(status, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

