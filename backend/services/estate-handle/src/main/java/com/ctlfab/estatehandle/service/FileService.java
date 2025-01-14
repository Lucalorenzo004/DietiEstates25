package com.ctlfab.estatehandle.service;

import com.ctlfab.estatehandle.dto.EstateDTO;
import com.ctlfab.estatehandle.dto.FileDTO;

import java.util.List;

public interface FileService {
    List<FileDTO> getFilesByEstateId(long estateId);
    FileDTO saveFile(FileDTO fileDTO, EstateDTO estateDTO);
    FileDTO updateFile(FileDTO fileDTO, EstateDTO estateDTO);
    boolean deleteFile(long id);
}
