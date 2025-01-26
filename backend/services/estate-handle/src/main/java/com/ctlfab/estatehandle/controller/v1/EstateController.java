package com.ctlfab.estatehandle.controller.v1;

import com.ctlfab.estatehandle.dto.*;
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
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final EstateService estateService;
    private final FileService fileService;
    private final LocationService locationService;

    @GetMapping
    public ResponseEntity<EstateResponse> getAllEstates(){
        List<EstateDTO> estateDTOList = estateService.getAllEstates();

        return ResponseEntity.ok(
                buildResponse(estateDTOList, "Estate retrieved saved", OK)
        );
    }

    /**
     * Handles the creation of a new estate along with its associated location and files.
     *
     * @param estateDTO The DTO containing details of the estate to be saved
     * @return A {@link ResponseEntity} containing a standardized response with the saved estate data.
     */
    @PostMapping
    public ResponseEntity<EstateResponse> saveEstate(@RequestBody @Valid EstateDTO estateDTO) {
        LocationDTO newlocation = locationService.saveLocation(estateDTO.getLocation());
        EstateDTO newEstate = estateService.saveEstate(estateDTO);

        List<FileDTO> newFiles = estateDTO.getFiles().stream()
                .map(fileDTO -> fileService.saveFile(fileDTO, newEstate))
                .toList();

        newEstate.setLocation(newlocation);
        newEstate.setFiles(newFiles);

        return ResponseEntity.ok(
                buildResponse(newEstate, "Estate saved", CREATED)
        );
    }

    /**
     * Updates an existing estate and its associated files and location.
     *
     * @param estateDTO The DTO containing the updated estate information, including its files and location.
     * @return A {@link ResponseEntity} containing a standardized response with the updated estate data.
     */
    @PutMapping()
    public ResponseEntity<EstateResponse> editEstate(@RequestBody @Valid EstateDTO estateDTO) {
        LocationDTO updatedLocation  = locationService.editLocation(estateDTO.getLocation());
        EstateDTO updatedEstate  = estateService.editEstate(estateDTO);

        List<FileDTO> updatedFiles = estateDTO.getFiles().stream()
                        .map(fileDTO -> fileService.editFile(fileDTO, updatedEstate))
                        .toList();

        updatedEstate.setLocation(updatedLocation);
        updatedEstate.setFiles(updatedFiles);

        return ResponseEntity.ok(
                buildResponse(updatedEstate, "Estate updated", OK)
        );
    }

    /**
     * Deletes an estate and all associated files.
     *
     * @param estateId The ID of the estate to be deleted.
     * @return A {@link ResponseEntity} containing a standardized response with a success message if the deletion was successful.
     */
    @DeleteMapping
    public ResponseEntity<EstateResponse> deleteEstate(@Param("estate") long estateId) {
        List<FileDTO> filesDTO = fileService.getFilesByEstateId(estateId);
        for (FileDTO fileDTO : filesDTO) {
            fileService.deleteFile(fileDTO.getId());
        }

        return ResponseEntity.ok(
                buildResponse(Map.of("estate", estateService.deleteEstate(estateId)),"Estate deleted", OK)
        );
    }

    /**
     * Builds a standardized response for operations related to the Estate entity.
     *
     * @param <T>       The generic type of the data contained in the response.
     * @param data      The object containing the data to be included in the response. Can be of any type.
     * @param message   A descriptive message accompanying the response, useful for context.
     * @param httpStatus The HTTP status associated with the response (e.g., 200 OK, 201 CREATED, 400 BAD REQUEST, etc.).
     * @return          An {@link EstateResponse} object built with the provided information.
     */
    private <T> EstateResponse buildResponse(T data, String message, HttpStatus httpStatus) {
        return EstateResponse.builder()
                .timestamp(now())
                .message(message)
                .httpStatus(httpStatus)
                .statusCode(httpStatus.value())
                .data(Map.of("estate", data))
                .build();
    }
}

