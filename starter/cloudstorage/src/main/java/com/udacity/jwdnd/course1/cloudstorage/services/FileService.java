package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(MultipartFile file, Integer userId) throws IOException {
        String filename = file.getOriginalFilename();
        String contenttype = file.getContentType();
        Long filesize = file.getSize();
        byte[] filedata = file.getBytes();

        return fileMapper.insert(new File(null, filename, contenttype, filesize, filedata, userId));
    }
}