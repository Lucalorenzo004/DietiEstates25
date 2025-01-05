package com.ctlfab.estatehandle.controller;

import com.ctlfab.estatehandle.dto.AddonsDTO;
import com.ctlfab.estatehandle.model.Response;
import com.ctlfab.estatehandle.service.AddonsService;
import com.ctlfab.estatehandle.service.EstateService;
import com.ctlfab.estatehandle.service.FileService;
import com.ctlfab.estatehandle.service.LocationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/estate-handle")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class EstateController {
    private final EstateService estateService;
    private final LocationService locationService;
    private final FileService fileService;
    private final AddonsService addonsService;

    @PostMapping("/save")
    public ResponseEntity<Response> addEstate(@RequestBody @Valid AddonsDTO addonsDTO) {
        return null;
    }

    @PutMapping("/edit")
    public ResponseEntity<Response> editEstate(@RequestBody @Valid AddonsDTO addonsDTO){
        return null;
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Response> deleteEstate(@Param("estate") long estateId){
       return null;
    }
}
