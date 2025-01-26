package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.model.File;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.List;

public interface AwsService {
    /**
     * Uploads a file to an AWS S3 bucket.
     *
     * @param file The {@link File} object representing the file to be uploaded.
     * @param bucket The name of the S3 bucket to upload the file to.
     * @param value The {@link InputStream} representing the file's contents.
     */
    void uploadFile(File file, String bucket, InputStream value);

    /**
     * Retrieves a list of file names from a specified S3 bucket.
     *
     * @param bucket The name of the S3 bucket from which to list the files.
     * @return A list of {@link String} representing the names of the files in the S3 bucket.
     */
    List<String> listFiles(String bucket);

    /**
     * Downloads a file from an AWS S3 bucket.
     *
     * @param bucket The name of the S3 bucket from which to download the file.
     * @param fileName The name of the file to be downloaded.
     * @return A {@link ByteArrayOutputStream} containing the downloaded file's contents.
     */
    ByteArrayOutputStream downloadFile(String bucket, String fileName);

    /**
     * Deletes a file from an AWS S3 bucket.
     *
     * @param bucket The name of the S3 bucket from which to delete the file.
     * @param fileName The name of the file to be deleted.
     */
    void deleteFile(final String bucket, final String fileName);
}