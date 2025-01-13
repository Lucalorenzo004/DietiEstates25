package com.ctlfab.estatehandle.controller.v1;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.model.Response;
import com.ctlfab.estatehandle.service.EstateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final EstateService estateService;

    @GetMapping
    public ResponseEntity<Response> getAllEstates(){
        List<EstateDTO> estateDTOList = estateService.getAllEstates();

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .message("Estate retrieved")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .data(Map.of("estates", estateDTOList))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Response> addEstate(@RequestBody @Valid EstateDTO estateDTO) {
            EstateDTO newEstate = estateService.addEstate(estateDTO);

            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .message("Estate saved")
                            .httpStatus(CREATED)
                            .statusCode(CREATED.value())
                            .data(Map.of("estate", newEstate))
                            .build()
            );
    }

    @PutMapping
    public ResponseEntity<Response> editEstate(@RequestBody @Valid EstateDTO estateDTO) {
        return null;
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteEstate(@Param("estate") long estateId) {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(now())
                        .message("Estate deleted")
                        .httpStatus(OK)
                        .statusCode(OK.value())
                        .data(Map.of("estate", estateService.deleteEstate(estateId)))
                        .build()
        );
    }
}
