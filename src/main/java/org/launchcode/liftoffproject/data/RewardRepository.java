package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Reward;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RewardRepository extends PagingAndSortingRepository<Reward, Integer> {
}
