package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    private NoteService noteService;
    private UserService userService;

    public NoteController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/notes")
    public ModelAndView postNote(@ModelAttribute Note noteForm, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        Integer userid = user.getUserId();

        try {
            Integer noteid = noteForm.getNoteid();
            noteService.createOrUpdateNote(noteForm, userid, noteid);
            model.addAttribute("success",true);
            String message = noteid != null ? "Note updated!" : "New note added!";
            model.addAttribute("message", message);
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }

        return new ModelAndView("result");
    }

    @PostMapping("/notes/delete")
    public ModelAndView deleteNote(@ModelAttribute Note noteDelete, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        Integer userid = user.getUserId();

        try {
            noteService.deleteNote(noteDelete, userid);
            model.addAttribute("success",true);
            model.addAttribute("message", "Note deleted!");
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }

        return new ModelAndView("result");
    }
}
