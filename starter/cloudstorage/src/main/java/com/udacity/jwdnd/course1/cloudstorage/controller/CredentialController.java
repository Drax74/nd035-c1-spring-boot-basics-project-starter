package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CredentialController {
    private CredentialService credentialService;
    private UserService userService;

    public CredentialController(CredentialService credentialService, UserService userService) {
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/credentials")
    public ModelAndView postCredential(@ModelAttribute Credential credentialForm, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        Integer userid = user.getUserId();

        try {
            Integer credentialid = credentialForm.getCredentialid();
            credentialService.createOrUpdateNote(credentialForm, userid, credentialid);
            model.addAttribute("success",true);
            String message = credentialid != null ? "Credential updated!" : "New credential added!";
            model.addAttribute("message", message);
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }

        return new ModelAndView("result");
    }

    @PostMapping("/credentials/delete")
    public ModelAndView deleteNote(@ModelAttribute Credential credentialDelete, Authentication authentication, Model model) {
        User user = this.userService.getUser(authentication.getName());
        Integer userid = user.getUserId();

        try {
            credentialService.deleteCredential(credentialDelete, userid);
            model.addAttribute("success",true);
            model.addAttribute("message", "Credentials deleted!");
        } catch (Exception e) {
            model.addAttribute("error",true);
            model.addAttribute("message","System error!" + e.getMessage());
        }

        return new ModelAndView("result");
    }
}
