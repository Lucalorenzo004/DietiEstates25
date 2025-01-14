package com.ctlfab.estatehandle.service;

import com.amazonaws.AmazonClientException;
import com.ctlfab.estatehandle.dto.FileDTO;
import com.ctlfab.estatehandle.model.File;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface AwsService {

    void uploadFile(final FileDTO file, final String bucket, final InputStream value) throws AmazonClientException;

    ByteArrayOutputStream downloadFile(final String bucket, final String fileName) throws IOException, AmazonClientException;

    List<String> listFiles(final String bucket) throws AmazonClientException;

    void deleteFile(final String bucket, final String fileName) throws AmazonClientException;
}