package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Reward;
import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.dto.RewardFormDTO;
import org.launchcode.liftoffproject.data.RewardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    private RewardRepository rewardRepository;

    @Override
    public List<Reward> getRewardsByChildUsername(String childUsername) {

        // return rewardRepository.findByChildUsername(childUsername);
        return null;
    }

    @Override
    public Reward getRewardById(Long id) {

        // return rewardRepository.findById(id).orElse(null);
        return null;
    }

    @Override
    public void saveReward(Reward reward) {

        // rewardRepository.save(reward);
    }

    @Override
    public void addRewardFromForm(RewardFormDTO rewardFormDTO) {

        // Reward newReward = new Reward();
        // newReward.setName(rewardFormDTO.getName());
        // newReward.setDescription(rewardFormDTO.getDescription());
        // newReward.setPoints(rewardFormDTO.getPoints());
        // rewardRepository.save(newReward);
    }

    @Override
    public List<Reward> getEarnedRewardsByChild(Child child) {

        // return rewardRepository.findByEarnedBy(child);
        return null;
    }
}
