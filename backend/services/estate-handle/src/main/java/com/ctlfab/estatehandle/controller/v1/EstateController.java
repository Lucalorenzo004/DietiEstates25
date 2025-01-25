package com.ctlfab.estatehandle.controller.v1;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.mapper.FileMapper;
import com.ctlfab.estatehandle.model.Response;
import com.ctlfab.estatehandle.service.AwsService;
import com.ctlfab.estatehandle.service.EstateService;
import com.ctlfab.estatehandle.service.FileService;
import com.fasterxml.jackson.core.JsonProcessingException;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.*;


/**
 *  Endpoint for handling creation, update and deletion operations concerning estates
 *  Author: Fabrizio Ciotola
 */
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/estates")
public class EstateController {
    private final AwsService awsService;
    private final EstateService service;
    private final FileService fileService;

    @Value("${cloud.aws.s3.bucket}")
    private final String bucket;

    @GetMapping
    public ResponseEntity<Response> getAllEstates(){
        List<EstateDTO> estateDTOList = service.getAllEstates();

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

    /**
     * Handling the saving of an Estate
     * @param estateJson JSON representation of an EstateDTO
     * @param multipartFiles Files related to an Estate (e.g., photos, documents)
     * @return ResponseEntity.OK if the save was successful, ResponseEntity.badRequest otherwise
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> addEstate(@RequestParam("data") String estateJson, @RequestParam("files") List<MultipartFile> multipartFiles) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EstateDTO estateDTO = mapper.readValue(estateJson, EstateDTO.class);

            List<FileDTO> filesDTO = new LinkedList<>();
            for(MultipartFile multipartFile : multipartFiles){
                FileDTO fileDTO = FileMapper.mapToDTO(multipartFile, bucket);
                filesDTO.add(fileDTO);
                awsService.uploadFile(fileDTO, bucket, multipartFile.getInputStream());

            }

            EstateDTO newEstate = service.addEstate(estateDTO, filesDTO);

            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .message("Estate saved")
                            .httpStatus(CREATED)
                            .statusCode(CREATED.value())
                            .data(Map.of("estate", newEstate))
                            .build()
            );
        } catch (IOException e) {
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

    /**
     * Handling the updating of an Estate
     * @param estateJson JSON representation of an EstateDTO
     * @param multipartFiles Files related to an Estate (e.g., photos, documents)
     * @return ResponseEntity.OK if the update was successful, ResponseEntity.badRequest otherwise
     */
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Response> editEstate(@RequestParam("data") String estateJson, @RequestParam("files") List<MultipartFile> multipartFiles) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            EstateDTO estateDTO = mapper.readValue(estateJson, EstateDTO.class);

            List<FileDTO> filesDTO = new LinkedList<>();
            for(MultipartFile multipartFile : multipartFiles){
                FileDTO fileDTO = FileMapper.mapToDTO(multipartFile, bucket);
                filesDTO.add(fileDTO);
                awsService.uploadFile(fileDTO, bucket, multipartFile.getInputStream());

            }

            EstateDTO newEstate = service.editEstate(estateDTO, filesDTO);

            return ResponseEntity.ok(
                    Response.builder()
                            .timestamp(now())
                            .message("Estate saved")
                            .httpStatus(CREATED)
                            .statusCode(CREATED.value())
                            .data(Map.of("estate", newEstate))
                            .build()
            );
        } catch (IOException e) {
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

    /**
     * Handling the deleting of an Estate
     * @param estateId ID of an EstateDTO
     * @return ResponseEntity.OK if the deletion was successful
     */
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
                        .data(Map.of("estate", service.deleteEstate(estateId)))
                        .build()
        );
    }
}
