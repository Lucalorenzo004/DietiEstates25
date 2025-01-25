package com.ctlfab.estatehandle.service.impl;

import com.amazonaws.AmazonClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.model.File;
import com.ctlfab.estatehandle.service.AwsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
public class AwsServiceImp implements AwsService {
    private final AmazonS3 s3Client;

    @Override
    public void uploadFile(FileDTO file, String bucket, InputStream value){
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        s3Client.putObject(bucket, file.getName(), value, metadata);
        log.info("File uploaded to bucket({}): {}", bucket, file.getName());
    }

    @Override
    public ByteArrayOutputStream downloadFile(String bucket, String fileName){
        S3Object s3Object = s3Client.getObject(bucket, fileName);
        InputStream inputStream = s3Object.getObjectContent();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try{
            int len;
            byte[] buffer = new byte[4096];
            while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            log.info("File downloaded from bucket({}): {}", bucket, fileName);
        }catch (IOException e){
            log.warn("Something was gone wrong ");
        }

        return outputStream;
    }

    @Override
    public List<String> listFiles(String bucket){
        List<String> keys = new ArrayList<>();
        ObjectListing objectListing = s3Client.listObjects(bucket);

        while (true) {
            List<S3ObjectSummary> objectSummaries = objectListing.getObjectSummaries();
            if (objectSummaries.isEmpty()) {
                break;
            }

            objectSummaries.stream()
                    .map(S3ObjectSummary::getKey)
                    .filter(key -> !key.endsWith("/"))
                    .forEach(keys::add);

            objectListing = s3Client.listNextBatchOfObjects(objectListing);
        }

        log.info("Files found in bucket({}): {}", bucket, keys);
        return keys;
    }

    @Override
    public void deleteFile(String bucket, String fileName) throws AmazonClientException {
        s3Client.deleteObject(bucket, fileName);
        log.info("File deleted from bucket({}): {}", bucket, fileName);
    }
}
