//package org.launchcode.liftoffproject.controllers;
//
//import org.launchcode.liftoffproject.models.dto.LoginFormDTO;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.validation.Valid;
//
//@Controller
//public class LoginController {
//
//    @GetMapping("/login")
//    public String displayLoginForm(Model model) {
//        model.addAttribute("loginFormDTO", new LoginFormDTO());
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String processLoginForm(@Valid @ModelAttribute("loginFormDTO") LoginFormDTO loginFormDTO,
//                                   BindingResult result) {
//
//
//        // If login is successful, redirect to the child dashboard page
//        return "redirect:/child/child_dashboard";
//    }
//}
