package org.launchcode.liftoffproject.controllers;

import org.launchcode.liftoffproject.data.ChildRepository;
import org.launchcode.liftoffproject.data.EarnedRewardsRepository;
import org.launchcode.liftoffproject.data.RewardRepository;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.models.Reward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
        model.addAttribute("rewards", rewardRepository.findAll());

        if (authenticationController.getChildFromSession(session) != null) {
            Child child = authenticationController.getChildFromSession(session);
            model.addAttribute("child", child);
        }

        return "rewards/index";
    };

    @PostMapping("create")
    public String processCreateRewardForm(@ModelAttribute @Valid Reward newReward, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title","New Reward");
            model.addAttribute(new Reward());
            return "redirect:/rewards";
        }
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

        updatedReward.setName(reward.getName());
        updatedReward.setDescription(reward.getDescription());
        updatedReward.setPoints(reward.getPoints());
        rewardRepository.save(updatedReward);
        return "redirect:/rewards";
    }

}
