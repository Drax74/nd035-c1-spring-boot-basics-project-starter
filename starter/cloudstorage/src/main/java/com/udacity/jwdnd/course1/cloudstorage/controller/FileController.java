package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.*;


@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;

    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/file-upload")
    public String postFile(@RequestParam("fileUpload") MultipartFile fileUpload, Authentication authentication, Model model) throws IOException {
        if(fileUpload.isEmpty()) {
            model.addAttribute("success",false);
            model.addAttribute("message","No file selected to upload!");
            return "home";
        }
        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();
        fileService.createFile(fileUpload, userId);
        model.addAttribute("success",true);
        model.addAttribute("message","New File added successfully!");
        return "home";
    }
}
