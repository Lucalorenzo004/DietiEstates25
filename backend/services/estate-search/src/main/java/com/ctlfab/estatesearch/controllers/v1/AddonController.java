package com.ctlfab.estatesearch.controllers.v1;

import com.ctlfab.estatesearch.dto.AddonDTO;
import com.ctlfab.estatesearch.serialization.ApiResponse;
import com.ctlfab.estatesearch.serialization.Meta;
import com.ctlfab.estatesearch.services.AddonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-search-api/v1/addons")
public class AddonController {
    private final AddonService service;

    @GetMapping
    public ResponseEntity<ApiResponse<List<AddonDTO>>> getAddons() {
        Meta meta = new Meta(now(), "v1");
        String status = "Addons retrieved";
        List<AddonDTO> addons = service.getAll();
        ApiResponse<List<AddonDTO>> response = new ApiResponse<>(status, addons, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
