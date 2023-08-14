package org.launchcode.liftoffproject.controllers;


import org.hibernate.Session;
import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.RewardRepository;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.models.Reward;
import org.launchcode.liftoffproject.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("rewards")
public class RewardController {

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private AuthenticationController authenticationController;

    @Autowired
    private ChildRepository childRepository;

    @GetMapping
    public String displayAllRewards(Model model, HttpSession session) {
        model.addAttribute("title","All Rewards");

        if (authenticationController.getChildFromSession(session) != null) {
            Child child = authenticationController.getChildFromSession(session);
            model.addAttribute("child", child);
            model.addAttribute("rewards", rewardRepository.findAllByParentCreator(child.getParent()));
        } else {
            Parent parent = authenticationController.getParentFromSession(session);
            model.addAttribute("rewards", rewardRepository.findAllByParentCreator(parent));
        }

        return "rewards/index";
    };

    @PostMapping("create")
    public String processCreateRewardForm(@ModelAttribute @Valid Reward newReward, Errors errors, Model model, HttpSession session) {
        if (errors.hasErrors()) {
            model.addAttribute("title","New Reward");
//            model.addAttribute(new Reward());
            return "redirect:/rewards";
        }
        Parent parent = authenticationController.getParentFromSession(session);
        newReward.setParentCreator(parent);
        rewardRepository.save(newReward);
        return "redirect:/rewards";
    }

    @PostMapping("delete")
    public String processDeleteRewardsForm(@RequestParam("rewardIds") int[] rewardIds) {

        if (rewardIds != null) {
            for (int id : rewardIds) {
                rewardRepository.deleteById(id);
            }
        }

        return "redirect:/rewards";
    }

    @GetMapping("edit")
    public String displayEditRewardForm(@RequestParam("rewardId") int rewardId, Model model) {
        Reward reward = rewardRepository.findById(rewardId).orElse(null);
        if (reward == null) {
            return "redirect:/rewards";
        }
        model.addAttribute("title", "Edit Reward");
        model.addAttribute("reward", reward);
        return "rewards/edit";
    }

    @PostMapping("edit")
    public String processEditRewardForm(@RequestParam(required = false) int rewardId, @ModelAttribute Reward reward) {

        Reward updatedReward = rewardRepository.findById(rewardId).orElse(null);

        updatedReward.setCategory(reward.getCategory());
        updatedReward.setName(reward.getName());
        updatedReward.setDescription(reward.getDescription());
        updatedReward.setPoints(reward.getPoints());
        rewardRepository.save(updatedReward);
        return "redirect:/rewards";
    }

    @PostMapping("redeemReward")
    public String processRedeemReward(@RequestParam int rewardId, HttpSession session) {
        Child child = authenticationController.getChildFromSession(session);
        Reward redeemedReward = rewardRepository.findById(rewardId).orElse(null);

        if (child != null && redeemedReward != null && !redeemedReward.isRedeemed()) {
            redeemedReward.setRedeemed(true);
            redeemedReward.setRedemptionDate(LocalDate.now());
            redeemedReward.setChild(child);
            rewardRepository.save(redeemedReward);

            // Reduce child's earned points by the reward points
            child.setPoints(child.getPoints() - redeemedReward.getPoints());
            childRepository.save(child);


        }
        return "redirect:/rewards";
    }

    @PostMapping("fulfillReward")
    public String processFulfillReward(@RequestParam int rewardId, HttpSession session) {
        Reward fulfilledReward = rewardRepository.findById(rewardId).orElse(null);

        if (fulfilledReward != null) {
            if (!fulfilledReward.isFulfilled()) {
                fulfilledReward.setFulfilled(true);

                rewardRepository.save(fulfilledReward);
            }
        }
        return "redirect:";
    }



}

