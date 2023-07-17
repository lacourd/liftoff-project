package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.RewardRepository;
import org.launchcode.liftoffproject.data.UserRepository;
import org.launchcode.liftoffproject.models.*;
import org.launchcode.liftoffproject.models.dto.AddCrewFormDTO;
import org.launchcode.liftoffproject.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("crew")
public class CrewController {

    @Autowired
    private ChildRepository childRepository;

    @Autowired
    private UserRepository userRepository;

    private static final String userSessionKey = "user";

    public Parent getParentFromSession(HttpSession session) {
        Integer parentId = (Integer) session.getAttribute(userSessionKey);
        if (parentId == null) {
            return null;
        }

        Optional<User> parent = userRepository.findById(parentId);

        if (parent.isEmpty()) {
            return null;
        }

        return (Parent) parent.get();
    }

    @GetMapping
    public String displayAllCrewMembers(Model model) {
        model.addAttribute("title","All Crew Members");
        model.addAttribute("crew", Parent.getChildren());
        return "crew/index";
    };

    @GetMapping("add")
    public String renderAddCrewMemberForm(Model model) {
        model.addAttribute("title","Add a Crew Member");
        model.addAttribute(new AddCrewFormDTO());
        return "crew/add";
    };

    @PostMapping("add")
    public String processAddCrewMemberForm(@ModelAttribute @Valid AddCrewFormDTO addCrewFormDTO, Errors errors, HttpServletRequest request, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","Add a Crew Member");
            return "crew/add";
        }

        User existingUser = userRepository.findByUsername(addCrewFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "A crew member with that username already exists");
            model.addAttribute("title","Add a Crew Member");
            return "crew/add";
        }

        String password = addCrewFormDTO.getPassword();
        String verifyPassword = addCrewFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title","Add a Crew Member");
            return "crew/add";
        }

        Parent parent = getParentFromSession(request.getSession());
        Child newChild = new Child(addCrewFormDTO.getFirstName(), addCrewFormDTO.getLastName(), parent);
        childRepository.save(newChild);
        ChildUser newChildUser = new ChildUser(addCrewFormDTO.getUsername(), addCrewFormDTO.getPassword());
        userRepository.save(newChildUser);
        userRepository.save(parent);
        return "redirect:";
    }

}
