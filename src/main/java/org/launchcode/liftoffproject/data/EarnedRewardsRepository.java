package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Child;
import org.launchcode.liftoffproject.models.Reward;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface EarnedRewardsRepository extends PagingAndSortingRepository<Reward, Integer>{
    List<Reward> findAllByChild(Child child);
}
