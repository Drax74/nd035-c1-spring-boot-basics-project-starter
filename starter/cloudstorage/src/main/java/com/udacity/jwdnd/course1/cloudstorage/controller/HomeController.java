package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/home")
public class HomeController {

    private CredentialService credentialService;
    private UserService userService;
    private NoteService noteService;
    private FileService fileService;

    public HomeController(CredentialService credentialService, UserService userService, NoteService noteService, FileService fileService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @GetMapping
    public String getHomePage(Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        Integer userId = user.getUserId();

        model.addAttribute("credentials", this.credentialService.getCredentials(userId));
        model.addAttribute("notes", this.noteService.getNotes(userId));
        model.addAttribute("files", this.fileService.getFiles(userId));
        model.addAttribute("noteForm", new Note());
        model.addAttribute("noteDelete", new Note());
        model.addAttribute("credentialForm", new Credential());
        model.addAttribute("credentialDelete", new Credential());
        model.addAttribute("fileDelete", new File());
        return "home";
    }
}