package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Reward;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends PagingAndSortingRepository<Reward, Integer> {
}
