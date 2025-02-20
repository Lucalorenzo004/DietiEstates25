package com.ctlfab.estatehandle.controllers.v1;

import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.serialization.ApiResponse;
import com.ctlfab.estatehandle.serialization.Meta;
import com.ctlfab.estatehandle.services.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/files")
public class FileController {
    private final FileService service;

    /**
     * Handles HTTP DELETE requests to delete an existing file.
     * @param file File's name to be deleted.
     * @return A {@link ResponseEntity} containing a standardized response with a success message if the deletion was successful.
     */
    @DeleteMapping
    public ResponseEntity<ApiResponse<FileDTO>> deleteFile(@RequestParam("file") String file) {
        service.delete(file);

        Meta meta = new Meta(now(), "v1");
        String status = "File deleted";
        ApiResponse<FileDTO> response = new ApiResponse<>(status, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
