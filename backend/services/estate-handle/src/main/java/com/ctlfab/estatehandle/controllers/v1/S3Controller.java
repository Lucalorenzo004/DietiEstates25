package com.ctlfab.estatehandle.controllers.v1;

import com.ctlfab.estatehandle.serialization.ApiResponse;
import com.ctlfab.estatehandle.serialization.Meta;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.DeleteObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.time.Duration;

import static java.time.LocalDateTime.now;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@RequestMapping("/estate-handle-api/v1/s3")
public class S3Controller {

    private final S3Presigner s3Presigner;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    /**
     * Handles HTTP GET requests to generate a pre-signed URL for uploading a file to S3.
     * @param file The name of file to be uploaded.
     * @return A ResponseEntity containing the pre-signed URL as a string.
     */
    @GetMapping("/presigned-upload")
    public ResponseEntity<ApiResponse<String>> getPresignedUrl(@RequestParam(value = "file") String file) {

        PutObjectPresignRequest presignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(10))
                .putObjectRequest(object -> object.bucket(bucket).key(file).build())
                .build();

        String presignedUrl = s3Presigner.presignPutObject(presignRequest).url().toString();
        Meta meta = new Meta(now(), "v1");
        String status = "UrlPresigned generated";
        ApiResponse<String> response = new ApiResponse<>(status, presignedUrl, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Handles HTTP GET requests to generate a pre-signed URL for deleting a file from S3.
     * @param file The name of the file to be deleted.
     * @return A ResponseEntity containing the pre-signed URL as a string.
     */
    @GetMapping("/presigned-delete")
    public ResponseEntity<ApiResponse<String>> getPresignedDeleteUrl(@RequestParam(value = "file") String file) {

        DeleteObjectPresignRequest presignRequest = DeleteObjectPresignRequest.builder()
                .signatureDuration(Duration.ofMinutes(5))
                .deleteObjectRequest(object -> object.bucket(bucket).key(file).build())
                .build();

        String presignedUrl = s3Presigner.presignDeleteObject(presignRequest).url().toString();

        Meta meta = new Meta(now(), "v1");
        String status = "Presigned URL for delete generated";
        ApiResponse<String> response = new ApiResponse<>(status, presignedUrl, meta);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
