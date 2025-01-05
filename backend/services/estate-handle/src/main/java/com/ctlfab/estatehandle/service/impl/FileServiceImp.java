package com.ctlfab.estatehandle.service.impl;

import com.ctlfab.estatehandle.service.FileService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Service
@Transactional
public class FileServiceImp implements FileService {
}
