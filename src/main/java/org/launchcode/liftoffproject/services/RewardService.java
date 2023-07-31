package org.launchcode.liftoffproject.services;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Reward;
import org.launchcode.liftoffproject.models.dto.RewardFormDTO;

import java.util.List;

public interface RewardService {

    List<Reward> getRewardsByChildUsername(String childUsername);

    Reward getRewardById(Long id);

    void saveReward(Reward reward);

    void addRewardFromForm(RewardFormDTO rewardFormDTO);

    List<Reward> getEarnedRewardsByChild(Child child);


}

