package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Parent;
import org.launchcode.liftoffproject.models.Reward;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RewardRepository extends PagingAndSortingRepository<Reward, Integer> {

//    List<Reward> findAllByParentCreatorAndRedeemed(Parent parent, boolean redeemed);
}
