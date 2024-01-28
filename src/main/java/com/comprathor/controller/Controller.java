package com.comprathor.controller;

import com.comprathor.model.UserModel;
import com.comprathor.repository.entity.User;
import com.comprathor.service.IUserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;  // Import for Principal
import java.util.List;

import org.springframework.ui.Model;  // Import for Model
import org.springframework.web.bind.annotation.GetMapping;  // Import for GetMapping
import org.springframework.web.bind.annotation.RequestMapping;  // Import for RequestMapping
import org.springframework.web.bind.annotation.RestController;  // Import for RestController


@RestController
@RequestMapping
public class Controller {

    @Autowired
    private IUserService userService;

    @GetMapping("/")
    public String goHome(){
        return "Bienvenido a Comprathor";
    }
    /*@GetMapping(path = "/")
    public String index() {
        return "external";
    }*/

    @GetMapping(path = "/customers")
    public List<UserModel> customers(Principal principal, Model model) {
        List<UserModel> allUsers = userService.getAllUsers();
        model.addAttribute("username", principal.getName());
        return allUsers;
    }

    @GetMapping("/login")
    public String login() {
        return "redirect:/oauth2/authorization/keycloak";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
