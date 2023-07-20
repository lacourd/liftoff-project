package org.launchcode.liftoffproject.data;

import org.launchcode.liftoffproject.models.Parent;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ParentRepository extends PagingAndSortingRepository<Parent, Integer> {
}
