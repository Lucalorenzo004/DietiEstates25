package com.ctlfab.estatehandle.controller.v1;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.dto.LocationDTO;
import com.ctlfab.estatehandle.model.Location;
import com.ctlfab.estatehandle.model.Response;
import com.ctlfab.estatehandle.service.AwsService;
import com.ctlfab.estatehandle.service.EstateService;
import com.ctlfab.estatehandle.service.FileService;
import com.ctlfab.estatehandle.service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final AwsService awsService;
    private final FileService fileService;
    private final EstateService estateService;
    private final LocationService locationService;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

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

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> addEstate(@RequestParam("data") String estateJson, @RequestParam("files") List<MultipartFile> files) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EstateDTO estateDTO = mapper.readValue(estateJson, EstateDTO.class);

            LocationDTO location = locationService.saveLocation(estateDTO.getLocation());
            estateDTO.setLocation(location);
            EstateDTO newEstate = estateService.addEstate(estateDTO);
            newEstate.setFiles(uploadFile(files, newEstate));

            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .message("Estate saved")
                            .httpStatus(CREATED)
                            .statusCode(CREATED.value())
                            .data(Map.of("estate", newEstate))
                            .build()
            );
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(
            Response.builder()
                    .timestamp(now())
                    .message(e.getMessage())
                    .httpStatus(BAD_REQUEST)
                    .statusCode(BAD_REQUEST.value())
                    .build()
            );
        }
    }

    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> editEstate(@RequestBody @Valid EstateDTO estateDTO) {
        return null;
    }


    @DeleteMapping
    public ResponseEntity<Response> deleteEstate(@Param("estate") long estateId) {
        List<FileDTO> filesDTO = fileService.getFilesByEstateId(estateId);
        for (FileDTO fileDTO : filesDTO) {
            fileService.deleteFile(fileDTO.getId());
            awsService.deleteFile(bucket, fileDTO.getName());
        }

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


    private List<FileDTO> uploadFile(List<MultipartFile> multipartFiles, EstateDTO estateDTO) {
        List<FileDTO> filesDTO = new ArrayList<>();

        try {
            for(MultipartFile file : multipartFiles ){
                FileDTO estateFile = FileDTO.builder()
                        .bucket(bucket)
                        .name(file.getOriginalFilename())
                        .contentType(file.getContentType())
                        .size(file.getSize())
                        .build();


                awsService.uploadFile(estateFile, bucket, file.getInputStream());
                filesDTO.add(fileService.saveFile(estateFile, estateDTO));
            }
        } catch (IOException e) {
            log.warn(e.getMessage());
        }

        return filesDTO;
    }
}
