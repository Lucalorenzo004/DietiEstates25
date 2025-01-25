package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.FileDTO;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

/**
 * AWS Service
 * Author: Fabrizio Ciotola
 */
public interface AwsService {

    /**
     * Upload file on AWS
     * @param file File to upload
     * @param bucket Bucket
     * @param value Value of file
     */
    void uploadFile(FileDTO file, String bucket, InputStream value);

    List<String> listFiles(String bucket);

    /**
     * Download file from AWS
     * @param bucket Bucket
     * @param fileName Name of file
     * @return ByteArrayOutputStream
     */
    ByteArrayOutputStream downloadFile(String bucket, String fileName);

    /**
     * Delete file on AWS
     * @param bucket Bucket
     * @param fileName Name of file
     */
    void deleteFile(final String bucket, final String fileName);
}