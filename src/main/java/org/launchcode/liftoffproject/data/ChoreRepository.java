package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Chore;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ChoreRepository extends PagingAndSortingRepository<Chore, Integer> {
}
