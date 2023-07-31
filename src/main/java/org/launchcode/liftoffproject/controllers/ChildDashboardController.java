package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.ChildUser;
import org.launchcode.liftoffproject.models.User;
import org.launchcode.liftoffproject.services.ChildService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Controller
@RequestMapping("/child")
public class ChildDashboardController {

    @Autowired
    private ChildService childService;

    private static final String userSessionKey = "user";

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }


        return childService.getUserById(userId);
    }

    @GetMapping("/dashboard")
    public String childDashboard(HttpSession session, Model model) {

        User user = getUserFromSession(session);

        // Am I a child?
        if (user == null || !(user instanceof ChildUser)) {
            // If not
            return "redirect:/login";
        }

        ChildUser childUser = (ChildUser) user;

        // Dashboard
        Child child = childService.getChildByUsername(childUser.getUsername());
        model.addAttribute("child", child);
        model.addAttribute("earnedRewards", childService.getEarnedRewards(child.getUsername()));
        model.addAttribute("chores", childService.getChores(child.getUsername()));

        return "/child/dashboard";
    }
}
