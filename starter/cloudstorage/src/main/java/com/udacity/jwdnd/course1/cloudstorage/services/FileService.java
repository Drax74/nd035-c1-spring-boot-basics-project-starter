package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

    public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public int createFile(MultipartFile uploadedFile, Integer userId) throws IOException {
        File file = new File();
        file.setFilename(uploadedFile.getOriginalFilename());
        file.setContenttype(uploadedFile.getContentType());
        file.setFilesize(uploadedFile.getSize());
        file.setFiledata(uploadedFile.getBytes());
        file.setUserid(userId);

        return fileMapper.insert(file);
    }

    public Boolean filenameExists(String filename, Integer userid) {
        return !fileMapper.getByUsername(filename, userid).isEmpty();
    }

    public List<File> getFiles(Integer userid) {
        return fileMapper.getFiles(userid);
    }

    public File findById(Integer fileId) {
        return fileMapper.getFileById(fileId);
    }

    public int deleteFile(File file, Integer userid) {
        file.setUserid(userid);
        return fileMapper.delete(file);
    }
}